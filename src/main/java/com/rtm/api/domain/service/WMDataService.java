package com.rtm.api.domain.service;

import com.rtm.api.application.dto.request.WMDataDTO;
import com.rtm.api.domain.mapper.WMDataMapper;
import com.rtm.api.domain.model.WMData;
import com.rtm.api.infra.repository.WMDataRepository;
import com.rtm.api.infra.util.WMUrlUtilities;
import lombok.RequiredArgsConstructor;
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
    private final WMDataMapper wmDataMapper = Mappers.getMapper( WMDataMapper.class );
    

    private final RestTemplate restTemplate;
    private final WMDataRepository wMDataRepository;
    private final OffsetService offsetService;
    
    public String sync() 
    {
        int offset = offsetService.getLastOffSet();
        boolean hasData = true;
        
        try 
        {
            while (hasData)
            {
                String url = WMUrlUtilities.BASE_WM_URL + "?" + String.format( WMUrlUtilities.SELECT, offset );
                ResponseEntity<WMDataDTO[]> resp = restTemplate.getForEntity( url, WMDataDTO[].class );
                List<WMData> values = Arrays.stream( resp.getBody() ).map( wmDataMapper::dtoToModel).toList();
                
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
            throw new RuntimeException(e);
        }
    }
    
    public List<WMData> getValues()
    {
        return wMDataRepository.findAll();
    }
}
