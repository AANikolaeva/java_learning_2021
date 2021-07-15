package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.СontactData;

public class ContactHelper extends HeplerBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() { click(By.linkText("home page"));
  }

  public void fillContactForm(СontactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"),contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("home"), contactData.getPhonehome());
    type(By.name("mobile"), contactData.getPhonemobile());
    type(By.name("work"), contactData.getPhonework());
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

}
