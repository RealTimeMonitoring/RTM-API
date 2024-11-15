package com.rtm.api.application.web.wmsync;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/sync")
public interface WSyncApi 
{
    @PostMapping
	@ResponseStatus( HttpStatus.OK )
	void sync();
}
