package com.rtm.api.domain.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rtm.api.application.dto.request.WCategoryRequestDTO;
import com.rtm.api.application.dto.request.WDataRequestDTO;
import com.rtm.api.domain.mapper.CategoryMapper;
import com.rtm.api.domain.mapper.WDataMapper;
import com.rtm.api.domain.model.Category;
import com.rtm.api.domain.model.Data;
import com.rtm.api.infra.repository.CategoryRepository;
import com.rtm.api.infra.repository.DataRepository;
import com.rtm.api.infra.util.WUrlUtilities;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SyncService 
{
    private final CategoryRepository categoryRepository;
    private final DataRepository dataRepository;
    
    private final OffsetService offsetService;
    
    private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
    private final WDataMapper wDataMapper = Mappers.getMapper(WDataMapper.class);
    
    private final RestTemplate restTemplate;
    
    public void sync()
	{
        try 
		{
            syncCategories();
            syncData();
		}
		
		catch ( Exception e )
		{
			throw new RuntimeException( "Error syncing products" );
		}
	}
	
	private void syncCategories() throws Exception
    {
        String syncUrl = WUrlUtilities.BASE_WM_URL + "/productID.jsp?" + WUrlUtilities.FORMAT;
            
        ResponseEntity<String> categoriesJson = restTemplate.getForEntity( syncUrl, String.class );
        
        if ( categoriesJson.getStatusCode().is2xxSuccessful() )
        {
            ArrayList<WCategoryRequestDTO> categoryDTOS = new Gson().fromJson( categoriesJson.getBody().replace( "\\", "\\\\" )
                                                                                                       .trim(), TypeToken.getParameterized( ArrayList.class,
                                                                                                                                            WCategoryRequestDTO.class ).getType() );
            
            List<Category> categories = categoryDTOS.stream().map( categoryMapper::dtoToModel ).toList();
            
            categoryRepository.saveAll( categories );
        }
    }
    
    private void syncData()
    {
        int offset = offsetService.getLastOffSet();
        boolean hasData = true;
        
        while ( hasData )
        {
            String url = WUrlUtilities.BASE_WM_URL + "?" + String.format( WUrlUtilities.SELECT, offset );
            ResponseEntity<WDataRequestDTO[]> resp = restTemplate.getForEntity( url, WDataRequestDTO[].class );
            List<Data> values = Arrays.stream( Objects.requireNonNull( resp.getBody() ) ).map( wDataMapper::dtoToEntity ).toList();
            
            if ( !values.isEmpty() )
            {
                dataRepository.saveAll( values );
                
                if ( values.size() == WUrlUtilities.LIMIT )
                {
                    offset += WUrlUtilities.LIMIT;
                    
                    offsetService.saveLastOffset( offset );
                }
                
                else if ( values.size() < WUrlUtilities.LIMIT )
                {
                    offset += values.size();
                    
                    offsetService.saveLastOffset( offset );
                }
                
                else 
                {
                    hasData = false;
                }
            } 
            
            else 
            {
                hasData = false;
            }
        }
    }
}