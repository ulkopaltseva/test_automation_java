package ru.ulko.addressbook.tests;

import org.testng.annotations.*;

import org.openqa.selenium.*;
import ru.ulko.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.gotoGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("name", "header","footer"));
        app.driver.findElement(By.name("submit")).click();
    }

}
