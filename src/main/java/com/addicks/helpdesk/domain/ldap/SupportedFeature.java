package com.addicks.helpdesk.domain.ldap;

import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPInterface;
import com.unboundid.ldap.sdk.RootDSE;
import com.unboundid.util.Validator;

public abstract class SupportedFeature {

  /**
   * Checks that the request control named by the specified {@code oid} is
   * supported by the server.
   * 
   * @param conn
   *          a connection to the LDAP server
   * @param controlOID
   *          The OID of a request control to determine if the server supports.
   */
  public static boolean isControlSupported(final LDAPInterface conn, final String controlOID) {
    Validator.ensureNotNullWithMessage(conn, "conn was null.");
    Validator.ensureNotNullWithMessage(controlOID, "controlOID was null.");

    final RootDSE rootDSE;
    try {
      rootDSE = conn.getRootDSE();
      return rootDSE.supportsControl(controlOID);
    }
    catch (LDAPException e) {
      // TODO exception handling
    }
    return false;
  }

  /**
   * Checks that the extended operation named by the specified {@code oid} is
   * supported by the server.
   * 
   * @param extensionOID
   *          The OID of an extended operation to determine if the server
   *          supports.
   */
  public static boolean isExtendedOperationSupported(final LDAPInterface conn,
      final String extensionOID) {
    Validator.ensureNotNullWithMessage(conn, "conn was null.");
    Validator.ensureNotNullWithMessage(extensionOID, "extensionOID was null.");

    final RootDSE rootDSE;
    try {
      rootDSE = conn.getRootDSE();
      return rootDSE.supportsExtendedOperation(extensionOID);
    }
    catch (LDAPException e) {
      // TODO exception handling
    }
    return false;
  }

}
