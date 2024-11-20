package com.rtm.api.application.web.wmdata;

import com.rtm.api.application.dto.request.DataRequestDTO;
import com.rtm.api.application.dto.response.DataResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/data")
public interface DataApi 
{
    @GetMapping("/offset")
    @ResponseStatus(HttpStatus.OK)
    List<DataResponseDTO> getData(@RequestParam( name = "page") Integer page, @RequestParam( name = "size" ) Integer size);
    
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    List<DataResponseDTO> getAllData();
    
    @GetMapping("/heatmap")
    @ResponseStatus(HttpStatus.OK)
    List<DataResponseDTO> getAllDataHeatMap();
    
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    void save(@RequestBody DataRequestDTO dto);
}
