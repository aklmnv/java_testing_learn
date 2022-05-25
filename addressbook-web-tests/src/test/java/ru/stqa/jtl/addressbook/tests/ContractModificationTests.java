package ru.stqa.jtl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jtl.addressbook.model.ContactData;
import ru.stqa.jtl.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContractModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePrecondishions(){
        app.goTo().homePage();
        if (app.contact().list().size() == 0){
            app.goTo().groupPage();
            if (!app.group().isThereAGroup()){
                app.group().create(new GroupData().withName("test1"));
            }
            app.goTo().contactCreationPage();
            app.contact().create(new ContactData().withFirstName("test name").withMiddleName("test middle name").withLastName("test last name").withAddress("test address").withHomePhone("89112233444").withEmail("test@test.ru").withGroup("test1"));
        }
    }

    @Test
    public void testContractModification(){
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData().withFirstName("test name").withMiddleName("test middle name").withLastName("test last name").withAddress("test address").withHomePhone("89112233444").withEmail("test@test.ru");
        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
