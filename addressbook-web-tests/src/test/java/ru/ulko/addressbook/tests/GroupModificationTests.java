package ru.ulko.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ulko.addressbook.model.GroupData;

/**
 * Created by yulia on 21.08.2019.
 */
public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("NAME", "HEADER","FOOTER"));
        }
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().modificateGroup(new GroupData("NAME", "HEADER","FOOTER"), before - 1);
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before);

    }

}
