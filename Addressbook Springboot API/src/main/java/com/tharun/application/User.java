package com.tharun.application;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity // This tells Hibernate to make a table out of this class

public class User implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id
  @Column(unique = true)
  @NotEmpty(message = "EmailError : email is required and can not be null or empty.")
  @NotNull(message = "EmailError : email is required and can not be null or empty.")
  @NotBlank(message = "EmailError : email is required and can not be null or empty.")
  @Email(message = "EmailError : Not a valid email.")
  private String email;
  
  @NotEmpty(message = "FirstNameError : firstName is required and can not be null or empty.")
  @NotNull(message = "FirstNameError : firstName is required and can not be null or empty.")
  @NotBlank(message = "FirstNameError : firstName is required and can not be null or empty.")
  private String firstname;

  @NotEmpty(message = "LastNameError : lastName is required and can not be null or empty.")
  @NotNull(message = "LastNameError : lastName is required and can not be null or empty.")
  @NotBlank(message = "LastNameError : lastName is required and can not be null or empty.")
  private String lastname;

  @Column(unique = true)
  @NotEmpty(message = "ContactError : contact is required and can not be null or empty.")
  @NotNull(message = "ContactError : contact is required and can not be null or empty.")
  @NotBlank(message = "ContactError : contact is required and can not be null or empty.")
  private String contact;
  
  // address can be optional so...
  @NotEmpty(message = "AddressError : address is required and can not be null or empty.")
  @NotNull(message = "AddressError : address is required and can not be null or empty.")
  @NotBlank(message = "AddressError : address is required and can not be null or empty.")
  private String address;
  
public User(String firstname, String lastname, String contact, String email, String address) {
	super();
	this.firstname = firstname;
	this.lastname = lastname;
	this.contact = contact;
	this.email = email;
	this.address = address;
}
public User() {
	
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
}