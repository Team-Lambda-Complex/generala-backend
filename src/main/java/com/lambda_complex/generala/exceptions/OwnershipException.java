package com.lambda_complex.generala.exceptions;

public class OwnershipException extends RuntimeException {
    public OwnershipException() {
        super("Input player is not the owner of this match");
    }
}
