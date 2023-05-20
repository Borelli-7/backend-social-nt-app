package com.kaly7dev.socialntapp.services.security;

import org.springframework.security.core.Authentication;

public interface JwtProvider {

    String generateToken(Authentication authenticate);

    String generateTokenWithUsername(String username);

    Long getJwtExpirationInMillis();
}
