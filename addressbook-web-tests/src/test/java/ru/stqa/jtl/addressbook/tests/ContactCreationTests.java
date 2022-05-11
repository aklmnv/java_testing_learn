package ru.stqa.jtl.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.jtl.addressbook.model.ContactData;
import ru.stqa.jtl.addressbook.model.GroupData;
import sun.font.TrueTypeFont;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getNavigationHelper().gotoContactCreationPage();
    app.getContactHelper().createContact(new ContactData("test name", "test middle name", "test last name", "test address", "89112233444", "test@test.ru", "test1"));
  }

}
