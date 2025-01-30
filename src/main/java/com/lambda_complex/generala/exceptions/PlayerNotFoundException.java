package com.lambda_complex.generala.exceptions;

public class PlayerNotFoundException extends RuntimeException {
  public PlayerNotFoundException(String message) {
    super(message);
  }
}
