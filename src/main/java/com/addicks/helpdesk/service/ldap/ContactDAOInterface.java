package com.addicks.helpdesk.service.ldap;

import java.util.List;

import com.addicks.helpdesk.domain.AppUser;

public interface ContactDAOInterface {
  public List<String> getAllContactNames();

  public List<AppUser> findUserByLastName(String lastName);
}
