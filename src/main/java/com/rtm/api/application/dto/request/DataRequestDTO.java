package com.rtm.api.application.dto.request;

import com.rtm.api.domain.enums.RegisterStatus;
import com.rtm.api.domain.model.Data;
import lombok.Value;

import java.io.Serializable;

public record DataRequestDTO( String productId,  
                              String latitude,  
                              String longitude,  
                              String value,  
                              String description,  
                              RegisterStatus status
                            ){}