package ru.ulko.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;
import ru.ulko.addressbook.model.Contacts;

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
            app.contact().create(new ContactData().withFirstName("First name").withLastName("Last Name").withAddress("8 sovetskaya 51/8, kv.14")
                    .withHomePhone("555 55 55").withMobilePhone("+7(922)444 11 16").withWorkPhone("8(800)555 55 55")
                    .withEmail1("E-mail1").withEmail2("E-mail2").withEmail3("E-mail3").withGroup("test"), true);
        }
    }

    @Test(enabled = true)
    public void testContactDelete() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();

        app.contact().removeById(deletedContact);
        app.closeAlert();
        app.contact().driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        app.goTo().homePageWithoutCheck();

        assertThat(app.contact().count(), equalTo(before.size() - 1));

        Contacts after = app.contact().all();
        app.contact().driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
