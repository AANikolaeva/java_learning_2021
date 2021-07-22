package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.СontactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new СontactData("Ivan", "Testing", "Omsk, Omskaya st, 42",
              "ivanT@tast.ru", "testing@mail.ru", "test.t@test.ru",
              "23-11-0", "8-56-98-55", "223-456", "test_0"), false);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().editContactModification();
    app.getContactHelper().fillContactForm(new СontactData("Vanya", "Test", "Tara",
            "i@test.ru", "test@mail.ru", "te@test.ru",
            "24-00", "8-56-98-55", "223-563", null), false);
    app.getNavigationHelper().returnToHomePage();
  }
}
