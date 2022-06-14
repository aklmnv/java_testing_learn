package ru.stqa.jtl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jtl.addressbook.model.ContactData;
import ru.stqa.jtl.addressbook.model.GroupData;

public class ContactAddToGroupTests extends TestBase{

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
    public void testContactAddToGroup() {
        app.goTo().homePage();
        ContactData selectedContact = app.db().contacts().stream().iterator().next();
        if (selectedContact.getGroups().equals(app.db().groups())){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
            app.goTo().homePage();
        }
        app.contact().selectContractById(selectedContact.getId());
        GroupData selectedGroup = app.group().selectGroupCanAddContact(selectedContact, app.db().groups());
        app.contact().selectGroupToAdd(selectedGroup);
        app.contact().addContactToGroup();
        Assert.assertTrue(app.db().getContractById(selectedContact.getId()).getGroups().isPresented(selectedGroup));
    }
}
