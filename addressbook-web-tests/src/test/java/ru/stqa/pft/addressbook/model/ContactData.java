package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String lastname;
  private String address;
  private String email;
  private String email2;
  private String email3;
  private String allmails;

  private String phonehome;
  private String phonemobile;
  private String phonework;
  private String allphones;

  private String group;

  public String getAllphones() {
    return allphones;
  }

  public ContactData withAllphones(String allphones) {
    this.allphones = allphones;
    return this;
  }

  public String getAllmails() {
    return allmails;
  }

  public ContactData withAllmails(String allmails) {
    this.allmails = allmails;
    return this;
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

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withPhonehome(String phonehome) {
    this.phonehome = phonehome;
    return this;
  }

  public ContactData withPhonemobile(String phonemobile) {
    this.phonemobile = phonemobile;
    return this;
  }

  public ContactData withPhonework(String phonework) {
    this.phonework = phonework;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
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
