package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test 1"));
    }
  }

  @Test
  public void addContactInGroupTest() {
    // Берем список контактов и список групп до выполнения теста.
    // Выбираем случайный контакт
    // Выполняем сами шаги теста по добавлению контакта в группу
    // Сравниваем список контактов - что было и что стало
    // Сравниваем список групп - что было и что стало

    Contacts before = app.db().contacts();
    Groups beforeInGroups = app.db().contactAllGroups();
    ContactData addGroupContact = before.iterator().next();
    app.contact().addGroupInContactById(addGroupContact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before));
    Groups afterInGrous = app.db().contactAllGroups();
    assertThat((afterInGrous), equalTo(beforeInGroups));
  }
}

