package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by yulia on 21.08.2019.
 */
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.getNavigationHelper().gotoGroupPage();

        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test", "header", "footer"));
        }
    }

    @Test
    public void testGroupModification() {
        ensurePreconditions();
        List<GroupData> before = app.getGroupHelper().getGroupList();

        GroupData newGroup = new GroupData("TEST", "HEADER", "FOOTER");
        int lastGroupNumber = before.size() - 1;
        int idOfModify = before.get(lastGroupNumber).getId();
        newGroup.setId(idOfModify);
        app.getGroupHelper().modificateGroup(newGroup, lastGroupNumber);

        List<GroupData> after = app.getGroupHelper().getGroupList();

        Assert.assertEquals(after.size(), before.size());

        before.remove(lastGroupNumber);
        before.add(newGroup);
        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

    }

}
