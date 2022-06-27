package ru.stqa.jtl.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.jtl.mantis.model.UserData;

public class ResetPasswordHelper extends HelperBase{

    public ResetPasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void loginAdmin(String username, String password){
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }

    public void start(UserData selectedUser){
        click(By.className("manage-menu-link"));
        click(By.xpath("//a[text()='Manage Users']"));
        click(By.xpath("//a[text()='" + selectedUser.getUsername() + "']"));
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void finish(String confirmationLink, String newPassword) {
        wd.get(confirmationLink);
        type( By.name("password"), newPassword);
        type( By.name("password_confirm"), newPassword);
        click(By.cssSelector("input[value='Update User']"));
    }
}
