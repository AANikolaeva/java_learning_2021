package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Ivan Ivanich").withLastname("Test").withAddress("Tara")
              .withEmail("i@test.ru").withEmail2("test@mail.ru").withEmail3("te@test.ru")
              .withPhonehome("2400").withPhonemobile("8565").withPhonework("223"), false);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification() {
    File photo = new File("src/test/resources/123.png");
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Vanya").withLastname("Test").withAddress("Tara")
            .withEmail("i@test.ru").withEmail2("test@mail.ru").withEmail3("te@test.ru")
            .withPhonehome("2400").withPhonemobile("8565").withPhonework("223").withPhoto(photo);
    app.goTo().homePage();
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }

}
