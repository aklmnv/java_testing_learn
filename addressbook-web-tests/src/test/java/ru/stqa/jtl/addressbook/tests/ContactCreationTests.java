package ru.stqa.jtl.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.jtl.addressbook.model.ContactData;
import sun.font.TrueTypeFont;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoContactCreationPage();
    app.getContactHelper().fillContactForm(new ContactData("test name", "test middle name", "test last name", "test address", "89112233444", "test@test.ru", "test1"), true);
    app.getContactHelper().sumitContactCreationForm();
    app.getContactHelper().returnToHomePage();
  }

}
