package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;
import ru.ulko.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        if (app.getGroupHelper().getGroupCount() == 0) {
            app.getGroupHelper().createGroup(new GroupData("test", "header", "footer"));
        }
        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelper().getContactCount();
        for (int i = 1; i < 10; i++) {
            app.getContactHelper().createContact(new ContactData("First name_" + i, "Last Name", "8 sovet street, 31", "home phone", "monib phone", "work phone", "fax phone", "email", "email2", null, "test"));
        }
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before + 9);
    }


}
