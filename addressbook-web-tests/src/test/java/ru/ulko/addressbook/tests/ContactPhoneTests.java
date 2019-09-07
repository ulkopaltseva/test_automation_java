package ru.ulko.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by yulia on 05.09.2019.
 */
public class ContactPhoneTests extends TestBase {
    private ContactData testContact;

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();

        testContact = new ContactData()
                    .withFirstName("testPhoneName").withLastName("testLastName")
                    .withHomePhone("555 55 55").withMobilePhone("+7(922)444 11 16").withWorkPhone("8(800)555 55 55")
                    .withGroup("test");
            app.contact().create(testContact, false);
        testContact.withId(app.contact().all().stream()
                .max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    }

    @AfterMethod
    public void clearAfterTest(){
        app.contact().removeById(testContact);
        app.closeAlert();
    }

    @Test
    public void testContactPhone() {
        ContactData contact = app.contact().contactById(testContact.getId());
        ContactData phoneInfoFromEditForm = app.contact().infoPhoneFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(phoneInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !equals("")).map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
