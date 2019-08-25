package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        // перейти на страницу групп
        app.getNavigationHelper().gotoGroupPage();

        // создать список before для хранения списка групп до создания новых
        List<GroupData> before = app.getGroupHelper().getGroupList();

        int i; // это для хранения количества созданных групп
        // в цикле создать несколько групп
        for (i = 1; i < 3; i++) {
            app.getGroupHelper().createGroup(new GroupData("test_" + i, "header", "footer"));
        }
        i--; /* количество созданных групп уменьшить на 1 так как в цикле на последнем круге группа не создалась
                но счетчик все равно был инкремирован */

        // создать список after в который добавить список групп после создания новых
        List<GroupData> after = app.getGroupHelper().getGroupList();

        // сравнивнить количество элементов в списках before и after. в after должно увеличиться на i
        Assert.assertEquals(after.size(), before.size() + i);
    }

}
