package ru.ulko.addressbook.tests;

import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initCreateContact();
    app.getContactHelper().fillContactData(new ContactData("First name", "Last Name", "8 sovet street, 31", "home phone", "monib phone", "work phone", "fax phone", "email", "email2", null, "test"), true);
    app.getContactHelper().submitCreationContact();
  }


}
