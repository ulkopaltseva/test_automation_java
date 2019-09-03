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

    protected GroupHelper(WebDriver driver) {
        super(driver);
    }

    private void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());

    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnGroupPage();
    }

    private void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void delete(int index) {
        select(index);
        deleteSelectedGroups();
        returnGroupPage();
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    public void modify(GroupData groupData, int index) {
        select(index);
        initGroupModification();
        fillGroupForm(groupData);
        submitGroupModification();
        returnGroupPage();
    }


    private void returnGroupPage() {
        click(By.linkText("group page"));
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return driver.findElements(By.name("selected[]")).size();
    }

    protected void select(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = element.getText();
            GroupData group = new GroupData().withId(id).withName(name);
            groups.add(group);
        }
        return groups;
    }

}
