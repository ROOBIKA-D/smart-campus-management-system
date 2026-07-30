package com.smartcampus.backend.controller;

import com.smartcampus.backend.dto.LoginRequest;
import com.smartcampus.backend.dto.LoginResponse;
import com.smartcampus.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }
}