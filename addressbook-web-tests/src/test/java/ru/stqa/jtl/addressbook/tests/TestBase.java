package ru.stqa.jtl.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.stqa.jtl.addressbook.appmanager.ApplicationManager;
import ru.stqa.jtl.addressbook.model.ContactData;
import ru.stqa.jtl.addressbook.model.Contacts;
import ru.stqa.jtl.addressbook.model.GroupData;
import ru.stqa.jtl.addressbook.model.Groups;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod
    public void testStart(Method m, Object[] p){
        logger.info("Start test " + m.getName() + " with parameters" + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void testStop(Method m){
        logger.info("Stop test " + m.getName());
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((c) -> new ContactData().withId(c.getId()).withFirstName(c.getFirstName()).withLastName(c.getLastName()).withAllPhones(c.getAllPhones()).withAddress(c.getAddress()).withAllEmails(c.getAllPhones()))
                    .collect(Collectors.toSet())));
        }
    }

}
