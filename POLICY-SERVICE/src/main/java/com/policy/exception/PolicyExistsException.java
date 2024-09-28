package com.policy.exception;

public class PolicyExistsException extends RuntimeException {

    PolicyExistsException(){

    }

    public PolicyExistsException(String message){
        super(message);
    }
}
