package ru.ulko.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;
import ru.ulko.addressbook.model.Groups;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.group().driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        app.goTo().groupPage();
        app.group().driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test(enabled = true)
    public void testGroupCreation() throws Exception {

        Groups before = app.group().all();

        Groups differenceBeforeAfter = new Groups();
        int countExistingElements = before.size();
        int countCreatedElements = 1;
        countCreatedElements++;
        for (int i = countExistingElements; i < countCreatedElements + countExistingElements; i++) {
            GroupData group = new GroupData().withName("test_" + i).withHeader("header").withFooter("footer");
            app.group().createGroup(group);
            int maxId = app.group().all().stream().mapToInt((g) -> g.getId()).max().getAsInt();
            group.withId(maxId);
            differenceBeforeAfter.add(group);
        }

        assertThat(app.group().count(), equalTo(before.size() + differenceBeforeAfter.size()));

        Groups after = app.group().all();
        assertThat(after, equalTo(before.withAddedAll(differenceBeforeAfter)));
    }

    @Test
    public void testBadGroupCreation() {
        Groups before = app.group().all();
        GroupData newBadGroup = new GroupData().withName("test'");
        app.group().createGroup(newBadGroup);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before));
    }

}
