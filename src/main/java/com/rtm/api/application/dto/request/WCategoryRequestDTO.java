package com.rtm.api.application.dto.request;

public record WCategoryRequestDTO(String id,
                                  String productid,
                                  String description,
                                  String type,
                                  String example,
                                  String validateexpression )
{
}
