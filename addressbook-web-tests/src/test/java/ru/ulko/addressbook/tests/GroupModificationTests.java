package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by yulia on 21.08.2019.
 */
public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        int id;
        String name = "test";
        String header = "header";
        String footer = "footer";
        GroupData newGroup = new GroupData(name, header, footer);

        app.getNavigationHelper().gotoGroupPage();

        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(newGroup);
        }

        List<GroupData> before = app.getGroupHelper().getGroupList();

        newGroup.setName("TEST");
        newGroup.setHeader("HEADER");
        newGroup.setFooter("FOOTER");
        int lastGroup = before.size() - 1;
        id = before.get(lastGroup).getId();
        newGroup.setId(id);
        app.getGroupHelper().modificateGroup(newGroup, lastGroup);

        List<GroupData> after = app.getGroupHelper().getGroupList();

        Assert.assertEquals(after.size(), before.size());

        before.remove(lastGroup);
        before.add(newGroup);
        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

    }

}
