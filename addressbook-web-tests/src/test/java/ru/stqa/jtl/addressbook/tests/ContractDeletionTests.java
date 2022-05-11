package ru.stqa.jtl.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jtl.addressbook.model.ContactData;
import ru.stqa.jtl.addressbook.model.GroupData;

public class ContractDeletionTests extends TestBase{

    @Test
    public void testContractDeletion(){
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoGroupPage();
            if (!app.getGroupHelper().isThereAGroup()){
                app.getGroupHelper().createGroup(new GroupData("test1", null, null));
            }
            app.getNavigationHelper().gotoContactCreationPage();
            app.getContactHelper().createContact(new ContactData("test name", "test middle name", "test last name", "test address", "89112233444", "test@test.ru", "test1"));
        }
        app.getContactHelper().selectContract();
        app.getContactHelper().deleteSelectedContracts();
        app.getContactHelper().closeConfirmAlert();
    }
}
