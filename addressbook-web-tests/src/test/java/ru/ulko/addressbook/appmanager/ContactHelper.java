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

    public int count() {
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
        contactCache = null;
    }

    private void selectById(int id){
        driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    private void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void deleteById(ContactData deletedContact) {
        selectById(deletedContact.getId());
        deleteSelectedContact();
        contactCache = null;
    }


    private void initModificationById(int id){
        driver.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    private void submitModificationContact() {
        driver.findElement(By.cssSelector("input[value='Update']")).submit();
    }


    public void modifyById(ContactData oldContact, ContactData newContact) {
        initModificationById(oldContact.getId());
        fillContactData(newContact, false);
        submitModificationContact();
        returnHomePage();
        contactCache = null;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        int id = contact.getId();
        initModificationById(id);
        String firstName = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastName = driver.findElement(By.name("lastname")).getAttribute("value");
        String address = driver.findElement(By.name("address")).getAttribute("value");
        String homePhone = driver.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = driver.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = driver.findElement(By.name("work")).getAttribute("value");
        String faxPhone = driver.findElement(By.name("fax")).getAttribute("value");
        String email1 = driver.findElement(By.name("email")).getAttribute("value");
        String email2 = driver.findElement(By.name("email2")).getAttribute("value");
        String email3 = driver.findElement(By.name("email3")).getAttribute("value");
        driver.navigate().back();
        return new ContactData()
                .withId(id).withFirstName(firstName).withLastName(lastName).withFaxPhone(faxPhone)
                .withAddress(address).withHomePhone(homePhone).withMobilePhone(mobilePhone)
                .withWorkPhone(workPhone).withEmail1(email1)
                .withEmail2(email2).withEmail3(email3);
    }




    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = driver.findElements(By.name("entry"));

        for (WebElement row: rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            String address = cells.get(3).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            ContactData contact = new ContactData()
                    .withId(id).withFirstName(firstName).withLastName(lastName)
                    .withAddress(address).withAllPhones(allPhones)
                    .withAllEmails(allEmails);
            contactCache.withAdded(contact);
        }


        return contactCache;
    }



}
