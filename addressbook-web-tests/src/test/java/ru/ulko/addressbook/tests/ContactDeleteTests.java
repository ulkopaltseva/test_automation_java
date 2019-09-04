package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yulia on 21.08.2019.
 */
public class ContactDeleteTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.contact().driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        app.goTo().homePage();

        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData("First name", "Last Name", "8 sovet street, 31", "home phone", "monib phone", "work phone", "fax phone", "email", "email2", null, "test"));
        }
    }

    @AfterMethod
    public void stopContact() {
        app.contact().driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test(enabled = false)
    public void testContactDelete() {
        List<ContactData> before = app.contact().list();

        app.contact().delete();
        app.closeAlert();
        app.goTo().homePage();


        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(0);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
