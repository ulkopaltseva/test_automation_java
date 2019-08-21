package ru.ulko.addressbook.tests;

import org.testng.annotations.*;

public class GroupDeleteTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
  }

}