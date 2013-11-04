package com.addicks.helpdesk.domain.exception;

import com.unboundid.ldap.sdk.ResultCode;
import com.unboundid.util.Validator;

public class PasswordModifyExtendedOperationFailedException extends Exception {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructs the object and sets its state with the {@code resultCode}
   * parameter.
   * 
   * @param resultCode
   *          The result code from a failed password modify extended operation
   *          failed exception.
   */
  public PasswordModifyExtendedOperationFailedException(final ResultCode resultCode) {
    Validator.ensureNotNull(resultCode);
    this.resultCode = resultCode;
  }

  /**
   * retrieves the result code associated with the exception.
   * 
   * @return result code from the exception.
   */
  public ResultCode getResultCode() {
    return resultCode;
  }

  private final ResultCode resultCode;

}
