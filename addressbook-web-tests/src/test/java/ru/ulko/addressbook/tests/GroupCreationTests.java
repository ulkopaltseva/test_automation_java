package ru.ulko.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;
import ru.ulko.addressbook.model.Groups;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
    }

    @Test
    public void testGroupCreation() throws Exception {

        Groups before = app.group().all();
        int maxId = -1;
        if (before.size() != 0) {
            maxId = before.stream().mapToInt((g) -> g.getId()).max().getAsInt();
        }
        Groups differenceBeforeAfter = new Groups();
        int countExistingElements = before.size();
        int countCreatedElements = 2;
        countCreatedElements++;
        for (int i = countExistingElements; i < countCreatedElements + countExistingElements; i++) {
            GroupData group = new GroupData().withName("test_" + i).withHeader("header").withFooter("footer");
            app.group().createGroup(group);
            maxId++;
            if (maxId == 0) {
                maxId = before.stream().mapToInt((g) -> g.getId()).max().getAsInt();
            }
            group.withId(maxId);
            differenceBeforeAfter.add(group);
        }


        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() + differenceBeforeAfter.size()));

        assertThat(after, equalTo(before.withAddedAll(differenceBeforeAfter)));
    }

}
