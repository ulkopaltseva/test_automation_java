package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;

import java.util.List;

public class GroupDeleteTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();

        if (app.group().list().size() == 0) {
            app.group().createGroup(new GroupData().withName("test").withHeader("header").withFooter("footer"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {

        List<GroupData> before = app.group().list();

        int index = before.size() - 1;
        app.group().delete(index);

        List<GroupData> after = app.group().list();

        Assert.assertEquals(after.size(), index);

        before.remove(index);

        Assert.assertEquals(before, after);

    }


}
