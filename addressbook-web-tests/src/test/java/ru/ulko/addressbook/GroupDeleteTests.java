package ru.ulko.addressbook;

import org.testng.annotations.*;
import org.openqa.selenium.*;

public class GroupDeleteTests extends TestBase{

  @Test
  public void testGroupDeletion() throws Exception {
    gotoGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage();
  }

}
