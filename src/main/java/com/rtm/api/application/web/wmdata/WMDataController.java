package com.rtm.api.application.web.wmdata;

import com.rtm.api.application.dto.response.WMDataResponseDTO;
import com.rtm.api.domain.service.WMDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WMDataController implements WMDataApi
{
    private final WMDataService wmDataService;
    
    @Override
    public List<WMDataResponseDTO> getData( Integer offset, Integer limit ) 
    {
        return wmDataService.findAll( offset, limit );    
    }
}
