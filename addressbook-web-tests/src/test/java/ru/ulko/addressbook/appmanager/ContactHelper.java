package ru.ulko.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.ulko.addressbook.model.ContactData;

/**
 * Created by yulia on 20.08.2019.
 */
public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void submitCreationContact() {
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]"));
    }


    public void initCreateContact() {
        click(By.linkText("add new"));
    }

    public void fillContactData(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("fax"), contactData.getFaxPhone());
        type(By.name("email"), contactData.getEmail1());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        if (creation == true){
            new Select(getDriver().findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else
        {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select all'])[1]/following::input[2]"));
    }

    public void initModificationContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitModificationContact() {
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Edit / add address book entry'])[1]/following::div[1]"));
    }

    public void createContact(ContactData contact) {
        initCreateContact();
        fillContactData(contact, true);
        submitCreationContact();
        returnHomePage();
    }

    public void returnHomePage() {
        click(By.linkText("home page"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return getDriver().findElements(By.name("selected[]")).size();
    }
}
