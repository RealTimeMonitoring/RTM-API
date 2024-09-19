package com.rtm.api.application.dto.request;


public record WMDataDTO(String id,
                        String vendorid,
                        String productid,
                        String latitude,
                        String longitude,
                        String value,
                        String dateinsert) {
}
