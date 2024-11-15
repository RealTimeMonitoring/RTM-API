package com.rtm.api.domain.mapper;

import com.rtm.api.application.dto.request.WCategoryRequestDTO;
import com.rtm.api.application.dto.response.CategoryResponseDTO;
import com.rtm.api.domain.model.Category;
import com.rtm.api.infra.config.BaseMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( config = BaseMapperConfig.class, componentModel = "spring")
public interface CategoryMapper
{
	@Mapping(source = "productid", target = "productId")
	@Mapping(source = "validateexpression", target = "validateExpression")
	Category dtoToModel(WCategoryRequestDTO dto);
	
	CategoryResponseDTO entityToDto(Category entity);
}
