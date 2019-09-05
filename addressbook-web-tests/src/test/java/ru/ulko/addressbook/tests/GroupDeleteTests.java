package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;
import ru.ulko.addressbook.model.Groups;

import java.util.Set;

public class GroupDeleteTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();

        if (app.group().all().size() == 0) {
            app.group().createGroup(new GroupData().withName("test").withHeader("header").withFooter("footer"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {

        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();

        app.group().deleteById(deletedGroup);

        Groups after = app.group().all();
        Assert.assertEquals(after.size(), before.size() - 1);
        Assert.assertEquals(before.withRemove(deletedGroup), after);

    }


}
