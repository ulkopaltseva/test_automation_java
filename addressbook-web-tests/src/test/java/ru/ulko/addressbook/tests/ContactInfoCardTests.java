package ru.ulko.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;

/**
 * Created by yulia on 06.09.2019.
 */
public class ContactInfoCardTests extends TestBase {

    private ContactData testContact;

    @BeforeMethod
    public void ensurePreconditions(){
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
    public void clearAfterTests(){
        app.contact().deleteById(testContact);
        app.closeAlert();
    }

    @Test
    public void testContactInfoCard(){
        ContactData contact = app.contact().contactById(testContact.getId());
        

    }
}
