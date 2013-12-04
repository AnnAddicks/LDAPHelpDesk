package com.addicks.helpdesk.service.ldap;

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
    String newPassword = "!@#$%^&*()_+=~" + new BigInteger(100, random).toString(32);
    String dn = "CN=Zoe Washburne,OU=normal,OU=users,OU=structure";
    System.out.println("New password: " + newPassword);
    passwordDao.changeUserPassword(dn, newPassword);

  }
}
