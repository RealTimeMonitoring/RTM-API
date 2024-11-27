package com.rtm.api.application.web.data;

import com.rtm.api.application.dto.filter.DataFilterDTO;
import com.rtm.api.application.dto.request.DataRequestDTO;
import com.rtm.api.application.dto.response.DataResponseDTO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/data")
public interface DataApi 
{
    @GetMapping("/offset")
    @ResponseStatus( HttpStatus.OK )
    List<DataResponseDTO> getData( @RequestParam( name = "page") Integer page, 
                                   @RequestParam( name = "size" ) Integer size,
                                   @ParameterObject DataFilterDTO filter );
    
    @GetMapping("/all")
    @ResponseStatus( HttpStatus.OK )
    List<DataResponseDTO> getAllData();
    
    @GetMapping("/all/filter")
    @ResponseStatus( HttpStatus.OK )
    List<DataResponseDTO> getDataFilter( @ParameterObject DataFilterDTO filter );
    
    @GetMapping("/permit")
    @ResponseStatus( HttpStatus.OK )
    List<DataResponseDTO> getPermittedData( @ParameterObject DataFilterDTO filter );
    
    @PostMapping( "/permit" )
    @ResponseStatus(HttpStatus.CREATED)
    void save(@RequestBody DataRequestDTO dto);
    
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    void update(@RequestBody DataRequestDTO dto);
}
