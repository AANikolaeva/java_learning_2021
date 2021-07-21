package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.СontactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().selectContact();
    app.getContactHelper().editContactModification();
    app.getContactHelper().fillContactForm(new СontactData("Vanya", "Test", "Tara",
            "i@test.ru", "test@mail.ru", "te@test.ru",
            "24-00", "8-56-98-55", "223-563", null), false);
    app.getContactHelper().returnToHomePage();
  }
}
