package ru.stqa.jtl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jtl.addressbook.model.ContactData;
import ru.stqa.jtl.addressbook.model.GroupData;

import java.util.Set;

public class ContractDeletionTests extends TestBase{

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
    public void testContractDeletion(){
        Set<ContactData> before = app.contact().all();
        ContactData deletedContract = before.stream().iterator().next();
        app.contact().delete(deletedContract);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContract);
        Assert.assertEquals(before, after);
    }
}
