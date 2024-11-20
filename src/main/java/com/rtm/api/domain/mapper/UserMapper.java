package com.rtm.api.domain.mapper;

import com.rtm.api.application.dto.response.UserResponseDTO;
import com.rtm.api.domain.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)public interface UserMapper {
    User toEntity(UserResponseDTO userResponseDTO);
    
    UserResponseDTO toDto(User user);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)User partialUpdate(UserResponseDTO userResponseDTO, @MappingTarget User user);
}