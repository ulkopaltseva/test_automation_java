package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;

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
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.closeAlert();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }
}
