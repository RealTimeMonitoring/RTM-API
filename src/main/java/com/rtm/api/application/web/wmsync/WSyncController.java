package com.rtm.api.application.web.wmsync;

import com.rtm.api.domain.service.SyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WSyncController implements WSyncApi
{
    private final SyncService syncService;
    
    @Override
    public void sync()
    {
        syncService.sync();
    }
}
