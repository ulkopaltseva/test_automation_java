package ru.ulko.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;
import ru.ulko.addressbook.model.Groups;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by yulia on 21.08.2019.
 */
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test").withHeader("header").withFooter("footer"));
        }
        app.contact().driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test
    public void testGroupModification() {
        Groups before = app.group().all();
        GroupData oldGroup = before.iterator().next();

        GroupData newGroup = new GroupData().withId(oldGroup.getId()).withName("TEST").withHeader("HEADER").withFooter("FOOTER");
        app.group().modify(newGroup);

        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.withModified(oldGroup, newGroup)));
    }

}
