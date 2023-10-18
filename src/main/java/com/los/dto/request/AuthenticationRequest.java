package com.los.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthenticationRequest {

    @NotNull(message = "username is required")
    private String username;

    @NotNull(message = "password is required")
    private String password;
}
