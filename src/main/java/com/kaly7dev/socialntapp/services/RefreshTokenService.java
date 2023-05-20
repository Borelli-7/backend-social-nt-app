package com.kaly7dev.socialntapp.services;

import com.kaly7dev.socialntapp.entities.RefreshToken;

public interface RefreshTokenService {
    RefreshToken generateRefreshToken();

    void validateRefreshToken(String refreshToken);

    void deleteRefreshToken(String refreshToken);
}
