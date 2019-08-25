package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

/**
 * Created by yulia on 21.08.2019.
 */
public class ContactDeleteTests extends TestBase {

    @Test
    public void testContactDelete(){
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact())
        {
            app.getContactHelper().createContact(new ContactData("First name", "Last Name", "8 sovet street, 31", "home phone", "monib phone", "work phone", "fax phone", "email", "email2", null, "test"));
        }
        app.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.closeAlert();
        app.getNavigationHelper().gotoHomePageWithoutCheck();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
        app.driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }
}
