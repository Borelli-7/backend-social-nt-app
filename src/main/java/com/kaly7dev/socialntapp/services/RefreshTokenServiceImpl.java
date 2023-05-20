package com.kaly7dev.socialntapp.services;

import com.kaly7dev.socialntapp.coreapi.exceptions.SocialNtException;
import com.kaly7dev.socialntapp.entities.RefreshToken;
import com.kaly7dev.socialntapp.repositories.RefreshTokenRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepo refreshTokenRepo;
    @Override
    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        return refreshTokenRepo.save(refreshToken);
    }

    @Override
    public void validateRefreshToken(String refreshToken) {
        refreshTokenRepo.findByToken(refreshToken)
                .orElseThrow(() -> new SocialNtException("Invalid refresh token"));
    }

    @Override
    public void deleteRefreshToken(String refreshToken) {
        refreshTokenRepo.deleteByToken(refreshToken);
    }
}
