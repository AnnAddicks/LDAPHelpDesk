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
public class ContactDaoTest {

  @Autowired
  ContactDAO contactDAO;

  @Test
  public void test() {
    System.out.println("People: " + contactDAO.getAllContactNames());
  }

}
