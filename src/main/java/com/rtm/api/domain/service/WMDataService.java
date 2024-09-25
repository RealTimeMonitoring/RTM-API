package com.rtm.api.domain.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rtm.api.application.dto.request.WMCategoryDTO;
import com.rtm.api.application.dto.request.WMDataDTO;
import com.rtm.api.domain.mapper.WMCategoryMapper;
import com.rtm.api.domain.mapper.WMDataMapper;
import com.rtm.api.domain.model.WMCategory;
import com.rtm.api.domain.model.WMData;
import com.rtm.api.infra.repository.WMCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WMDataService
{
	public static String BASE_WM_URL = "http://177.44.248.13:8080";

	private final WMDataMapper wmDataMapper = Mappers.getMapper( WMDataMapper.class );
	private final WMCategoryMapper wmCategoryMapper = Mappers.getMapper( WMCategoryMapper.class );

	private final WMCategoryRepository categoryRepository;

	private final RestTemplate restTemplate;

	public String syncDB()
	{
		ResponseEntity<WMDataDTO[]> resp = restTemplate.getForEntity( BASE_WM_URL + "/WaterManager?op=SELECT&FORMAT=JSON", WMDataDTO[].class );

		List<WMData> values = Arrays.stream( resp.getBody() ).map( wmDataMapper::dtoToModel ).toList();

		ResponseEntity<String> categoriesJson = restTemplate.getForEntity( "http://177.44.248.13:8080/WaterManager/productID.jsp?FORMAT=JSON", String.class );

		if ( categoriesJson.hasBody() )
		{
			ArrayList<WMCategoryDTO> categoryDTOS = new Gson().fromJson( categoriesJson.getBody().replace( "\\", "\\\\" ).trim(), TypeToken.getParameterized( ArrayList.class, WMCategoryDTO.class ).getType() );

			List<WMCategory> categories = categoryDTOS.stream().map( wmCategoryMapper::dtoToModel ).toList();

			categoryRepository.saveAll( categories );
		}

		values.forEach( System.out::println );
		return "Dados sincronizados!";
	}
}
