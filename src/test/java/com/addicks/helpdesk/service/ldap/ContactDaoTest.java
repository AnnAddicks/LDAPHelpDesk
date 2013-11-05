package com.addicks.helpdesk.service.ldap;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.addicks.helpdesk.domain.AppUser;

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
public class ContactDaoTest {

  @Autowired
  ContactDAO contactDAO;

  @Test
  public void shouldFindNames() {
    List<String> allContactNames = contactDAO.getAllContactNames();

    assertNotNull(allContactNames);
    assertTrue(allContactNames.contains("Administrator"));
    assertTrue(allContactNames.contains("Guest"));
    assertTrue(allContactNames.contains("Brian Addicks"));
  }

  @Test
  public void shouldReturnNull() {
    assertNull(contactDAO.findUserByLastName(null));
  }

  @Test
  public void shouldReturnEmptyList() {
    List<AppUser> users = contactDAO.findUserByLastName("asdfjlksjfdldsjlkfjds");

    assertNotNull(users);
    assertTrue(users.isEmpty());
  }

  @Test
  public void shouldReturnAppUser() {
    System.out.println("App User: " + contactDAO.findUserByLastName("Addicks"));
  }

}
