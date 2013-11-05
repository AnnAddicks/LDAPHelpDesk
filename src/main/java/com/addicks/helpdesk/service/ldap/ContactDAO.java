package com.addicks.helpdesk.service.ldap;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

@Service
public class ContactDAO implements ContactDAOInterface {

  @Autowired
  private LdapTemplate ldapTemplate;

  @Override
  public List<String> getAllContactNames() {
    return ldapTemplate.search(query().where("objectclass").is("person"),
        new AttributesMapper<String>() {
          @Override
          public String mapFromAttributes(final Attributes attrs) throws NamingException {
            return (String) attrs.get("cn").get();
          }
        });
  }

}