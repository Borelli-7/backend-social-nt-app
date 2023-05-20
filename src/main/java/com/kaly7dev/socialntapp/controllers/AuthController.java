package com.kaly7dev.socialntapp.controllers;

import com.kaly7dev.socialntapp.services.AuthService;
import com.kaly7dev.socialntapp.coreapi.dtos.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful", OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verificationAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Accont activated successfully", OK);
    }

}
