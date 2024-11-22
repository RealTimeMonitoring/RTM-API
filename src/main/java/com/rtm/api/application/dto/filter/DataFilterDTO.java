package com.rtm.api.application.dto.filter;

import com.rtm.api.domain.enums.RegisterStatus;

import java.time.LocalDate;

public record DataFilterDTO(String category, String vendorId, LocalDate startDate, LocalDate endDate, RegisterStatus status) {}
