package com.kaly7dev.socialntapp.services;

import com.kaly7dev.socialntapp.coreapi.dtos.AuthenticationResponse;
import com.kaly7dev.socialntapp.coreapi.dtos.LoginRequest;
import com.kaly7dev.socialntapp.coreapi.dtos.RefreshTokenRequest;
import com.kaly7dev.socialntapp.coreapi.dtos.RegisterRequest;
import com.kaly7dev.socialntapp.entities.User;

public interface AuthService {

    User getCurrentUser();

    void signup(RegisterRequest registerRequest);

    void verifyAccount(String token);

    AuthenticationResponse login(LoginRequest loginRequest);

    AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
