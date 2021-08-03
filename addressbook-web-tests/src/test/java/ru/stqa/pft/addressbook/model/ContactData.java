package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

  private int id;
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String email;
  private final String email2;
  private final String email3;
  private final String phonehome;
  private final String phonemobile;
  private final String phonework;
  private String group;

  public ContactData(int id, String firstname, String lastname, String address, String email, String email2, String email3, String phonehome, String phonemobile, String phonework, String group) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
    this.email2 = email2;
    this.email3 = email3;
    this.phonehome = phonehome;
    this.phonemobile = phonemobile;
    this.phonework = phonework;
    this.group = group;
  }

  public ContactData(String firstname, String lastname, String address, String email, String email2, String email3, String phonehome, String phonemobile, String phonework, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
    this.email2 = email2;
    this.email3 = email3;
    this.phonehome = phonehome;
    this.phonemobile = phonemobile;
    this.phonework = phonework;
    this.group = group;
  }

  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getPhonehome() {
    return phonehome;
  }

  public String getPhonemobile() {
    return phonemobile;
  }

  public String getPhonework() {
    return phonework;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }
}
