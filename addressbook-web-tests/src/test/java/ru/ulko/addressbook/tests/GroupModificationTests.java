package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by yulia on 21.08.2019.
 */
public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test", "HEADER","FOOTER"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().modificateGroup(new GroupData("test", "HEADER","FOOTER"), before.size() - 1);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

    }

}
