package ru.ulko.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;
import ru.ulko.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by yulia on 21.08.2019.
 */
public class ContactDeleteTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();

        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("First name").withLastName("Last Name")
                    .withAddress("8 sovet street, 31").withHomePhone("home phone")
                    .withMobilePhone("mobile phone").withWorkPhone("work phone")
                    .withFaxPhone("fax phone").withEmail1("email").withEmail2("email2").withGroup("test"));
        }
    }

    @Test(enabled = true)
    public void testContactDelete() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();

        app.contact().deleteById(deletedContact);
        app.closeAlert();
        app.contact().driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        app.goTo().homePageWithoutCheck();

        assertThat(app.contact().count(), equalTo(before.size() - 1));

        Contacts after = app.contact().all();
        app.contact().driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        assertThat(after, equalTo(before.withRemoved(deletedContact)));
    }
}
