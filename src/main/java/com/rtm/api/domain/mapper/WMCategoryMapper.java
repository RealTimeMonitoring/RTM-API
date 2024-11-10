package com.rtm.api.domain.mapper;

import com.rtm.api.application.dto.request.WMCategoryDTO;
import com.rtm.api.application.dto.response.WMCategoryResponse;
import com.rtm.api.domain.model.WMCategory;
import com.rtm.api.infra.config.BaseMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( config = BaseMapperConfig.class, componentModel = "spring")
public interface WMCategoryMapper
{
	@Mapping(source = "productid", target = "productId")
	@Mapping(source = "validateexpression", target = "validateExpression")
	WMCategory dtoToModel( WMCategoryDTO dto);
	
	WMCategoryResponse entityToDto( WMCategory entity);
}
