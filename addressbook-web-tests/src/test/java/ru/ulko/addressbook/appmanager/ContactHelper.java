package ru.ulko.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.ulko.addressbook.model.ContactData;
import ru.ulko.addressbook.model.Contacts;

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

    public void returnHomePage() {
        click(By.linkText("home page"));
    }

    public void create(ContactData contact) {
        initCreateContact();
        fillContactData(contact, true);
        submitCreationContact();
        returnHomePage();
    }

    public void select() {
        click(By.name("selected[]"));
    }

    public void selectById(int id){
        driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }


    public void delete() {
        select();
        deleteSelectedContact();
    }

    public void deleteById(ContactData deletedContact) {
        selectById(deletedContact.getId());
        deleteSelectedContact();
    }


    public void initModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void initModificationById(int id){
        driver.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void submitModificationContact() {
        WebElement parentElement = driver.findElement(By.cssSelector("form[action='edit.php']"));
        WebElement childElement = parentElement.findElement(By.cssSelector("input[value='Update']"));
        childElement.submit();
    }

    public void modify(ContactData contact) {
        initModification();
        fillContactData(contact, false);
        submitModificationContact();
        returnHomePage();
    }

    public void modifyById(ContactData oldContact, ContactData newContact) {
        initModificationById(oldContact.getId());
        fillContactData(newContact, false);
        submitModificationContact();
        returnHomePage();
    }


    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> rows = driver.findElements(By.tagName("tr"));

        for (int i = 1; i < rows.size(); i++) {
            int id = Integer.parseInt(rows.get(i).findElement(By.tagName("input")).getAttribute("value"));
            String firstName = rows.get(i).findElement(By.xpath("td[3]")).getText();
            String lastName = rows.get(i).findElement(By.xpath("td[2]")).getText();
            ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withGroup("test");
            contacts.add(contact);
        }
        return contacts;
    }



}
