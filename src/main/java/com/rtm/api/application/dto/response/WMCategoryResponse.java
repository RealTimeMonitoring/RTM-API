package com.rtm.api.application.dto.response;

public record WMCategoryResponse(Long id, Long productId, String description, String type, String example,
                                 String validateExpression) {}
