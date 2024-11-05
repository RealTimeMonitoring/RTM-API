package com.rtm.api.application.dto.response;

import java.time.LocalDateTime;

public record WMDataResponseDTO(Long id, String vendorId, String productId, String latitude, String longitude, String value, LocalDateTime dtInsert) {
}
