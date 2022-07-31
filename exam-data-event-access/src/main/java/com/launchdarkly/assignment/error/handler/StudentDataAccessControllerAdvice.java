package com.launchdarkly.assignment.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentDataAccessControllerAdvice {

  @ExceptionHandler(RestResponseError.class)
  public ResponseEntity<?> handleInvalidInput(RestResponseError exception){
    GenericError error = new GenericError(exception.getErrorCode(), exception.getErrorMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleGlobalException(Exception exception){
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
