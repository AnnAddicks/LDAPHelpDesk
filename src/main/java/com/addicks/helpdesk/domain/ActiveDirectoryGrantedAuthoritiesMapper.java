package com.addicks.helpdesk.domain;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;

public class ActiveDirectoryGrantedAuthoritiesMapper implements GrantedAuthoritiesMapper {

  // TODO This string is used on the LDAP side. we may need to pull this out and
  // make it a configurable property per site.
  private String ROLE_ADMIN = "Domain Admins";

  public ActiveDirectoryGrantedAuthoritiesMapper() {
  }

  @Override
  public Collection<? extends GrantedAuthority> mapAuthorities(
      final Collection<? extends GrantedAuthority> authorities) {
    Set<UserRole> roles = EnumSet.noneOf(UserRole.class);

    for (GrantedAuthority authority : authorities) {
      // authority.getAuthority() returns the role in LDAP nomenclature
      if (ROLE_ADMIN.equals(authority.getAuthority())) {
        roles.add(UserRole.ROLE_ADMIN);
      }
    }
    return roles;
  }

}
