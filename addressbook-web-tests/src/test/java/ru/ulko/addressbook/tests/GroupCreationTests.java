package ru.ulko.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;
import ru.ulko.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new GroupData().withName("test_1").withHeader("header_1").withFooter("footer_1")});
        list.add(new Object[]{new GroupData().withName("test_2").withHeader("header_2").withFooter("footer_2")});
        list.add(new Object[]{new GroupData().withName("test_3").withHeader("header_3").withFooter("footer_3")});
        return list.iterator();
    }

    @BeforeMethod
    public void ensurePreconditions() {
        app.group().driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        app.goTo().groupPage();
        app.group().driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test(enabled = true, dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) throws Exception {

        Groups before = app.group().all();

        Groups differenceBeforeAfter = new Groups();
        app.group().createGroup(group);
        int maxId = app.group().all().stream().mapToInt((g) -> g.getId()).max().getAsInt();
        group.withId(maxId);
        differenceBeforeAfter.add(group);
       /* int countExistingElements = before.size();
        int countCreatedElements = 1;
        countCreatedElements++;
        for (int i = countExistingElements; i < countCreatedElements + countExistingElements; i++) {
            GroupData group = new GroupData().withName("test_" + i).withHeader("header").withFooter("footer");
            app.group().createGroup(group);
            int maxId = app.group().all().stream().mapToInt((g) -> g.getId()).max().getAsInt();
            group.withId(maxId);
            differenceBeforeAfter.add(group);
        }*/

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
