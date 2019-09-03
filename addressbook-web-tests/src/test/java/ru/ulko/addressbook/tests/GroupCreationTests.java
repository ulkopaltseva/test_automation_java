package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
    }

    @Test
    public void testGroupCreation() throws Exception {

        List<GroupData> before = new ArrayList<>();
        int maxId = 0;
        if (app.group().list().size() != 0) {
            before = app.group().list();
            maxId = before.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
        }
        List<GroupData> differenceBeforeAfter = new ArrayList<>();
        int index = before.size();
        for (int i = 1; i < 3; i++) {
            GroupData group = new GroupData("test_" + i, "header", "footer");
            maxId++;
            group.setId(maxId);
            app.group().createGroup(group);
            if (app.group().list().size() == 1){
                maxId = before.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
            }
            differenceBeforeAfter.add(group);
            index++;
        }
        List<GroupData> after = app.group().list();

        Assert.assertEquals(after.size(), index);
        before.addAll(differenceBeforeAfter);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
