package com.rtm.api.application.dto.response;

import com.rtm.api.domain.enums.RegisterStatus;

import java.time.LocalDateTime;

public record DataResponseDTO( Long id,
                               String vendorId,
                               String latitude,
                               String longitude,
                               String value,
                               String description,
                               RegisterStatus status,
                               LocalDateTime dtInsert,
                               CategoryResponseDTO category ) {
}
