package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        // перейти на страницу групп
        app.getNavigationHelper().gotoGroupPage();

        // создать список before для хранения списка групп до создания новых
        List<GroupData> before = app.getGroupHelper().getGroupList();

        // вычислить максимальное значение id  в списке before, чтобы задать правильные id для новых групп
        int max = 0;
        for (GroupData g: before){
            if (g.getId() > max){
                max = g.getId();
            }
        }

        // вычислить максимальное значение id с помощью компаратора
        Comparator<? super GroupData> ById = new Comparator<GroupData>() {
            @Override
            public int compare(GroupData o1, GroupData o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        };
        int max1 = before.stream().max(ById).get().getId();

        // вычислить максимальное значение id с помощью лямбда-выражения
        int max2 = before.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();


        int i; // это для хранения количества созданных групп
        // в цикле создать несколько групп
        List<GroupData> differensBeforeAfter = new ArrayList<>(); //список групп, которые будут добавлены
        for (i = 1; i < 3; i++) {
            max2++; // id каждой следующей группы
            GroupData group = new GroupData(max2, "test_" + i, "header", "footer");
            app.getGroupHelper().createGroup(group);
            differensBeforeAfter.add(group);
        }
        i--; /* количество созданных групп уменьшить на 1 так как в цикле на последнем круге группа не создалась
                но счетчик все равно был инкремирован */

        // создать список after в который добавить список групп после создания новых
        List<GroupData> after = app.getGroupHelper().getGroupList();

        // сравнивнить количество элементов в списках before и after. в after должно увеличиться на i
        Assert.assertEquals(after.size(), before.size() + i);

        // вычислить значение id для созданной группы - будет максимальное id среди созданных групп


        // добавить в список before созданные группы, добавленные в список differensBeforeAfter
        for (GroupData d: differensBeforeAfter){
            before.add(d);
        }
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
