package com.rtm.api.application.web.wmsync;

import com.rtm.api.domain.service.WMCategoryService;
import com.rtm.api.domain.service.WMDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WMSyncController implements WMSyncApi
{
    private final WMDataService dataService;
    private final WMCategoryService categoryService;
    
    @Override
    public String sync()
    {
        dataService.sync();
        categoryService.sync();
        
        return "Dados sincronizados";
    }
}
