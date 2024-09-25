package com.rtm.api.domain.mapper;

import com.rtm.api.application.dto.request.WMDataDTO;
import com.rtm.api.domain.model.WMData;
import com.rtm.api.infra.config.BaseMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = BaseMapperConfig.class, componentModel = "spring")
public interface WMDataMapper 
{
    @Mapping(source = "vendorid", target = "vendorId")
    @Mapping(source = "productid", target = "productId")
    @Mapping(source = "dateinsert", target = "dtInsert", dateFormat = "yyyy-MM-dd HH:mm:ss")
    WMData dtoToModel(WMDataDTO dto);
}
