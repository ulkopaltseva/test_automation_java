package ru.ulko.addressbook.tests;

import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;

/**
 * Created by yulia on 21.08.2019.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().gotoHomePage();
        app.getContactHelper().initModificationContact();
        app.getContactHelper().fillContactData(new ContactData("First name1", "Last Name1", "8 sovet street, 32", "home phone", "monib phone", "work phone", "fax phone", "email", "email2", "email3"));
        app.getContactHelper().submitModificationContact();
    }
}
