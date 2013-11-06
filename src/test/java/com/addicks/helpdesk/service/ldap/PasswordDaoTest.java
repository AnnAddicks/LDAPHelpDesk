package com.addicks.helpdesk.service.ldap;

import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
public class PasswordDaoTest {

  @Autowired
  private PasswordDAO passwordDao;

  private SecureRandom random = new SecureRandom();

  @Test(expected = IllegalArgumentException.class)
  public void changeUserPasswordShouldThrowException() {
    passwordDao.changeUserPassword(null, null);
  }

  @Test
  public void changeUserPasswordShouldChangePassword() {
    // "!@#$%^&*()_+=~" +
    String newPassword = new BigInteger(130, random).toString(32);
    String dn = "CN=Zoe Washburne";

    passwordDao.changeUserPassword(dn, newPassword);

    assertTrue(passwordDao.authenticate(dn, newPassword));
  }
}
