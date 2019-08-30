package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.ContactData;

/**
 * Created by yulia on 21.08.2019.
 */
public class ContactModificationTests extends TestBase {

    @Test(enabled = false)
    public void testContactModification() {
        // перейти на главную страницу - там список контактов
        app.getNavigationHelper().gotoHomePage();

        // проверить, есть хоть один контакт на странице, иначе создать новый
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("First name", "Last Name", "8 sovet street, 31", "home phone", "monib phone", "work phone", "fax phone", "email", "email2", null, "test"));
        }

        // создать список before для хранения списка контактов до добавления новых
        int before = app.getContactHelper().getContactCount();

        // создать объект contact с текстом в полях, которые нужно изменить
        ContactData contact = new ContactData("First name", "Last Name", "8 sovet street, 31", "home phone", "monib phone", "work phone", "fax phone", "email", "email2", null, "test");

        /* модифицировать контакт (пока нажать на первый найденный элемнт "карандаш" на странице контактов для
           редактирования данных */
        app.getContactHelper().modificateContact(contact);

        // перейти на главную страницу со списком контактов
        app.getNavigationHelper().gotoHomePage();

        // заполнить список after обновленным списком контактов
        int after = app.getContactHelper().getContactCount();

        // вычислить сколько контактов было до модификации и после. должно совпадать
        Assert.assertEquals(after, before);



    }
}
