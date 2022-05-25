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
                app.group().create(new GroupData("test1", null, null));
            }
            app.goTo().contactCreationPage();
            app.contact().create(new ContactData("test name", "test middle name", "test last name", "test address", "89112233444", "test@test.ru", "test1"));
        }
    }

    @Test
    public void testContractModification(){
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData("test1 name", "test1 middle name", "test1 last name", "test1 address", "89112233444", "test1@test.ru", null);
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
