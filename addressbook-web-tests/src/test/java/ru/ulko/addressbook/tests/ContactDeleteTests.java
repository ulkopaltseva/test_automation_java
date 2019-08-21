package ru.ulko.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by yulia on 21.08.2019.
 */
public class ContactDeleteTests extends TestBase {

    @Test
    public void testContactDelete(){
        app.getContactHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.closeAlert();
    }
}
