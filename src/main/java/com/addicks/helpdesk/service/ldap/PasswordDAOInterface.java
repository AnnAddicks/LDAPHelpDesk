package com.addicks.helpdesk.service.ldap;

public interface PasswordDAOInterface {

  public void changeUserPassword(String dn, String newPassword);

  public boolean authenticate(String dn, String password);
}
