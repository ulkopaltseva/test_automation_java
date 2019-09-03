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
    public void ensurePreconditions() {
        app.goTo().groupPage();

        if (app.group().list().size() == 0) {
            app.group().createGroup(new GroupData().withName("test").withHeader("header").withFooter("footer"));
        }
    }

    @Test
    public void testGroupModification() {
        ensurePreconditions();
        List<GroupData> before = app.group().list();

        GroupData group = new GroupData().withName("TEST").withHeader("HEADER").withFooter("FOOTER");
        int index = before.size() - 1;
        int id = before.get(index).getId();
        group.withId(id);
        app.group().modify(group, index);

        List<GroupData> after = app.group().list();

        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

    }

}
