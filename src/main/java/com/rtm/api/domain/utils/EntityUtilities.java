package com.rtm.api.domain.utils;

import com.rtm.api.domain.exception.NotFoundException;
import com.rtm.api.domain.model.Data;
import com.rtm.api.infra.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EntityUtilities 
{
    private final DataRepository dataRepository;
    
    public Data getDataEntity( Long id )
    {
        return dataRepository.findById(id).orElseThrow( () -> new NotFoundException( "Data not found" ) );
    }
}
