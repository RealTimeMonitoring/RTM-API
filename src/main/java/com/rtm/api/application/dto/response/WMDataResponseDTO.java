package com.rtm.api.application.dto.response;

import java.time.LocalDateTime;

public record WMDataResponseDTO(Long id, String vendorId, String latitude, String longitude, String value, LocalDateTime dtInsert, WMCategoryResponse category ) {
}
