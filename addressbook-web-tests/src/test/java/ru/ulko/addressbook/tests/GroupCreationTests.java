package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import ru.ulko.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().createGroup(new GroupData("test", "header","footer"));
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }

}
