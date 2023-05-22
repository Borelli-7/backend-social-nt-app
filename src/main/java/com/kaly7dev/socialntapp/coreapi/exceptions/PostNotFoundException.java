package com.kaly7dev.socialntapp.coreapi.exceptions;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String exMessage) {
        super(exMessage);
    }
}
