package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;
import ru.ulko.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {


    @Test(enabled = true)
    public void testContactCreation() throws Exception {
        // перейти на страницу групп
        app.getNavigationHelper().gotoGroupPage();

        /* проверить, есть ли хоть одна группа на странице, если нет - добавить ту,
           что нужна при создании контакта - test */
        if (app.getGroupHelper().getGroupCount() == 0) {
            app.getGroupHelper().createGroup(new GroupData("test", "header", "footer"));
        }

        // перейти на страницу контактов
        app.getNavigationHelper().gotoHomePage();

        // заполнить список before списком контактов до каких-л. действий
        List<ContactData> before = app.getContactHelper().getContactList();

        // список групп, которые будут созданы во время теста
        List<ContactData> differenceBeforeAfter = new ArrayList<>();

        // для вычисления id нового контакта, нужно вычислить максимальный элемент, если элементов нет - это будет ноль
        int max;
        if (app.getContactHelper().isThereAContact()) {
            max = before.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
        } else {
            max = 0;
        }

        // переменная для хранения количества созданных контактов
        int i; // начинается с 1цы, чтобы использовать в именах контактов

        // цикл для создания сразу нескольких контактов в количестве i
        for (i = 1; i < 2; i++) {
            // id вычислить, прибавляя i к максимальному идентификатору списка контактов before
            ContactData newContact = new ContactData(max + i, "First name_" + i, "Last Name", "8 sovet street, 31", "home phone", "monib phone", "work phone", "fax phone", "email", "email2", null, "test");
            app.getContactHelper().createContact(newContact);
            // если контакт только один, вычислить реальный id
            if(app.getContactHelper().getContactCount() == 1){
                max = app.getContactHelper().getContactList().get(0).getId();
                newContact.setId(max);
            }
            differenceBeforeAfter.add(newContact);
        }

        i--; /* количество созданных контактов подсчитывается уменьшением на 1 переменной i
                так как в цикле на последнем круге контакт уже не создается
                но счетчик все равно инкремируется */

        // заполняется список after обновленным списком контактов
        List<ContactData> after = app.getContactHelper().getContactList();

        // проверяется количество контактов в списке до добавления нового и после
        Assert.assertEquals(after.size(), before.size() + i);

        // к старому списку добавить созданные контакты, сохраненные в differenceBeforeAfter
        for(ContactData newContact: differenceBeforeAfter){
            before.add(newContact);
        }


        // сравнить списки контактов до и после добавления новых
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }


}
