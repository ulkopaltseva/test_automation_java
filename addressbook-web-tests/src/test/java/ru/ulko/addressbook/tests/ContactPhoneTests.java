package ru.ulko.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;

/**
 * Created by yulia on 05.09.2019.
 */
public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().count() == 0){
            ContactData contact = new ContactData().withFirstName("test")
                    .withGroup("test").withHomePhone("800")
                    .withMobilePhone("900").withFaxPhone("1000");
            app.contact().create(contact);
        }
    }

    @Test
    public void testContactPhone(){
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    }
}
