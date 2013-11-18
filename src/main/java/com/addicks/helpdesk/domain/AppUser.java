package com.addicks.helpdesk.domain;

import java.util.Calendar;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class AppUser {

  /**
     */
  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(style = "M-")
  private Calendar lastResetPassword;

  private String fullName;

  private String lastName;

  private String description;

  private String uid;

  public AppUser() {

  }

  public Calendar getLastResetPassword() {
    return lastResetPassword;
  }

  public void setLastResetPassword(final Calendar lastResetPassword) {
    this.lastResetPassword = lastResetPassword;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(final String fullName) {
    this.fullName = fullName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(final String uid) {
    this.uid = uid;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((lastResetPassword == null) ? 0 : lastResetPassword.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AppUser other = (AppUser) obj;
    if (description == null) {
      if (other.description != null)
        return false;
    }
    else if (!description.equals(other.description))
      return false;
    if (fullName == null) {
      if (other.fullName != null)
        return false;
    }
    else if (!fullName.equals(other.fullName))
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    }
    else if (!lastName.equals(other.lastName))
      return false;
    if (lastResetPassword == null) {
      if (other.lastResetPassword != null)
        return false;
    }
    else if (!lastResetPassword.equals(other.lastResetPassword))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "AppUser [lastResetPassword=" + lastResetPassword + ", fullName=" + fullName
        + ", lastName=" + lastName + ", description=" + description + ", uid=" + uid + "]";
  }

}
