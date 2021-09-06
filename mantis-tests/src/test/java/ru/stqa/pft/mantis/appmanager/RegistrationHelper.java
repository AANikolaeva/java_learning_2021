package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class RegistrationHelper {

  private final ApplicationManager app;
  private WebDriver wd;

  public RegistrationHelper(ApplicationManager app) {
    this.app = app;
    wd = app.getDriver();
  }
//
  public void start(String username, String email) {
    wd.get(app.getProperty("web.BaseURL") + "/signup_page.php");
//    type(By.name("username"), username);
//    type(By.name("email"), email);
//    click(By.cssSelector("input[type='submit']"));
  }
}
