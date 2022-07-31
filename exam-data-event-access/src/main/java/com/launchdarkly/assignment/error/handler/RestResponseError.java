package com.launchdarkly.assignment.error.handler;

public class RestResponseError extends Exception{
  private final int errorCode;
  private final String errorMessage;

  public RestResponseError(int errorCode, String errorMessage) {
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
