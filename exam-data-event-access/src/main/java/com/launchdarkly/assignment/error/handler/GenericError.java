package com.launchdarkly.assignment.error.handler;

public class GenericError {
  private final int errorCode;
  private final String errorMessage;

  public GenericError(int errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
