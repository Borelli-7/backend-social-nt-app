package com.kaly7dev.socialntapp.services;

import com.kaly7dev.socialntapp.coreapi.dtos.AuthenticationResponse;
import com.kaly7dev.socialntapp.coreapi.dtos.LoginRequest;
import com.kaly7dev.socialntapp.coreapi.dtos.RegisterRequest;
import com.kaly7dev.socialntapp.coreapi.exceptions.SocialNtException;
import com.kaly7dev.socialntapp.entities.User;
import com.kaly7dev.socialntapp.entities.VerificationToken;
import com.kaly7dev.socialntapp.model.NotificationEmail;
import com.kaly7dev.socialntapp.repositories.UserRepo;
import com.kaly7dev.socialntapp.repositories.VerificationTokenRepo;
import com.kaly7dev.socialntapp.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepo verificationTokenRepo;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService  refreshTokenService;
    @Override
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        Jwt principal= (Jwt) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return userRepo.findByUsername(principal.getSubject())
                .orElseThrow(()->new UsernameNotFoundException("User name not found - "+ principal.getSubject()));
    }

    @Override
    public void signup(RegisterRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .created(Instant.now())
                .enabled(false)
                .build();

        userRepo.save(user);

        String token= generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please activate your Account",
                user.getEmail(), "Thank you to signing up to Social Network,"+
                "please click on the below link url to activate your account : "+
                "https://localhost:8080/api/auth/accountVerification/"+ token));

    }

    @Override
    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken= verificationTokenRepo.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(()->new SocialNtException("Invalid Token")));
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate= authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken( loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token= jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username= verificationToken.getUser().getUsername();
        User user= userRepo.findByUsername(username).orElseThrow(
                ()->new SocialNtException(" User Not found with name: "+ username));
        user.setEnabled(true);
        userRepo.save(user);
    }

    private String generateVerificationToken(User user) {
        String token= UUID.randomUUID().toString();
        VerificationToken verificationToken= new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepo.save(verificationToken);
        return token;
    }
}
