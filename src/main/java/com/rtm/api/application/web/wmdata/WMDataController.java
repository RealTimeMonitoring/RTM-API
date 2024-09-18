package com.rtm.api.application.web.wmdata;

import com.rtm.api.domain.service.WMDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WMDataController implements WMDataApi
{
    private final WMDataService wmDataService;
    
    @Override
    public String syncDB() 
    {
        return wmDataService.syncDB();    
    }
}
