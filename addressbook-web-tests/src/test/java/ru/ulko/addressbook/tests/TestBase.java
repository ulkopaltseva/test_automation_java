package ru.ulko.addressbook.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.ulko.addressbook.appmanager.ApplicationManager;

/**
 * Created by yulia on 20.08.2019.
 */
public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterClass
    public void tearDown() throws Exception {
        app.stop();
    }


}
