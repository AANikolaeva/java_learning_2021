package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HeplerBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoContactCreationPage() {
    click(By.linkText("add new"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"),contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("home"), contactData.getPhonehome());
    type(By.name("mobile"), contactData.getPhonemobile());
    type(By.name("work"), contactData.getPhonework());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
//    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void accessAlert() {
    wd.switchTo().alert().accept();
  }

  public void editContactModification(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public void clickNewContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void homePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public void create(ContactData сontact, boolean creation) {
    gotoContactCreationPage();
    fillContactForm(сontact, creation);
    clickNewContact();
    homePage();
    contactCache = null;
  }

  public void modify(ContactData contact) {
    editContactModification(contact.getId());
    fillContactForm(contact, false);
    clickUpdateContact();
    homePage();
  }

  public void delete(ContactData contact) throws InterruptedException {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    accessAlert();
    Thread.sleep(500);
    homePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

//  public Contacts all() {
//    Contacts contacts = new Contacts();
//    List<WebElement> elements = wd.findElements(By.name("entry"));
//    for (WebElement element : elements) {
//      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//      String firstname = element.findElement(By.xpath("td[3]")).getText();
//      String lastname = element.findElement(By.xpath("td[2]")).getText();
//      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
//      contacts.add(contact);
//    }
//    return contacts;
//  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name(("entry")));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = cells.get(1).getText();
      String lastname = cells.get(2).getText();
      String[] phones = cells.get(5).getText().split("\n");
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(null)
              .withEmail(null).withEmail2(null).withEmail3(null)
              .withPhonehome(phones[0]).withPhonemobile(phones[1]).withPhonework(phones[2]));
    }
    return contactCache;
  }

  public void clickUpdateContact() {
    click(By.name("update"));
  }

}
