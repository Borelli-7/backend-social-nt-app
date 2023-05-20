package com.kaly7dev.socialntapp.services;

import com.kaly7dev.socialntapp.coreapi.dtos.RegisterRequest;
import com.kaly7dev.socialntapp.entities.User;
import com.kaly7dev.socialntapp.entities.VerificationToken;
import com.kaly7dev.socialntapp.model.NotificationEmail;
import com.kaly7dev.socialntapp.repositories.UserRepo;
import com.kaly7dev.socialntapp.repositories.VerificationTokenRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
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

    private String generateVerificationToken(User user) {
        String token= UUID.randomUUID().toString();
        VerificationToken verificationToken= new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepo.save(verificationToken);
        return token;
    }
}
