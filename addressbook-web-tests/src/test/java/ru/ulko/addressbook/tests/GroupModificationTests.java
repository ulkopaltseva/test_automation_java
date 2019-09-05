package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;
import ru.ulko.addressbook.model.Groups;

import java.util.Set;

/**
 * Created by yulia on 21.08.2019.
 */
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();

        if (app.group().all().size() == 0) {
            app.group().createGroup(new GroupData().withName("test").withHeader("header").withFooter("footer"));
        }
    }

    @Test
    public void testGroupModification() {
        ensurePreconditions();
        Groups before = app.group().all();
        GroupData oldGroup = before.iterator().next();

        GroupData newGroup = new GroupData().withId(oldGroup.getId()).withName("TEST").withHeader("HEADER").withFooter("FOOTER");
        app.group().modify(newGroup);

        Groups after = app.group().all();
        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(before.withModify(oldGroup, newGroup), after);

    }

}
