package com.addicks.helpdesk.service.ldap;

import static org.junit.Assert.assertEquals;
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
  private ContactDAO contactDAO;

  @Test
  public void getAllContactNamesShouldFindNames() {
    List<String> allContactNames = contactDAO.getAllContactNames();

    assertNotNull(allContactNames);
    assertTrue(allContactNames.contains("Administrator"));
    assertTrue(allContactNames.contains("Guest"));
    assertTrue(allContactNames.contains("Brian Addicks"));
  }

  @Test
  public void findUserByLastNameShouldReturnNull() {
    assertNull(contactDAO.findUserByLastName(null));
  }

  @Test
  public void findUserByLastNameShouldReturnEmptyList() {
    List<AppUser> users = contactDAO.findUserByLastName("asdfjlksjfdldsjlkfjds");

    assertNotNull(users);
    assertTrue(users.isEmpty());
  }

  @Test
  public void findUserByLastNameShouldReturnAppUser() {
    AppUser user = new AppUser();
    user.setDescription(null);
    user.setLastName("Addicks");
    user.setFullName("Brian Addicks");
    user.setLastResetPassword(null);

    List<AppUser> users = contactDAO.findUserByLastName("Addicks");

    assertTrue(users.size() == 1);
    assertEquals(user, users.get(0));

  }

}
