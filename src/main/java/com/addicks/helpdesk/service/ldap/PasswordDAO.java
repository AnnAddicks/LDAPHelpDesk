package com.addicks.helpdesk.service.ldap;

import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Service;

@Service
public class PasswordDAO implements PasswordDAOInterface {

  @Autowired
  private LdapTemplate ldapTemplate;

  @Override
  public void changeUserPassword(final String dn, final String newPassword) {
    if (dn == null || newPassword == null) {
      throw new IllegalArgumentException("Dn and new password cannot be null.");
    }

    // ldapTemplate.executeReadOnly(new ContextExecutor() {
    // @Override
    // public Object executeWithContext(final DirContext ctx) throws
    // NamingException {
    // if (!(ctx instanceof LdapContext)) {
    // throw new
    // IllegalArgumentException("Extended operations require LDAPv3 - "
    // + "Context must be of type LdapContext");
    // }
    // LdapContext ldapContext = (LdapContext) ctx;
    // ExtendedRequest er = new ModifyPasswordRequest(dn, newPassword);
    // return ldapContext.extendedOperation(er);
    // }
    // });

    ModificationItem repitem = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
        new BasicAttribute("unicodepwd", encodePassword(newPassword)));

    ldapTemplate.modifyAttributes(dn, new ModificationItem[] { repitem });

  }

  private String encodePassword(final String password) {

    String quotedPassword = "\"" + password + "\"";
    char unicodePwd[] = quotedPassword.toCharArray();
    byte pwdArray[] = new byte[unicodePwd.length * 2];

    for (int i = 0; i < unicodePwd.length; i++) {
      pwdArray[i * 2 + 1] = (byte) (unicodePwd[i] >>> 8);
      pwdArray[i * 2 + 0] = (byte) (unicodePwd[i] & 0xff);
    }

    return new String(pwdArray);
  }

  @Override
  public boolean authenticate(final String dn, final String password) {
    AndFilter filter = new AndFilter();
    filter.and(new EqualsFilter("ou", "users")).and(new EqualsFilter("cn", dn));
    return ldapTemplate.authenticate("", filter.toString(), password);

    // return ldapTemplate.authenticate("", "(dn=" + dn + ")", password);
  }
}
