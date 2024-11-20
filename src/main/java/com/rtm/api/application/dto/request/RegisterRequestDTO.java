package com.rtm.api.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequestDTO(@NotNull @NotBlank String email, @NotNull @NotBlank String password, @NotNull @NotBlank String username) {
}
