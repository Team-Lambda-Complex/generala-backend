package com.lambda_complex.generala.exceptions;

import lombok.Getter;

@Getter
public class PlayerNotFoundException extends RuntimeException {

  private final String key;
  private final String value;

  public PlayerNotFoundException(String key, String value) {
      this.key = key;
      this.value = value;
  }
}
