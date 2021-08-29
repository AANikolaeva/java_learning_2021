package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Ivan Ivanich").withLastname("Test").withAddress("Tara")
              .withEmail("i@test.ru").withEmail2("test@mail.ru").withEmail3("te@test.ru")
              .withPhonehome("111").withPhonemobile("222").withPhonework("333").withGroup(null), false);
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getPhonehome(), equalTo(cleaned(contactInfoFromEditForm.getPhonehome())));
    assertThat(contact.getPhonemobile(), equalTo(cleaned(contactInfoFromEditForm.getPhonemobile())));
    assertThat(contact.getPhonework(), equalTo(cleaned(contactInfoFromEditForm.getPhonework())));
  }
  // метод для очищения номеров телефонов от "лишних" знаков для проверок
  // "\\s" - пробельные символы, "-" - тире, "()" - скобки

  public String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");

  }
}
