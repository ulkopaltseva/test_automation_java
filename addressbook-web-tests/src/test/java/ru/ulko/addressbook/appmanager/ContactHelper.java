package ru.ulko.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.ulko.addressbook.model.ContactData;

/**
 * Created by yulia on 20.08.2019.
 */
public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    // нажть на кнопку для сохранения изменений при создании / модификации контакта
    public void submitCreationContact() {
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]"));
    }


    // нажать на add new на странице сайта для создания контакта
    public void initCreateContact() {
        click(By.linkText("add new"));
    }

    // заполнить поля в контакте
    public void fillContactData(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("fax"), contactData.getFaxPhone());
        type(By.name("email"), contactData.getEmail1());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());

        // если это создание контакта - заполнить поле группа
        if (creation == true) {
            new Select(getDriver().findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            // если это редактирование контакта, создать исключение, если поле группы все равно есть (не должно быть)
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    // выбрать первый контакт в списке
    public void selectContact() {
        click(By.name("selected[]"));
    }

    // нажать на кнопку удалить для выбранного контакта
    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    // нажать на значок карандаша для редактирования контакта
    public void initModificationContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    // нажать на кнопку update при редактировании контакта для сохранения изменений
    public void submitModificationContact() {
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Edit / add address book entry'])[1]/following::div[1]"));
    }

    // метод для модификации контакта, передать объект ContactData с заполненными полями
    public void modificateContact(ContactData contact) {
        // инициировать модификацию - нажать на "карандаш" в строке контакта
        initModificationContact();

        /* заполнить данные контакта переданным объектом ContactData и передать false для переменной creation
           эта переменная нужна, так как при создании контакта есть поле выбора групп,
           а при редактировании контакта нет поля выбора групп
         */
        fillContactData(contact, false);

        // нажать на кнопку update для сохранения измененных данных
        submitModificationContact();
    }

    // создать контакт
    public void createContact(ContactData contact) {
        initCreateContact();
        fillContactData(contact, true);
        submitCreationContact();
        returnHomePage();
    }

    // нажать на ссылку - вернуться на домашнюю страницу (контакты)
    public void returnHomePage() {
        click(By.linkText("home page"));
    }

    // проверка, есть ли контакты на странице контактов
    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    // посчитать количество контактов на странице
    public int getContactCount() {
        return getDriver().findElements(By.name("selected[]")).size();
    }


}
