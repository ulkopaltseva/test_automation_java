package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yulia on 21.08.2019.
 */
public class ContactDeleteTests extends TestBase {

    @Test(enabled = false)
    public void testContactDelete() {
        // перейти на страницу контактов
        app.getNavigationHelper().gotoHomePage();


        // проверить, есть ли хоть один контакт на странице, если нет - создать
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("First name", "Last Name", "8 sovet street, 31", "home phone", "monib phone", "work phone", "fax phone", "email", "email2", null, "test"));
        }
        /* добавить имплицидный таймаут, так как есть проблемы с чтением списка после удаления контакта
           (считывает список еще до загрузки нового) */
        app.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // заполнить список before списком контактов до удаления
        List<ContactData> before = app.getContactHelper().getContactList();

        // выбрать контакт, который нужно удалить
        app.getContactHelper().selectContact();

        // удалить выбранных контакт
        app.getContactHelper().deleteSelectedContact();

        /* появляется всплывающее окно с выбором "да", "нет" на запрос об удалении.
           выбрать "да" с помощью метода closeAlert */
        app.closeAlert();

        /* перейти на страницу контактов, используя метод, где нет проверки,
           открыта ли уже страница контактов. Это нужно, чтобы принудительно обновить страницу,
            так как нужно посмотреть изменения в списке контактов, но там зависает на сообщении successfull*/
        app.getNavigationHelper().gotoHomePageWithoutCheck();

        // заполнить список after для обновленного списка контактов после удаления
        List<ContactData> after = app.getContactHelper().getContactList();

        // сравнить количество контактов в списках after и before, должно уменьшиться на 1
        Assert.assertEquals(after.size(), before.size() - 1);

        // выставить имплицидное ожидание обратно в ноль
        app.driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        // удалить из списка before контакт, который был удален (первый в списке)
        before.remove(0);
        // сравнить списки before и after превратив их в неупорядоченные множества
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
