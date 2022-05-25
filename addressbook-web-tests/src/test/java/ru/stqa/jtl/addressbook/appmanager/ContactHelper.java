package ru.stqa.jtl.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.jtl.addressbook.model.ContactData;
import ru.stqa.jtl.addressbook.model.Contacts;

import java.util.List;

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

    private void selectContractById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "'")).click();
    }

    public void deleteSelectedContracts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void closeConfirmAlert() {
        wd.switchTo().alert().accept();
    }

    public void initContractModificationById(int id) {
        wd.findElement(By.xpath("//input[@value = '" + id + "']/../..//img[@alt='Edit']")).click();
    }

    public void submitContractModification() {
        click(By.name("update"));
    }

    public void create(ContactData contactData) {
        fillContactForm(contactData, true);
        sumitContactCreationForm();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements){
            List<WebElement> variables = element.findElements(By.tagName("td"));
            String lastName = variables.get(1).getText();
            String name = variables.get(2).getText();
            int id = Integer.parseInt(variables.get(0).findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstName(name).withLastName(lastName));
        }
        return contacts;
    }

    public void modify(ContactData contact) {
        initContractModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContractModification();
        returnToHomePage();
    }

    public void delete(ContactData contract) {
        selectContractById(contract.getId());
        deleteSelectedContracts();
        closeConfirmAlert();
    }

}
