package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
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

    assertThat(contact.getAllphones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  /*склеить номера телефонов в поток и разделить их \
  map - применить ко всем элементам функцию cleaned
  метод обратных проверок
  */

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getPhonehome(), contact.getPhonemobile(), contact.getPhonework())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }
  // метод для очищения номеров телефонов от "лишних" знаков для проверок
  // "\\s" - пробельные символы, "-" - тире, "()" - скобки

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");

  }
}
