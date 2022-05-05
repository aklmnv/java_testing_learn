package ru.stqa.jtl.addressbook.tests;

import org.testng.annotations.Test;

public class ContractDeletionTests extends TestBase{

    @Test
    public void testContractDeletion(){
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContract();
        app.getContactHelper().deleteSelectedContracts();
        app.getContactHelper().closeConfirmAlert();
    }
}
