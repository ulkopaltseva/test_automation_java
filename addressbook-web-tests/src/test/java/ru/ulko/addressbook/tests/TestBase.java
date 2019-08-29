package ru.ulko.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import ru.ulko.addressbook.appmanager.ApplicationManager;

/**
 * Created by yulia on 20.08.2019.
 */
public class TestBase {

    // глобальная переменная app для обращения к браузеру
    protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

    // это выполняется перед каждым тестом
    @BeforeSuite()
    public void setUp() throws Exception {
        app.init(); //выполняется код инициализации, кот. инициализирует глобальную переменную app
    }

    @AfterSuite
    // это выполняется после каждого теста
    public void tearDown() throws Exception {
        app.stop();
    }


}
