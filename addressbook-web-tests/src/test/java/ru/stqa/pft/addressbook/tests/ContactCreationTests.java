package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void ContactCreationTests() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoContactCreationPage();
    ContactData contact = new ContactData("Ivan", "Testing", "Omsk, Omskaya st, 42",
            "ivanT@tast.ru", "testing@mail.ru", "test.t@test.ru",
            "23-11-0", "8-56-98-55", "223-456", "test_0");
    app.getContactHelper().fillContactForm(contact, true);
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byID = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
