package ru.stqa.jtl.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.jtl.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void sumitContactCreationForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"),contactData.getAddress());
        type(By.name("home"),contactData.getHomePhone());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }else{
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContract() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedContracts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void closeConfirmAlert() {
        wd.switchTo().alert().accept();
    }

    public void initContractModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContractModification() {
        click(By.name("update"));
    }

    public void createContact(ContactData contactData) {
        fillContactForm(contactData, true);
        sumitContactCreationForm();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
}
