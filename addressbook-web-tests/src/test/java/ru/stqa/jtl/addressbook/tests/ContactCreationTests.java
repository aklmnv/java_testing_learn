package ru.stqa.jtl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.jtl.addressbook.model.ContactData;
import ru.stqa.jtl.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().groupPage();
    if (!app.group().isThereAGroup()){
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    app.goTo().contactCreationPage();
    ContactData contact = new ContactData().withFirstName("test name").withMiddleName("test middle name").withLastName("test last name").withAddress("test address").withHomePhone("89112233444").withEmail("test@test.ru").withGroup("test1");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());

    contact.withId(after.stream().max(byId).get().getId());
    before.add(contact);

    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
