package com.kaly7dev.socialntapp.coreapi.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String exMessage) {
        super(exMessage);
    }
}
