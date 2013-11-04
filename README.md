LDAPHelpDesk
============
One file is excluded from this repository:  src/main/WEB-INF/spring/ldap.properties.  It needs the following fields:

 * ldap.dn
 * ldap.url

 The dn should be something like "test.org" instead of the LDAP syntax "dn=test, dn=org".  The url should start with ldap:// and include a :port at the end.