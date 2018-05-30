package com.ahmed.enums;

public enum StatfloErrors{
  DATABASE("00", "A database error has occured."),
  DUPLICATE_USER("10", "This user already exists."),
  USER_NOT_EXIST("10", "This user already exists."),
  INPUT_VALIDATION("11", "Input data validation."),
  GENERIC_ERROR("10", "Generic error");

  private final String code;
  private final String description;

  private StatfloErrors(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public String getCode() {
    return code;
  }

  @Override
  public String toString() {
    return code + ": " + description;
  }
}