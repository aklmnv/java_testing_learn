package ru.stqa.jtl.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.jtl.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.gotoContactCreationPage();
    app.fillContactCreationForm(new ContactData("test name", "test middle name", "test last name", "test address", "89112233444", "test@test.ru"));
    app.sumitContactCreationForm();
    app.gotoHomePage();
  }

}
