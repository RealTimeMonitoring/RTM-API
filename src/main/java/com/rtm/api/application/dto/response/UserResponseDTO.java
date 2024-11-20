package com.rtm.api.application.dto.response;

import com.rtm.api.domain.enums.Role;

import java.io.Serializable;

/**
 * DTO for {@link com.rtm.api.domain.model.User}
 */
public record UserResponseDTO( Long id, String email, String name, Role role )
{
}