package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.СontactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new СontactData("Vanya", "Test", "Tara",
              "i@test.ru", "test@mail.ru", "te@test.ru",
              "24-00", "8-56-98-55", "223-563", "test_0"), false);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().accessAlert();
    app.getNavigationHelper().returnToHomePage();
  }
}
