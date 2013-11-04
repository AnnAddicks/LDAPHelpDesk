package com.addicks.helpdesk.service.ldap;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.addicks.helpdesk.domain.exception.PasswordModifyExtendedOperationFailedException;
import com.unboundid.ldap.sdk.DN;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
public class ChangePasswordTest {

  @Test
  public void testNewChangePassword() throws LDAPException {
    ChangePassword changePassword = this.getChangePassword();
    assertNotNull(changePassword);
  }

  // @Test
  public void testChangePassword() throws LDAPException,
      PasswordModifyExtendedOperationFailedException {
    ChangePassword changePassword = this.getChangePassword();
    assertNotNull(changePassword);

    DN dn = new DN("CN=test,OU=normal,OU=users,OU=structure,DC=addicks,DC=us");
    changePassword.changePassword(dn, "BBekac42", "Under30", 1000);
  }

  private ChangePassword getChangePassword() throws LDAPException {
    LDAPConnection connection = new LDAPConnection("10.10.64.11", 389, "svc-ldap-bind",
        "8ffoIkCbOpfr");

    return ChangePassword.newChangePassword(connection);
  }

}
