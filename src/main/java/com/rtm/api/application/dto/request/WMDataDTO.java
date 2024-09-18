package com.rtm.api.application.dto.request;

import java.time.LocalDateTime;

public record WMDataDTO(String id,
                        String vendorId,
                        String productId,
                        String latitude,
                        String longitude,
                        String value,
                        String dtInsert) {
}
