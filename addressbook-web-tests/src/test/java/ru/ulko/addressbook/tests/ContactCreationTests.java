package ru.ulko.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;
import ru.ulko.addressbook.model.Contacts;
import ru.ulko.addressbook.model.GroupData;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        app.goTo().groupPage();
        if (app.group().count() == 0) {
            app.group().createGroup(new GroupData().withName("test").withHeader("header").withFooter("footer"));
        }

        app.goTo().homePage();
        app.contact().driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test(enabled = true)
    public void testContactCreation() throws Exception {
        File photo = new File("src/test/resources/cat.jpg");
        Contacts before = app.contact().all();
        Contacts differenceBeforeAfter = new Contacts();

        int countExistingElements = before.size();
        int countCreatedElements = 1;
        countCreatedElements++;
        for (int i = countExistingElements + 1; i < countCreatedElements + countExistingElements; i++) {
            ContactData newContact = new ContactData()
                    .withFirstName("First name_" + i).withLastName("Last Name").withAddress("8 sovetskaya 51/8, kv.14")
                    .withHomePhone("555 55 55").withMobilePhone("+7(922)444 11 16").withWorkPhone("8(800)555 55 55")
                    .withEmail1("E-mail1").withEmail2("E-mail2").withEmail3("E-mail3").withGroup("test")
                    .withPhoto(photo);
            app.contact().create(newContact, true);
            int maxId = app.contact().all().stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();

            newContact.withId(maxId);
            differenceBeforeAfter.add(newContact);
        }

        assertThat(app.contact().count(), equalTo(before.size() + differenceBeforeAfter.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAddedAll(differenceBeforeAfter)));
    }


}
