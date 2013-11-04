package com.addicks.helpdesk.domain;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
  ROLE_ADMIN, ROLE_USER;

  @Override
  public String getAuthority() {
    return name();
  }
}
