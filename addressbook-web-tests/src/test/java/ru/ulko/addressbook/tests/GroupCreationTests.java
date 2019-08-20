package ru.ulko.addressbook.tests;

import org.testng.annotations.*;

import ru.ulko.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("name", "header","footer"));
        app.getGroupHelper().submitGroupCreation();
    }

}
