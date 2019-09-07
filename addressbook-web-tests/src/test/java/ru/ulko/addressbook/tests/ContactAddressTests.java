package ru.ulko.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by yulia on 06.09.2019.
 */
public class ContactAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        if (app.contact().count() == 0) {
            app.contact().create(new ContactData().withAddress("rostovskay vulitsa"), false);
        }
    }

    @Test
    public void testContactAddress() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData infoAddressFromAddedPage = app.contact().infoAddressFromAddedPage(contact);
        assertThat(contact.getAddress(), equalTo(infoAddressFromAddedPage.getAddress()));
    }
}
