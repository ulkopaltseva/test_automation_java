package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;
import ru.ulko.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        app.group().createGroup(new GroupData("test", "header", "footer"));
        app.goTo().homePage();
    }

    @Test(enabled = false)
    public void testContactCreation() throws Exception {
        List<ContactData> before = app.contact().list();

        List<ContactData> differenceBeforeAfter = new ArrayList<>();

        int maxId;
        if (before.size() != 0) {
            maxId = before.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
        } else
            maxId = 0;

        int index;
        for (index = 1; index < 2; index++) {
            ContactData newContact = new ContactData(maxId + index, "First name_" + index, "Last Name", "8 sovet street, 31", "home phone", "monib phone", "work phone", "fax phone", "email", "email2", null, "test");
            app.contact().create(newContact);
            if (app.contact().getContactCount() == 1) {
                maxId = app.contact().list().get(0).getId();
                newContact.setId(maxId);
            }
            differenceBeforeAfter.add(newContact);
        }
        index--;
        List<ContactData> after = app.contact().list();

        Assert.assertEquals(after.size(), before.size() + index);

        before.addAll(differenceBeforeAfter);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }


}
