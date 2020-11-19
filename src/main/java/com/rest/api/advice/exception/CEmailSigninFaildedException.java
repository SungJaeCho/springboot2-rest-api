package com.rest.api.advice.exception;


public class CEmailSigninFaildedException extends RuntimeException{
    public CEmailSigninFaildedException(String msg, Throwable t) {
        super(msg, t);
    }
    public CEmailSigninFaildedException(String msg) {
        super(msg);
    }
    public CEmailSigninFaildedException() {
        super();
    }
}
