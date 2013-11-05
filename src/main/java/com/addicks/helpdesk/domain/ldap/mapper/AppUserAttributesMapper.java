package com.addicks.helpdesk.domain.ldap.mapper;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

import com.addicks.helpdesk.domain.AppUser;

public class AppUserAttributesMapper implements AttributesMapper<AppUser> {

  @Override
  public AppUser mapFromAttributes(final Attributes attrs) throws NamingException {
    AppUser user = new AppUser();
    user.setFullName((String) attrs.get("cn").get());
    user.setLastName((String) attrs.get("sn").get());
    user.setDescription((String) attrs.get("description").get());
    return user;
  }
}
