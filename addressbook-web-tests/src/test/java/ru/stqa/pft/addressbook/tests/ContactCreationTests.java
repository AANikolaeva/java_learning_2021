package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.СontactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoContactCreationPage();
    app.getContactHelper().fillContactForm(new СontactData("Ivan", "Testing", "Omsk, Omskaya st, 42",
            "ivanT@tast.ru", "testing@mail.ru", "test.t@test.ru",
            "23-11-0", "8-56-98-55", "223-456"));
    app.getContactHelper().returnToHomePage();
  }

}
