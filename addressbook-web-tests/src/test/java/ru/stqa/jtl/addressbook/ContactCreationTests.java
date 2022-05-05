package ru.stqa.jtl.addressbook;

import org.testng.annotations.*;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    gotoContactCreationPage();
    fillContactCreationForm(new ContactData("test name", "test middle name", "test last name", "test address", "89112233444", "test@test.ru"));
    sumitContactCreationForm();
    gotoHomePage();
  }

}
