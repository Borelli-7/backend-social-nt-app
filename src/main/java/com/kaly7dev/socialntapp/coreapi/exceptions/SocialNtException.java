package com.kaly7dev.socialntapp.coreapi.exceptions;

public class SocialNtException extends RuntimeException{
    public SocialNtException(String exMessage, Exception exception){
        super(exMessage, exception);
    }
    public SocialNtException(String exMessage){
        super(exMessage);
    }
}
