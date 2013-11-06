package com.addicks.helpdesk.service.ldap;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.ldap.ExtendedRequest;
import javax.naming.ldap.LdapContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.ContextExecutor;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import com.addicks.helpdesk.domain.ldap.ModifyPasswordRequest;

@Service
public class PasswordDAO implements PasswordDAOInterface {

  @Autowired
  private LdapTemplate ldapTemplate;

  @Override
  public void changeUserPassword(final String dn, final String newPassword) {
    if (dn == null || newPassword == null) {
      throw new IllegalArgumentException("Dn and new password cannot be null.");
    }

    ldapTemplate.executeReadOnly(new ContextExecutor() {
      @Override
      public Object executeWithContext(final DirContext ctx) throws NamingException {
        if (!(ctx instanceof LdapContext)) {
          throw new IllegalArgumentException("Extended operations require LDAPv3 - "
              + "Context must be of type LdapContext");
        }
        LdapContext ldapContext = (LdapContext) ctx;
        ExtendedRequest er = new ModifyPasswordRequest(dn, newPassword);
        return ldapContext.extendedOperation(er);
      }
    });

  }

}
