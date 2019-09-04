package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yulia on 21.08.2019.
 */
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();

        if (app.group().list().size() == 0) {
            app.group().createGroup(new GroupData().withName("test").withHeader("header").withFooter("footer"));
        }
    }

    @Test
    public void testGroupModification() {
        ensurePreconditions();
        Set<GroupData> before = app.group().all();
        GroupData modifyGroup = before.iterator().next();

        GroupData group = new GroupData().withId(modifyGroup.getId()).withName("TEST").withHeader("HEADER").withFooter("FOOTER");
        app.group().modify(group);

        Set<GroupData> after = app.group().all();

        Assert.assertEquals(after.size(), before.size());

        before.remove(modifyGroup);
        before.add(group);
        Assert.assertEquals(before, after);

    }

}
