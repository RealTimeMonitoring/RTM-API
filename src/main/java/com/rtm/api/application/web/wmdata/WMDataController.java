package com.rtm.api.application.web.wmdata;

import com.rtm.api.application.dto.response.WMDataResponseDTO;
import com.rtm.api.domain.service.WMDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WMDataController implements WMDataApi
{
    private final WMDataService wmDataService;
    
    @Override
    public List<WMDataResponseDTO> getData( Integer page, Integer size ) 
    {
        return wmDataService.findAll( PageRequest.of( page, size ) );    
    }
    
    @Override
    public List<WMDataResponseDTO> getAllData() {
        return wmDataService.findAll();
    }
}
