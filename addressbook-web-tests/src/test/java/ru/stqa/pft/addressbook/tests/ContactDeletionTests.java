package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Ivan Ivanich").withLastname("Test").withAddress("Tara")
              .withEmail("i@test.ru").withEmail2("test@mail.ru").withEmail3("te@test.ru")
              .withPhonehome("2400").withPhonemobile("8565").withPhonework("223"), false);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactDeletion() throws InterruptedException {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.goTo().homePage();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, CoreMatchers.equalTo(before.without(deletedContact)));
    verifyContactListInUI();
  }

}
