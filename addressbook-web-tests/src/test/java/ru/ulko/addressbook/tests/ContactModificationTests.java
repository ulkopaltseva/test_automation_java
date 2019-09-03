package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;
import ru.ulko.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yulia on 21.08.2019.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        app.contact().driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        app.goTo().groupPage();
        if(app.group().list().size() == 0) {
            app.goTo().groupPage();
            app.group().createGroup(new GroupData().withName("test"));
        }

        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData("First name", "Last Name", "8 sovet street, 31", "home phone", "monib phone", "work phone", "fax phone", "email", "email2", null, "test"));
        }


    }

    @AfterMethod
    public void stopContact(){
        app.contact().driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test(enabled = false)
    public void testContactModification() {
        List<ContactData> before = app.contact().list();
        int id = 0;
        int indexModifyContact = before.get(id).getId();
        ContactData contact = new ContactData("name", "last name", "rostovskaya 26", "home phone", "8800", "5500", "3500", "email@", "email2@", null, "test");
        contact.setId(indexModifyContact);
        app.contact().modify(contact);


        List<ContactData> after = app.contact().list();
        before.remove(id);
        before.add(contact);
        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }
}
