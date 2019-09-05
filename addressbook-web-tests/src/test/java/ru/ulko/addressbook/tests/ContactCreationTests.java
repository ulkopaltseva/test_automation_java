package ru.ulko.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;
import ru.ulko.addressbook.model.Contacts;
import ru.ulko.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        app.group().createGroup(new GroupData().withName("test").withHeader("header").withFooter("footer"));
        app.goTo().homePage();
    }

    @Test(enabled = true)
    public void testContactCreation() throws Exception {
        Contacts before = app.contact().all();

        Contacts differenceBeforeAfter = new Contacts();

        int maxId = 0;
        if (before.size() != 0) {
            maxId = before.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
        }

        int index;
        for (index = 1; index < 2; index++) {
            ContactData newContact = new ContactData()
                    .withId(maxId + index).withFirstName("First name_" + index).withLastName("Last Name")
                    .withAddress("8 sovet street, 31").withHomePhone("home phone").withMobilePhone("mobile phone")
                    .withWorkPhone("work phone").withFaxPhone("fax phone")
                    .withEmail1("email").withEmail2("email2").withGroup("test");
            app.contact().create(newContact);

            if (maxId == 0) {
                maxId = app.contact().all().stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
                newContact.withId(maxId);
            }

            differenceBeforeAfter.add(newContact);
        }
        index--;
        Contacts after = app.contact().all();

        assertThat(after.size(), equalTo(before.size() + index));
        assertThat(after, equalTo(before.withAddedAll(differenceBeforeAfter)));
    }


}
