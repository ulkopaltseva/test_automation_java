package ru.ulko.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.ulko.addressbook.model.GroupData;

/**
 * Created by yulia on 20.08.2019.
 */
public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());

    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnGroupPage();
        int after = getDriver().findElements(By.name("selected[]")).size();
    }

    private void returnGroupPage() {
        click(By.linkText("group page"));
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return getDriver().findElements(By.name("selected[]")).size();
    }
}
