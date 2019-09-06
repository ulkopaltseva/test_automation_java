package ru.ulko.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by yulia on 05.09.2019.
 */
public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().count() == 0){
            ContactData contact = new ContactData().withFirstName("First name").withLastName("Last Name").withAddress("8 sovetskaya 51/8, kv.14")
                    .withHomePhone("555 55 55").withMobilePhone("+7(922)444 11 16").withWorkPhone("8(800)555 55 55")
                    .withEmail1("E-mail1").withEmail2("E-mail2").withEmail3("E-mail3").withGroup("test");
            app.contact().create(contact);
        }
    }

    @Test
    public void testContactPhone(){
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact, equalTo(contactInfoFromEditForm));
    }
}
