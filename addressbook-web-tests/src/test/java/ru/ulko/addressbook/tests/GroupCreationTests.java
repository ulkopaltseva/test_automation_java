package ru.ulko.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;
import ru.ulko.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
        String line = reader.readLine();
        while(line != null){
            String[] split = line.split(";");
            list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1])
            .withFooter(split[2])});
            line = reader.readLine();
        }

        return list.iterator();
    }

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
    }

    @Test(enabled = true, dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) throws Exception {
        Groups before = app.group().all();
        app.group().create(group);
        int maxId = app.group().all().stream().mapToInt((g) -> g.getId()).max().getAsInt();
        group.withId(maxId);

        assertThat(app.group().count(), equalTo(before.size() + 1));

        Groups after = app.group().all();
        assertThat(after, equalTo(before.withAdded(group)));
    }

    @Test(enabled = false)
    public void testBadGroupCreation() {
        Groups before = app.group().all();
        GroupData newBadGroup = new GroupData().withName("test'");
        app.group().create(newBadGroup);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before));
    }

}
