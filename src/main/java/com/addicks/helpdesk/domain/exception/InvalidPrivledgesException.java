package com.addicks.helpdesk.domain.exception;

public class InvalidPrivledgesException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public InvalidPrivledgesException() {

  }

  @Override
  public String getMessage() {
    return "You do not have the privledges to do this operation.";
  }
}
