package com.addicks.helpdesk.service.ldap;

import java.util.List;

public interface ContactDAOInterface {
  public List getAllContactNames();

  public List getContactDetails(String commonName);
}
