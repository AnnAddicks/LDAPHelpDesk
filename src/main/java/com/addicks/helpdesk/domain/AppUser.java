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

  @Override
  public String toString() {
    return "AppUser [lastResetPassword=" + lastResetPassword + ", fullName=" + fullName
        + ", lastName=" + lastName + ", description=" + description + "]";
  }

}
