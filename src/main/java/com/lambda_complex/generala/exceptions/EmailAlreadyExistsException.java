package com.lambda_complex.generala.exceptions;

import lombok.Getter;

@Getter
public class EmailAlreadyExistsException extends RuntimeException{
    private String value;
    public EmailAlreadyExistsException(String message, String value){
        super(message);
        this.value = value;
    }
}
