package com.rtm.api.domain.mapper;

import com.rtm.api.application.dto.request.WDataRequestDTO;
import com.rtm.api.application.dto.response.DataResponseDTO;
import com.rtm.api.domain.model.Data;
import com.rtm.api.infra.config.BaseMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = BaseMapperConfig.class, componentModel = "spring")
public interface WDataMapper 
{
    @Mapping(source = "vendorid", target = "vendorId")
    @Mapping(source = "productid", target = "productId")
    @Mapping(source = "dateinsert", target = "dtInsert", dateFormat = "yyyy-MM-dd HH:mm:ss")
    Data dtoToEntity(WDataRequestDTO dto);
    
    DataResponseDTO entityToDTO(Data data);
}
