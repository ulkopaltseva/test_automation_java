package ru.ulko.addressbook.tests;

import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;
import ru.ulko.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getGroupHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test", "header","footer"));
    app.getContactHelper().createContact(new ContactData("First name", "Last Name", "8 sovet street, 31", "home phone", "monib phone", "work phone", "fax phone", "email", "email2", null, "test"));

  }


}
