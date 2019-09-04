package ru.ulko.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.ulko.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yulia on 20.08.2019.
 */
public class ContactHelper extends HelperBase {


    protected ContactHelper(WebDriver driver) {
        super(driver);
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

        if (creation == true) {
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return driver.findElements(By.name("selected[]")).size();
    }

    public void initCreateContact() {
        click(By.linkText("add new"));
    }

    private void submitCreationContact() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    private void returnHomePage() {
        click(By.linkText("home page"));
    }

    public void create(ContactData contact) {
        initCreateContact();
        fillContactData(contact, true);
        submitCreationContact();
        returnHomePage();
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }


    public void delete() {
        selectContact();
        deleteSelectedContact();
    }


    public void initModificationContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitModificationContact() {
        click(By.name("update"));
    }

    public void modify(ContactData contact) {
        initModificationContact();
        fillContactData(contact, false);
        submitModificationContact();
        returnHomePage();
    }


    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> rows = driver.findElements(By.tagName("tr"));

        for (int i = 1; i < rows.size(); i++) {
            int id = Integer.parseInt(rows.get(i).findElement(By.tagName("input")).getAttribute("value"));
            String firstName = rows.get(i).findElement(By.xpath("td[3]")).getText();
            String lastName = rows.get(i).findElement(By.xpath("td[2]")).getText();
            ContactData contact = new ContactData(id, firstName, lastName, null, null, null, null, null, null, null, null, "test");
            contacts.add(contact);
        }
        return contacts;
    }


}
