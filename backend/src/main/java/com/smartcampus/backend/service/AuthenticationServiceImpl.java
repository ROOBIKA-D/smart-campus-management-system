package com.smartcampus.backend.service.impl;

import com.smartcampus.backend.dto.LoginRequest;
import com.smartcampus.backend.dto.LoginResponse;
import com.smartcampus.backend.security.JwtService;
import com.smartcampus.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        String token = jwtService.generateToken(loginRequest.getEmail());

        return new LoginResponse(token);
    }
}