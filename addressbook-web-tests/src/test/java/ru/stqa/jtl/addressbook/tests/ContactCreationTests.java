package ru.stqa.jtl.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.jtl.addressbook.model.ContactData;
import ru.stqa.jtl.addressbook.model.Contacts;
import ru.stqa.jtl.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().groupPage();
    if (!app.group().isThereAGroupWithName("test1")){
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().contactCreationPage();
    ContactData contact = new ContactData().withFirstName("test name").withMiddleName("test middle name").withLastName("test last name").withAddress("test address").withHomePhone("89112233444").withEmail("test@test.ru").withGroup("test1");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}
