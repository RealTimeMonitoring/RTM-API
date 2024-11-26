package com.rtm.api.application.dto.request;


public record WDataRequestDTO(String vendorid,
                              String productid,
                              String latitude,
                              String longitude,
                              String value,
                              String dateinsert) {
}
