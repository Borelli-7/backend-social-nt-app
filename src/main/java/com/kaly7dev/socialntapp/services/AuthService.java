package com.kaly7dev.socialntapp.services;

import com.kaly7dev.socialntapp.coreapi.dtos.RegisterRequest;
import com.kaly7dev.socialntapp.entities.User;

public interface AuthService {

    User getCurrentUser();

    void signup(RegisterRequest registerRequest);

    void verifyAccount(String token);
}
