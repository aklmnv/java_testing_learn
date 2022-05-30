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
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        attach(By.name("photo"), contactData.getPhoto());

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
        contactCache = null;
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements){
            List<WebElement> variables = element.findElements(By.tagName("td"));
            String lastName = variables.get(1).getText();
            String name = variables.get(2).getText();
            int id = Integer.parseInt(variables.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String allPhones = variables.get(5).getText();
            String allEmails = variables.get(4).getText();
            String address = variables.get(3).getText();
            contactCache.add(new ContactData().withId(id).withFirstName(name).withLastName(lastName).withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
        }
        return new Contacts(contactCache);
    }

    public void modify(ContactData contact) {
        initContractModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContractModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contract) {
        selectContractById(contract.getId());
        deleteSelectedContracts();
        contactCache = null;
        closeConfirmAlert();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContractModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail2(email2).withEmail(email).withEmail3(email3).withAddress(address);
    }
}
