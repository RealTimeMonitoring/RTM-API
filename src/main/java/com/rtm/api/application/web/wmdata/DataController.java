package com.rtm.api.application.web.wmdata;

import com.rtm.api.application.dto.request.DataRequestDTO;
import com.rtm.api.application.dto.response.DataResponseDTO;
import com.rtm.api.domain.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DataController implements DataApi
{
    private final DataService dataService;
    
    @Override
    public List<DataResponseDTO> getData(Integer page, Integer size ) 
    {
        return dataService.findAll( PageRequest.of( page, size ) );    
    }
    
    @Override
    public List<DataResponseDTO> getAllData() {
        return dataService.findAll();
    }
    
    @Override
    public void save(DataRequestDTO dto) 
    {
        dataService.save( dto );    
    }
}
