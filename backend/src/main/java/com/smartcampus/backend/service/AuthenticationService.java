package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.LoginRequest;
import com.smartcampus.backend.dto.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest loginRequest);

}