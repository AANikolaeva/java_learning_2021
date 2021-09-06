package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test 1"));
      app.contact().homePage();
    }
    if (app.db().contactAllGroups().size()==0 ){
      Contacts before = app.db().contacts();
      ContactData addGroupContact = before.iterator().next();
      app.contact().addGroupInContactById(addGroupContact);
      app.contact().homePage();
    }
  }

  @Test
  public void deleteContactFromGroupTest() {
    if (!app.contact().verifyFreeContact()){
      app.contact().verifyGroupContact();
    }

    Contacts before = app.db().contacts();
    Groups beforeInGroups = app.db().contactAllGroups();
    ContactData contact = before.iterator().next();
    app.contact().homePage();
    String getNameGroup = contact.getGroups().iterator().next().getName();
    app.contact().contactDeleteGroup(contact, getNameGroup);

    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before));
    Groups afterInGrous = app.db().contactAllGroups();
    assertThat((afterInGrous), equalTo(beforeInGroups));
  }
}