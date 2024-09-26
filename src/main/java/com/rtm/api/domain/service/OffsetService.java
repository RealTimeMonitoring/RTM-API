package com.rtm.api.domain.service;

import com.rtm.api.domain.model.OffsetEntity;
import com.rtm.api.infra.repository.OffsetEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OffsetService 
{
    private final OffsetEntityRepository offsetEntityRepository;
    
    public int getLastOffSet()
    {
        return offsetEntityRepository.findLastOffSet().orElse( 0 );
    }
    
    public void saveLastOffset( int offset )
    {
        offsetEntityRepository.save( new OffsetEntity( offset ) );
    }
}
