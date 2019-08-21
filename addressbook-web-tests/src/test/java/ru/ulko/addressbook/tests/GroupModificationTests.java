package ru.ulko.addressbook.tests;

import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;

/**
 * Created by yulia on 21.08.2019.
 */
public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("NAME", "HEADER","FOOTER"));
        app.getGroupHelper().submitGroupModification();
    }

}
