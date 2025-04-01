package com.lambda_complex.generala.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class PlayersNotFoundException extends RuntimeException {

  private List<String> errors;

  public PlayersNotFoundException(List<String> errors) {
    super("Player emails not found");
    this.errors = errors;
  }
}
