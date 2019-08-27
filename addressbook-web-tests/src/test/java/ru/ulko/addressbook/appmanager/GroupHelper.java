package ru.ulko.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.ulko.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yulia on 20.08.2019.
 */
public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    // заполнить поля создания или модификации группы
    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());

    }

    // нажать на добавление новой группы
    public void initGroupCreation() {
        click(By.name("new"));
    }

    // подтвердить сохранение новой группы
    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    // удалить выбранную группу
    public void deleteSelectedGroups() {
        click(By.name("delete"));
        returnGroupPage();

    }

    // нажать чек-бокс первой в списке группы
    public void selectGroup() {
        click(By.name("selected[]"));
    }

    // нажать на кнопку edit для выбранной группы
    public void initGroupModification() {
        click(By.name("edit"));
    }

    // нажать на кнопку update для сохранения изменений при создании/модификации группы
    public void submitGroupModification() {
        click(By.name("update"));
    }

    // создать группу
    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnGroupPage();
    }


    // перейти по ссылке возврата на страницу групп
    public void returnGroupPage() {
        click(By.linkText("group page"));
    }

    // проверить, есть ли хоть одна группа в списке
    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    // посчитать количество групп на странице
    public int getGroupCount() {
        return getDriver().findElements(By.name("selected[]")).size();
    }


    // редактировать группу
    public void modificateGroup(GroupData groupData, int index) {
        selectGroup(index);
        initGroupModification();
        fillGroupForm(groupData);
        submitGroupModification();
        returnGroupPage();
    }

    // проставить чек-бокс у группы с определенным индексом
    public void selectGroup(int index) {
        getDriver().findElements(By.name("selected[]")).get(index).click();
    }

    // вернуть список всех групп на странице
    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<>();
        List<WebElement> elements = getDriver().findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            // нахождение по тегу input с атрибутом value айдишника группы и преобразование в int
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = element.getText();
            GroupData group = new GroupData(id, name, null, null);
            groups.add(group);
        }
        return groups;
    }
}
