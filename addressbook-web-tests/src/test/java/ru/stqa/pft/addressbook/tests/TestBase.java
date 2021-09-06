package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty(("browser"), BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

  //сравнение данных из БД и данных со страницы пользователя
  //из БД берем только id и name, потому что на странице пользователя только эта информация
  // информацию из БД объединяем в поток(stream), выбираем, что надо, а потом создаем коллекцию выбранных данных
  public void verifyGroupListInUI() {
    if(Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      MatcherAssert.assertThat(uiGroups, CoreMatchers.equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getId()).withName(g.getName())).collect(Collectors.toSet())));
    }
  }

  public void verifyContactListInUI() {
    if(Boolean.getBoolean("verifyUI")) {
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().all();
      MatcherAssert.assertThat(uiContacts, CoreMatchers.equalTo(dbContacts.stream()
              .map((c) -> new ContactData().withId(c.getId()).withFirstname(c.getFirstname())
                      .withLastname(c.getLastname()).withAddress(c.getAddress())).collect(Collectors.toSet())));
    }
  }

}
