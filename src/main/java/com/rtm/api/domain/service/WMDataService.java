package com.rtm.api.domain.service;

import com.rtm.api.application.dto.request.WMDataDTO;
import com.rtm.api.domain.mapper.WMDataMapper;
import com.rtm.api.domain.model.WMData;
import com.rtm.api.infra.repository.WMDataRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WMDataService 
{
    public static String BASE_WM_URL = "http://177.44.248.13:8080";
    
    private final WMDataMapper wmDataMapper = Mappers.getMapper( WMDataMapper.class );
    private final RestTemplate restTemplate;
    private final WMDataRepository wMDataRepository;
    
    public String syncDB() 
    {
        ResponseEntity<WMDataDTO[]> resp = restTemplate.getForEntity( BASE_WM_URL + "/WaterManager?op=SELECT&FORMAT=JSON", WMDataDTO[].class );
        
        List<WMData> values = Arrays.stream(resp.getBody()).map( wmDataMapper::dtoToModel).toList();
        
        wMDataRepository.saveAll( values );
        
        return "Dados sincronizados!";
    }
}
