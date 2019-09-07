package ru.ulko.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by yulia on 06.09.2019.
 */
public class ContactEmailTests extends TestBase{

    private ContactData testContact;

    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().homePage();
        testContact = new ContactData().withFirstName("testEmailFirstName")
                .withLastName("testEmailLastName").withEmail1("testEmail1")
                .withEmail2("testEmail2").withEmail3("testEmail3");
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
    public void testContactEmail(){
        ContactData contact = app.contact().contactById(testContact.getId());
        ContactData infoEmailFromAddedForm = app.contact().infoEmailFromAddedForm(contact);
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(infoEmailFromAddedForm)));
    }

    private String mergeEmails(ContactData contact){
        return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3()).stream()
                .filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
    }
}
