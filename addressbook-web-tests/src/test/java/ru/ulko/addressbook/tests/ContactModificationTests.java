package ru.ulko.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;
import ru.ulko.addressbook.model.Contacts;
import ru.ulko.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by yulia on 21.08.2019.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.goTo().groupPage();
            app.group().createGroup(new GroupData().withName("test"));
        }

        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create((new ContactData().withFirstName("First name").withLastName("Last Name")
                    .withAddress("8 sovet street, 31").withHomePhone("home phone")
                    .withMobilePhone("mobile phone").withWorkPhone("work phone")
                    .withFaxPhone("fax phone").withEmail1("email").withEmail2("email2").withGroup("test")));
        }


    }


    @Test(enabled = true)
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData oldContact = before.iterator().next();
        ContactData newContact = new ContactData()
                .withId(oldContact.getId()).withFirstName("name")
                .withLastName("last name").withAddress("rostovskaya 26")
                .withHomePhone("home phone").withHomePhone("8800")
                .withMobilePhone("5500").withFaxPhone("3500").withEmail1("email@")
                .withEmail2("email2@").withGroup("test");

        app.contact().modifyById(oldContact, newContact);


        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(before, equalTo(before.withModified(oldContact, newContact)));

    }
}
