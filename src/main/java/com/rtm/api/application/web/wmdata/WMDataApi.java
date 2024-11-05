package com.rtm.api.application.web.wmdata;

import com.rtm.api.application.dto.response.WMDataResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/data")
public interface WMDataApi 
{
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<WMDataResponseDTO> getData(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit);
    
    
}
