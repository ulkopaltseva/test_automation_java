package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yulia on 21.08.2019.
 */
public class ContactModificationTests extends TestBase {

    @Test(enabled = true)
    public void testContactModification() {
        // перейти на главную страницу - там список контактов
        app.getNavigationHelper().gotoHomePage();

        // создать список before и заполнить списков контактов до изменений
        List<ContactData> before = app.getContactHelper().getContactList();

        // проверить, есть хоть один контакт на странице, иначе создать новый
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("First name", "Last Name", "8 sovet street, 31", "home phone", "monib phone", "work phone", "fax phone", "email", "email2", null, "test"));

            // если добавлен один контакт, получаем список контактов на странице и единственный нулевой элемент будет созданный контакт
            before.add(app.getContactHelper().getContactList().get(0));
        }

        // создать объект contact с текстом в полях, которые нужно изменить
        // id использовать такой же, как у изменяемого
        ContactData contact = new ContactData(before.get(0).getId(),"First name", "Last Name", "8 sovet street, 31", "home phone", "monib phone", "work phone", "fax phone", "email", "email2", null, "test");

        /* модифицировать контакт (пока нажать на первый найденный элемнт "карандаш" на странице контактов для
           редактирования данных */
        app.getContactHelper().modificateContact(contact);

        // перейти на главную страницу со списком контактов
        app.getNavigationHelper().gotoHomePage();

        // заполнить список after обновленным списком контактов
        List<ContactData> after = app.getContactHelper().getContactList();

        // вычислить сколько контактов было до модификации и после. должно совпадать
        Assert.assertEquals(after.size(), before.size());

        // сравнить списки контактов before и after
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }
}
