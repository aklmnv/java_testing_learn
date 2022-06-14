package ru.stqa.jtl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jtl.addressbook.model.ContactData;
import ru.stqa.jtl.addressbook.model.GroupData;

public class ContactDeleteFromGroupTests extends TestBase{

    @BeforeMethod
    public void ensurePrecondishions(){
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        if (app.db().contacts().size() == 0){
            app.goTo().contactCreationPage();
            app.contact().create(new ContactData().withFirstName("test name").withMiddleName("test middle name").withLastName("test last name").withAddress("test address").withHomePhone("89112233444").withEmail("test@test.ru").withMobilePhone("4-3837-98787").withWorkPhone("76788").withEmail2("aaaa").withEmail3("ghfdtrsr"));
        }
    }

    @Test
    public void testContactDeleteFromGroup() {
        GroupData selectedGroup = app.db().groups().stream().iterator().next();
        ContactData selectedContact = app.contact().selectContactToAddToGroup(selectedGroup, app.db().contacts());
        app.goTo().homePage();
        app.contact().selectGroupToView(selectedGroup);
        app.contact().selectContractById(selectedContact.getId());
        app.contact().deleteContactFromGroup();
        Assert.assertFalse(app.db().getContractById(selectedContact.getId()).getGroups().isPresented(selectedGroup));
    }
}
