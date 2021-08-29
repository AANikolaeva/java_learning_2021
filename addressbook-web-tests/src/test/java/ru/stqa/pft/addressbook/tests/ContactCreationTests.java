package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase{

  @Test(enabled = false)
  public void testContactCreation() {
    app.contact().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Ivan").withLastname("Testing").withAddress("Omsk")
            .withEmail("item@test.ru").withEmail2("test2@mail.ru").withEmail3("tete@test.ru")
            .withPhonehome("25-00").withPhonemobile("8-56-45-98-55").withPhonework("223-563").withGroup("test_0");
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) ->c.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadContactCreation() {
    app.contact().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Ivan'").withLastname("Testing").withAddress("Omsk")
            .withEmail("item@test.ru").withEmail2("test2@mail.ru").withEmail3("tete@test.ru")
            .withPhonehome("25-00").withPhonemobile("8-56-45-98-55").withPhonework("223-563").withGroup("test_0");
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }

}
