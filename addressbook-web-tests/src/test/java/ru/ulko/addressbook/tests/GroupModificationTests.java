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
        // переход на страницу группы
        app.getNavigationHelper().gotoGroupPage();

        // проверяется, есть ли хоть одна группа на странице, иначе создается новую
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test", "HEADER", "FOOTER"));
        }

        // создается список before и заполняется списком текущих групп
        List<GroupData> before = app.getGroupHelper().getGroupList();

        // создается объект group, которым будет модифицированна группа
        GroupData group = new GroupData((before.get(before.size() - 1)).getId(), "test", "HEADER", "FOOTER");

        // вызывается метод модификации для последней в списке группы
        app.getGroupHelper().modificateGroup(group, before.size() - 1);

        // создается список after и заполняется получившимся после модификации списком групп
        List<GroupData> after = app.getGroupHelper().getGroupList();
        // сравниваем количество групп до и после модификации - должно совпадать
        Assert.assertEquals(after.size(), before.size());

        // удаляется элемент в списке before, который был модифицирован
        before.remove(before.size() - 1);

        // добавляется новый объект, которым заменяется модифицируемый - group
        before.add(group);

        // сравниваются списки before и after, предворительно преобразовав их в множества - HashSet<Object>
        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

    }

}
