package ru.stqa.jtl.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jtl.addressbook.model.ContactData;
import ru.stqa.jtl.addressbook.model.Contacts;
import ru.stqa.jtl.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContractModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePrecondishions(){
        app.goTo().homePage();
        if (app.contact().all().size() == 0){
            app.goTo().groupPage();
            if (!app.group().isThereAGroupWithName("test1")){
                app.group().create(new GroupData().withName("test1"));
            }
            app.goTo().contactCreationPage();
            app.contact().create(new ContactData().withFirstName("test name").withMiddleName("test middle name").withLastName("test last name").withAddress("test address").withHomePhone("89112233444").withEmail("test@test.ru").withGroup("test1").withMobilePhone("4-38137-9887").withWorkPhone("76788"));
        }
    }

    @Test
    public void testContractModification(){
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.stream().iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("test name").withMiddleName("test middle name").withLastName("test last name").withAddress("test address").withHomePhone("89112233444").withEmail("test@test.ru").withMobilePhone("4-3837-9887").withWorkPhone("767188").withEmail2("aaaa").withEmail3("ghfdtrsr");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}
