package com.rtm.api.domain.service;

import com.rtm.api.application.dto.request.WMDataDTO;
import com.rtm.api.application.dto.response.WMCategoryResponse;
import com.rtm.api.application.dto.response.WMDataResponseDTO;
import com.rtm.api.domain.mapper.WMCategoryMapper;
import com.rtm.api.domain.mapper.WMDataMapper;
import com.rtm.api.domain.model.WMCategory;
import com.rtm.api.domain.model.WMData;
import com.rtm.api.infra.repository.WMCategoryRepository;
import com.rtm.api.infra.repository.WMDataRepository;
import com.rtm.api.infra.util.WMUrlUtilities;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WMDataService {
    private final WMDataMapper wmDataMapper = Mappers.getMapper(WMDataMapper.class);
    private final WMCategoryMapper wmCategoryMapper = Mappers.getMapper(WMCategoryMapper.class);
    
    
    private final RestTemplate restTemplate;
    private final WMDataRepository wMDataRepository;
    private final WMCategoryRepository wmCategoryRepository;
    private final OffsetService offsetService;
    private final WMDataMapper dataMapper = Mappers.getMapper(WMDataMapper.class);
    
    public String sync() {
        int offset = offsetService.getLastOffSet();
        boolean hasData = true;
        
        try 
        {
            while ( hasData )
            {
                String url = WMUrlUtilities.BASE_WM_URL + "?" + String.format( WMUrlUtilities.SELECT, offset );
                ResponseEntity<WMDataDTO[]> resp = restTemplate.getForEntity( url, WMDataDTO[].class );
                List<WMData> values = Arrays.stream( resp.getBody() ).map( wmDataMapper::dtoToModel ).toList();
                
                if ( values != null && !values.isEmpty() )
                {
                    wMDataRepository.saveAll( values );
                    
                    if ( values.size() == WMUrlUtilities.LIMIT )
                    {
                        offset += WMUrlUtilities.LIMIT;
                        
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
            
            return "Dados sincronizados!";
        }
        
        catch ( Exception e ) 
        {
            throw new RuntimeException( e );
        }
    }
    
    public List<WMDataResponseDTO> findAll(Pageable pageable) {
        Map<Long, WMCategory> wmCategoryMap = wmCategoryRepository.findAll().stream().collect(Collectors.toMap(WMCategory::getId, c -> c));
        
        return wMDataRepository.findAll( pageable ).stream().map( wmData -> 
                {
                    String productId = wmData.getProductId();
                    WMCategory category = null;
                    
                    try 
                    {
                        category = wmCategoryMap.get( Long.parseLong( productId ) );
                    }
                    
                    catch ( Exception e ) 
                    {
                        System.out.println( "Wolf ainda não limpou o db, ta cheio de lixo..." );
                    }
                    
                    return new WMDataResponseDTO( wmData.getId(),
                                                  wmData.getVendorId(),
                                                  wmData.getLatitude(),
                                                  wmData.getLongitude(),
                                                  wmData.getValue(),
                                                  wmData.getDtInsert(),
                                                  category == null ? null
                                                  : wmCategoryMapper.entityToDto( category ) );
                } ).toList();
    }
}
