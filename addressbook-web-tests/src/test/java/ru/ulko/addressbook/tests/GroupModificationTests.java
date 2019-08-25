package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by yulia on 21.08.2019.
 */
public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test", "HEADER", "FOOTER"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("test", "HEADER", "FOOTER");
        app.getGroupHelper().modificateGroup(group, before.size() - 1);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        //удаляем элемент в списке before, который мы модифицировали
        before.remove(before.size() - 1);

        //добавляем новый объект, которым мы заменяли модифицируемый - group
        before.add(group);

        //сравниваем списки before и after, предворительно преобразовав их в множества - HashSet<Object>
        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

    }

}
