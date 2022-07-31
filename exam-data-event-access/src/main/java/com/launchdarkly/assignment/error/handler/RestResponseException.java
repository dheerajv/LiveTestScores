package com.launchdarkly.assignment.error.handler;

public final class RestResponseException extends Exception{
  private final int errorCode;
  private final String errorMessage;

  public RestResponseException(int errorCode, String errorMessage) {
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
