package com.rtm.api.domain.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rtm.api.application.dto.request.WMCategoryDTO;
import com.rtm.api.application.dto.response.WMCategoryResponse;
import com.rtm.api.domain.mapper.WMCategoryMapper;
import com.rtm.api.domain.model.WMCategory;
import com.rtm.api.infra.repository.WMCategoryRepository;
import com.rtm.api.infra.util.WMUrlUtilities;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WMCategoryService
{
	private final WMCategoryRepository categoryRepository;
	private final WMCategoryMapper wmCategoryMapper = Mappers.getMapper( WMCategoryMapper.class );
	
	private final RestTemplate restTemplate;
	
	public String sync()
	{
		try 
		{
			ResponseEntity<String> categoriesJson = restTemplate.getForEntity( WMUrlUtilities.BASE_WM_URL+ "/productID.jsp?" + WMUrlUtilities.FORMAT, String.class );
			
			if ( categoriesJson.hasBody() )
			{
				ArrayList<WMCategoryDTO> categoryDTOS = new Gson().fromJson( categoriesJson.getBody().replace( "\\", "\\\\" ).trim(), TypeToken.getParameterized( ArrayList.class, WMCategoryDTO.class ).getType() );
				
				List<WMCategory> categories = categoryDTOS.stream().map( wmCategoryMapper::dtoToModel ).toList();
				
				categoryRepository.saveAll( categories );
			}
			
			return "Categorias sincronizadas";
		}
		
		catch ( Exception e )
		{
			throw new RuntimeException( e );
		}
	}
	
	public List<WMCategoryResponse> getCategories()
	{
		return categoryRepository.findAll().stream().map(wmCategoryMapper::entityToDto).toList();
	}
}
