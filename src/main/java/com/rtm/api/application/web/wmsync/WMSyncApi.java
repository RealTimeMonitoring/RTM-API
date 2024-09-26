package com.rtm.api.application.web.wmsync;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/sync")
public interface WMSyncApi 
{
    @GetMapping
	@ResponseStatus( HttpStatus.OK )
	String sync();
}
