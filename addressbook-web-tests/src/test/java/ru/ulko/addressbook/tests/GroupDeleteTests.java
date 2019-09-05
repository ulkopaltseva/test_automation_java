package ru.ulko.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;
import ru.ulko.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

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
        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.withRemoved(deletedGroup)));

    }


}
