package com.addicks.helpdesk.service.ldap;

import static com.unboundid.util.Validator.ensureNotNullWithMessage;

import com.addicks.helpdesk.domain.exception.InvalidPrivledgesException;
import com.addicks.helpdesk.domain.exception.PasswordModifyExtendedOperationFailedException;
import com.addicks.helpdesk.domain.ldap.SupportedFeature;
import com.unboundid.ldap.sdk.DN;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.ResultCode;
import com.unboundid.ldap.sdk.extensions.PasswordModifyExtendedRequest;
import com.unboundid.ldap.sdk.extensions.PasswordModifyExtendedResult;
import com.unboundid.util.NotMutable;

/**
 * Provide support for changing the authentication password of an entry.
 */

@NotMutable
public class ChangePassword {

  /**
   * Gets a new and distinct instance of the {@code ChangePassword} object that
   * will use the supplied {@code ldapConnection} for changing passwords.
   * 
   * @param ldapConnection
   *          connection to LDAP server, {@code null} object not permitted.
   * 
   * @return a new and distinct instance of the {@code ChangePassword} object.
   */
  public static ChangePassword newChangePassword(final LDAPConnection ldapConnection) {
    return new ChangePassword(ldapConnection);
  }

  private ChangePassword(final LDAPConnection ldapConnection) {
    ensureNotNullWithMessage(ldapConnection, "ldapConnection was null");
    this.ldapConnection = ldapConnection;
  }

  /**
   * Changes the password of the entry. Requires the existing password and the
   * new password.
   * 
   * @param distinguishedName
   *          the distinguished name of the entry whose password will be
   *          changed.
   * @param existingPassword
   *          the existing password of the entry, {@code null} is not permitted.
   * @param newPassword
   *          the new password of the entry, if {@code null}, the server will
   *          generate a new password and send it back to this client.
   * @param responseTimeoutMillis
   *          maximum time (ms) to wait for a response from the server.
   * 
   * @throws LDAPException
   * @throws PasswordModifyExtendedOperationFailedException
   * 
   */
  public void changePassword(final DN distinguishedName, final String existingPassword,
      final String newPassword, final int responseTimeoutMillis) throws LDAPException,
      PasswordModifyExtendedOperationFailedException {
    ensureNotNullWithMessage(distinguishedName, "DN was null");
    ensureNotNullWithMessage(existingPassword, "existingPassword was null");
    ensureNotNullWithMessage(newPassword, "newPassword was null");

    /*
     * Check the the server supports the password modify extended request.
     */
    final String oid = PasswordModifyExtendedRequest.PASSWORD_MODIFY_REQUEST_OID;
    if (!SupportedFeature.isExtendedOperationSupported(ldapConnection, oid)) {
      throw new InvalidPrivledgesException();
    }

    /*
     * Create the password modify extended request. The extended request will
     * operate on the authentication identity of the existing connection.
     */
    final PasswordModifyExtendedRequest passwordModifyExtendedRequest = new PasswordModifyExtendedRequest(
        existingPassword, newPassword);
    passwordModifyExtendedRequest.setResponseTimeoutMillis(responseTimeoutMillis);

    /*
     * Send the request to the server:
     */
    final PasswordModifyExtendedResult extendedResult = (PasswordModifyExtendedResult) ldapConnection
        .processExtendedOperation(passwordModifyExtendedRequest);

    /*
     * Examine the results:
     */
    final ResultCode resultCode = extendedResult.getResultCode();
    System.out.println("Result CODE: " + extendedResult.getResultCode());
    if (!resultCode.equals(ResultCode.SUCCESS)) {
      throw new PasswordModifyExtendedOperationFailedException(resultCode);
    }
  }

  /**
   * The LDAP connection supplied by the client
   */
  private final LDAPConnection ldapConnection;
}
