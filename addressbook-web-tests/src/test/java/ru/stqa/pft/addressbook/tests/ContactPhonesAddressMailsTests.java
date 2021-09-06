package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhonesAddressMailsTests extends TestBase {

  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Ivan Ivanich").withLastname("Test").withAddress("Tara")
              .withEmail("i@test.ru").withEmail2("test@mail.ru").withEmail3("te@test.ru")
              .withPhonehome("2400").withPhonemobile("8565").withPhonework("223"), false);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactPhonesAddressMails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllphones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat(contact.getAllmails(), equalTo(mergeMails(contactInfoFromEditForm)));
  }

  /*склеить номера телефонов в поток и разделить их \
  map - применить ко всем элементам функцию cleaned
  метод обратных проверок
  */

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getPhonehome(), contact.getPhonemobile(), contact.getPhonework())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhonesAddressMailsTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeMails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhonesAddressMailsTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  // метод для очищения номеров телефонов от "лишних" знаков для проверок
  // "\\s" - пробельные символы, "-" - тире, "()" - скобки

  public static String cleaned(String text) {
    return text.replaceAll("\\s", "").replaceAll("[-()]", "");

  }
}
