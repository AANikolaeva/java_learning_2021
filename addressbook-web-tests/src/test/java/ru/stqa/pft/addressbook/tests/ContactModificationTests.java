package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test(enabled = false)
  public void testContactModification() {
    app.getNavigationHelper().returnToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Ivan", "Testing", "Omsk, Omskaya st, 42",
              "ivanT@tast.ru", "testing@mail.ru", "test.t@test.ru",
              "23-11-0", "8-56-98-55", "223-456", "test_0"), false);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
//    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().editContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Vanya", "Test", "Tara",
            "i@test.ru", "test@mail.ru", "te@test.ru",
            "24-00", "8-56-98-55", "223-563", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().clickUpdateContact();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byID = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before, after);
  }
}
