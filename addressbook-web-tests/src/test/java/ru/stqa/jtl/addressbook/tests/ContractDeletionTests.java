package ru.stqa.jtl.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jtl.addressbook.model.ContactData;
import ru.stqa.jtl.addressbook.model.Contacts;
import ru.stqa.jtl.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContractDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePrecondishions(){
        app.goTo().homePage();
        if (app.db().contacts().size() == 0){
            app.goTo().groupPage();
            if (!app.group().isThereAGroupWithName("test1")){
                app.group().create(new GroupData().withName("test1"));
            }
            app.goTo().contactCreationPage();
            app.contact().create(new ContactData().withFirstName("test name").withMiddleName("test middle name").withLastName("test last name").withAddress("test address").withHomePhone("89112233444").withEmail("test@test.ru"));
        }
    }

    @Test
    public void testContractDeletion(){
        Contacts before = app.db().contacts();
        ContactData deletedContract = before.stream().iterator().next();
        app.contact().delete(deletedContract);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContract)));
        verifyContactListInUI();
    }
}
