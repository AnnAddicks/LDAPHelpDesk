package com.addicks.helpdesk.service.ldap;

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
  PasswordDAO passwordDao;

  @Test(expected = IllegalArgumentException.class)
  public void changeUserPasswordShouldThrowException() {
    passwordDao.changeUserPassword(null, null);
  }

}
