package ru.ulko.addressbook.tests;

import org.testng.annotations.*;
import ru.ulko.addressbook.model.GroupData;

public class GroupDeleteTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("name", "header","footer"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
  }

}
