package com.rtm.api.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rtm.api.domain.enums.RegisterStatus;
import com.rtm.api.domain.model.Data;
import lombok.Value;

import java.io.Serializable;

public record DataRequestDTO( Long id,
                              String productId,  
                              String latitude,  
                              String longitude,  
                              String value,  
                              String description,
                              RegisterStatus status){}