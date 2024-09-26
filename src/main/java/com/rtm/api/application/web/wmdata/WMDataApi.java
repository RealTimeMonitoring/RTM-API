package com.rtm.api.application.web.wmdata;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/data")
public interface WMDataApi 
{
    @GetMapping("/sync")
    @ResponseStatus(HttpStatus.OK)
    String syncData();
}
