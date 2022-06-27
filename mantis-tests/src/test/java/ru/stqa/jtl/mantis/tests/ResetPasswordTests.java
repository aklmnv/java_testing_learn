package ru.stqa.jtl.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jtl.mantis.model.MailMessage;
import ru.stqa.jtl.mantis.model.UserData;
import ru.stqa.jtl.mantis.model.Users;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase{

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testResetUserPassword() throws IOException {
        long now = System.currentTimeMillis();
        UserData admin = app.db().getUserByUsername(app.getProperty("web.adminLogin"));
        Users allUsers = app.db().users().without(admin);
        UserData selectedUser = null;
        List<MailMessage> mailMessages;
        String confirmationLink = "";
        if (allUsers.size() < 1){
            selectedUser = new UserData().withEmail(String.format("user%s@localhost.localdomain", now)).withUsername(String.format("user%s", now));
            app.registration().start(selectedUser.getUsername(), selectedUser.getEmail());
            mailMessages =  app.mail().waitForMail(2, 10000);
            confirmationLink = findLinkInMail(mailMessages, selectedUser.getEmail());
            app.registration().finish(confirmationLink, "password");
        }else{
            selectedUser = allUsers.stream().iterator().next();
        }
        app.resetPassword().loginAdmin(admin.getUsername(), app.getProperty("web.adminPassword"));
        app.resetPassword().start(selectedUser);
        mailMessages =  app.mail().waitForMail(1, 10000);
        confirmationLink = findLinkInMail(mailMessages, selectedUser.getEmail());
        final String newPassword = "newPassword";
        app.resetPassword().finish(confirmationLink, newPassword);
        assertTrue(app.newSession().login(selectedUser.getUsername(), newPassword));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
