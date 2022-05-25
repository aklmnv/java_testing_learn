package ru.stqa.jtl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.jtl.addressbook.model.ContactData;
import ru.stqa.jtl.addressbook.model.GroupData;

import java.util.Set;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().groupPage();
    if (!app.group().isThereAGroupWithName("test1")){
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    app.goTo().contactCreationPage();
    ContactData contact = new ContactData().withFirstName("test name").withMiddleName("test middle name").withLastName("test last name").withAddress("test address").withHomePhone("89112233444").withEmail("test@test.ru").withGroup("test1");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);

    Assert.assertEquals(before, after);
  }

}
