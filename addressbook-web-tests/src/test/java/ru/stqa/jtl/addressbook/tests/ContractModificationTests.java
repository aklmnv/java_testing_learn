package ru.stqa.jtl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jtl.addressbook.model.ContactData;
import ru.stqa.jtl.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContractModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePrecondishions(){
        app.goTo().homePage();
        if (app.contact().list().size() == 0){
            app.goTo().groupPage();
            if (!app.group().isThereAGroupWithName("test1")){
                app.group().create(new GroupData().withName("test1"));
            }
            app.goTo().contactCreationPage();
            app.contact().create(new ContactData().withFirstName("test name").withMiddleName("test middle name").withLastName("test last name").withAddress("test address").withHomePhone("89112233444").withEmail("test@test.ru").withGroup("test1"));
        }
    }

    @Test
    public void testContractModification(){
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.stream().iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("test name").withMiddleName("test middle name").withLastName("test last name").withAddress("test address").withHomePhone("89112233444").withEmail("test@test.ru");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }

}
