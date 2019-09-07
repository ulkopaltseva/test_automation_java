package ru.ulko.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by yulia on 06.09.2019.
 */
public class ContactInfoCardTests extends TestBase {

    private ContactData testContact;

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        testContact = new ContactData()
                .withFirstName("contactFirstName").withLastName("contactLastName")
                .withAddress("sovetskaya ulitsa n1").withHomePhone("555 88 88")
                .withWorkPhone("(812)333 33 33").withMobilePhone("8(952)220-00-74")
                .withEmail1("email1").withEmail2("email2").withEmail3("email3");
        app.contact().create(testContact, false);
        int id = app.contact().all().stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
        testContact.withId(id);
    }

    @AfterMethod
    public void clearAfterTests() {
        app.contact().removeById(testContact);
        app.closeAlert();
    }

    @Test
    public void testContactInfoCard() {
        ContactData contact = app.contact().contactById(testContact.getId());
        ContactData infoContactCard = app.contact().infoFromCardForm(contact);
        ContactData infoFromAddedForm = app.contact().infoPhoneFromEditForm(contact);
        contact.withMobilePhone(infoFromAddedForm.getMobilePhone())
                .withHomePhone(infoFromAddedForm.getHomePhone())
                .withWorkPhone(infoFromAddedForm.getWorkPhone());
        MatcherAssert.assertThat(infoContactCard.getAllInfo(), equalTo(mergeAllinfo(contact)));
    }

    private String mergeAllinfo(ContactData contact) {
        String FirstNameAndLastName = contact.getFirstName() + " " + contact.getLastName();
        String address = contact.getAddress() + "\n";
        String homePhone = "H: " + contact.getHomePhone();
        String mobilePhone = "M: " + contact.getMobilePhone();
        String workPhone = "W: " + contact.getWorkPhone();
        String emails = "\n" + contact.getAllEmails();
        return Arrays.asList(FirstNameAndLastName, address,
                homePhone, mobilePhone, workPhone, emails)
                .stream().collect(Collectors.joining("\n"));
    }
}
