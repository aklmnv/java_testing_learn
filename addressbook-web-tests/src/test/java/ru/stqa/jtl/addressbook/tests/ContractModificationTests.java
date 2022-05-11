package ru.stqa.jtl.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jtl.addressbook.model.ContactData;
import ru.stqa.jtl.addressbook.model.GroupData;

public class ContractModificationTests extends TestBase{

    @Test
    public void testContractModification(){
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoGroupPage();
            if (!app.getGroupHelper().isThereAGroup()){
                app.getGroupHelper().createGroup(new GroupData("test1", null, null));
            }
            app.getNavigationHelper().gotoContactCreationPage();
            app.getContactHelper().createContact(new ContactData("test name", "test middle name", "test last name", "test address", "89112233444", "test@test.ru", "test1"));
        }
        app.getContactHelper().initContractModification();
        app.getContactHelper().fillContactForm(new ContactData("test1 name", "test1 middle name", "test1 last name", "test1 address", "89112233444", "test1@test.ru", null), false);
        app.getContactHelper().submitContractModification();
        app.getContactHelper().returnToHomePage();
    }
}
