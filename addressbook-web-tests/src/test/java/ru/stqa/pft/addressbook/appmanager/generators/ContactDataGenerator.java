package ru.stqa.pft.addressbook.appmanager.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws Exception {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws Exception {
    List<ContactData> contacts = generaterContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    }
    else {
      System.out.println("Unrecodnized format " + format);
    }
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    System.out.println();

    try (Writer writer = new FileWriter(file)){
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getAddress(),
                contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
                contact.getPhonehome(), contact.getPhonemobile(), contact.getPhonework()));
      }
    }

  }

  private List<ContactData> generaterContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();

    File photo = new File("src/test/resources/123.png");
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData()
              .withFirstname(String.format("Ivan %s", i))
              .withLastname(String.format("Claus %s", i))
              .withAddress(String.format("Omsk %s", i))
              .withEmail(String.format("te@gmail.com %s", i))
              .withEmail2(String.format("t@mail.ru %s", i))
              .withEmail3(String.format("tet@mail.ru %s", i))
              .withPhonehome(String.format("22-22 %s", i))
              .withPhonemobile(String.format("8951-213 %s", i))
              .withPhonework(String.format("456-456 %s", i))
              .withPhoto(photo)
      );
    }
    return contacts;
  }

  private void saveAsJson(List<ContactData> contact, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contact);
    try (Writer writer = new FileWriter(file)){
      writer.write(json);
    }
  }


  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    xstream.alias("contact", ContactData.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }
}
