package ru.ulko.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.ulko.addressbook.model.GroupData;
import ru.ulko.addressbook.model.Groups;

import java.util.List;

/**
 * Created by yulia on 20.08.2019.
 */
public class GroupHelper extends HelperBase {

    protected GroupHelper(WebDriver driver) {
        super(driver);
    }

    private void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());

    }

    private void selectById(int id) {
        driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnGroupPage();
        groupCache = null;
    }

    private void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void deleteById(GroupData deletedGroup) {
        selectById(deletedGroup.getId());
        deleteSelectedGroups();
        returnGroupPage();
        groupCache = null;
    }


    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    public void modify(GroupData group) {
        selectById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnGroupPage();
        groupCache = null;
    }


    private void returnGroupPage() {
        click(By.linkText("group page"));
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return driver.findElements(By.name("selected[]")).size();
    }

    private Groups groupCache = null;

    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = element.getText();
            GroupData group = new GroupData().withId(id).withName(name);
            groupCache.add(group);
        }

        return groupCache;
    }

}
