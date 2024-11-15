package com.rtm.api.domain.mapper;

import com.rtm.api.application.dto.request.DataRequestDTO;
import com.rtm.api.application.dto.response.DataResponseDTO;
import com.rtm.api.domain.model.Data;
import com.rtm.api.infra.config.BaseMapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class, componentModel = "spring")
public interface DataMapper 
{
    Data dtoToEntity( DataRequestDTO dto );
    
    DataResponseDTO EntityToDTO (Data dto );
}
