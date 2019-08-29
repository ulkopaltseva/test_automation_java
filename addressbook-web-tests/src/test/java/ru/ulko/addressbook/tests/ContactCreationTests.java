package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;
import ru.ulko.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {


    @Test(enabled = false)
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
        int before = app.getContactHelper().getContactCount();

        // переменная для хранения количества созданных контактов
        int i; // начинается с 1цы, чтобы использовать в именах контактов

        // цикл для создания сразу нескольких контактов в количестве i
        for (i = 1; i < 5; i++) {
            app.getContactHelper().createContact(new ContactData("First name_" + i, "Last Name", "8 sovet street, 31", "home phone", "monib phone", "work phone", "fax phone", "email", "email2", null, "test"));
        }

        i--; /* количество созданных контактов подсчитывается уменьшением на 1 переменной i
                так как в цикле на последнем круге контакт уже не создается
                но счетчик все равно инкремируется */

        // заполняется список after обновленным списком контактов
        int after = app.getContactHelper().getContactCount();

        // проверяется количество контактов в списке до добавления нового и после
        Assert.assertEquals(after, before + i);
    }


}
