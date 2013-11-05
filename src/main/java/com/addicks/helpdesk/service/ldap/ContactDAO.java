package com.addicks.helpdesk.service.ldap;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Service;

import com.addicks.helpdesk.domain.AppUser;

@Service
public class ContactDAO implements ContactDAOInterface {

  @Autowired
  private LdapTemplate ldapTemplate;

  @Value("${ldap.ldapStyleDn}")
  private String DN;

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

  @Override
  public List<AppUser> findUserByLastName(final String lastName) {
    if (lastName == null) {
      return null;
    }

    LdapQuery query = query().attributes("cn", "sn").where("objectclass").is("person").and("sn")
        .is(lastName);

    return ldapTemplate.search(query, new AttributesMapper() {
      @Override
      public Object mapFromAttributes(final Attributes attrs) throws NamingException {
        return attrs.get("cn").get();
      }
    });
  }
}
