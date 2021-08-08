package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withFirstname("Ivan").withLastname("Testing").withAddress("Omsk")
            .withEmail("item@test.ru").withEmail2("test2@mail.ru").withEmail3("tete@test.ru")
            .withPhonehome("25-00").withPhonemobile("8-56-45-98-55").withPhonework("223-563").withGroup("test_0");;
    app.contact().create(contact, true);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byID = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before, after);
  }

}
