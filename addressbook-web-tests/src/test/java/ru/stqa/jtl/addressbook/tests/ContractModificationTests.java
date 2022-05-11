package ru.stqa.jtl.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jtl.addressbook.model.ContactData;

public class ContractModificationTests extends TestBase{

    @Test
    public void testContractModification(){
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().initContractModification();
        app.getContactHelper().fillContactForm(new ContactData("test1 name", "test1 middle name", "test1 last name", "test1 address", "89112233444", "test1@test.ru", null), false);
        app.getContactHelper().submitContractModification();
        app.getContactHelper().returnToHomePage();
    }
}
