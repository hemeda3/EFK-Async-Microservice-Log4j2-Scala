package com.ahmed.exceptions;

public class InputValidationException extends RuntimeException {

  public InputValidationException(String message) {
    super("Some input parameters are missing or not valid {empty or null }");
  }
}
