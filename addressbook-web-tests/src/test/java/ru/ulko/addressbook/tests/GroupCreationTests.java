package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.Set;

public class GroupCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
    }

    @Test
    public void testGroupCreation() throws Exception {

        Set<GroupData> before = app.group().all();
        int maxId = 0;
        if (before.size() != 0) {
            maxId = before.stream().mapToInt((g) -> g.getId()).max().getAsInt();
        }
        Set<GroupData> differenceBeforeAfter = new HashSet<>();
        int index = before.size();
        for (int i = 1; i < 3; i++) {
            GroupData group = new GroupData().withName("test_" + i).withHeader("header").withFooter("footer");
            app.group().createGroup(group);
            if (maxId == 0) {
                maxId = before.stream().mapToInt((g) -> g.getId()).max().getAsInt();
            }
            maxId++;
            group.withId(maxId);
            differenceBeforeAfter.add(group);
            index++;
        }
        Set<GroupData> after = app.group().all();

        Assert.assertEquals(after.size(), index);
        before.addAll(differenceBeforeAfter);

        Assert.assertEquals(before, after);
    }

}
