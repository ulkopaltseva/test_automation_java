package ru.ulko.addressbook;

import org.testng.annotations.*;

import org.openqa.selenium.*;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() throws Exception {
        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("name", "header","footer"));
        driver.findElement(By.name("submit")).click();
        returnToGroupPage();
    }

}
