package ru.ulko.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;
import ru.ulko.addressbook.model.Contacts;
import ru.ulko.addressbook.model.GroupData;

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
            app.contact().create(new ContactData().withFirstName("First name").withLastName("Last Name").withAddress("8 sovetskaya 51/8, kv.14")
                    .withHomePhone("555 55 55").withMobilePhone("+7(922)444 11 16").withWorkPhone("8(800)555 55 55")
                    .withEmail1("E-mail1").withEmail2("E-mail2").withEmail3("E-mail3").withGroup("test"), true);
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

        assertThat(app.contact().count(), equalTo(before.size()));

        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withModified(oldContact, newContact)));

    }
}
