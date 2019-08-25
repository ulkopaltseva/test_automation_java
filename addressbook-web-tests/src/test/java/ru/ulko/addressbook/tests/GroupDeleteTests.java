package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;

import java.util.List;

public class GroupDeleteTests extends TestBase {

    @Test
    public void testGroupDeletion() throws Exception {
        // переход на страницу группы
        app.getNavigationHelper().gotoGroupPage();

        // проверка, есть ли хоть одна группа на странице, если нет - создать
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("name", "header", "footer"));
        }

        // создать список before для хранения списка групп до удаления
        List<GroupData> before = app.getGroupHelper().getGroupList();

        // выставить чек-бокс в последней группе из списка
        app.getGroupHelper().selectGroup(before.size() - 1);

        // удалить выбранную группу
        app.getGroupHelper().deleteSelectedGroups();

        // создать список after и заполнить его получившимся списком групп после удаления
        List<GroupData> after = app.getGroupHelper().getGroupList();

        // сравнить количество групп до и после удаления - после удаления должно уменьшиться на 1
        Assert.assertEquals(after.size(), before.size() - 1);

        // удалить в списке before последний элемент, как и в тесте на удаление
        before.remove(before.size() - 1);

        // сравнить полученные списки - должны совпасть
        Assert.assertEquals(before, after);

    }

}
