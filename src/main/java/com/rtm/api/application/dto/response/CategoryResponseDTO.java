package com.rtm.api.application.dto.response;

public record CategoryResponseDTO( Long id,
                                   Long productId,
                                   String description,
                                   String type,
                                   String example,
                                   String validateExpression ) {}
