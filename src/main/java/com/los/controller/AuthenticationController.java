package com.los.controller;

import com.los.dto.request.AuthenticationRequest;
import com.los.dto.response.AuthenticationResponse;
import com.los.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(path = "/login")
    public AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.login(authenticationRequest);
    }
}
