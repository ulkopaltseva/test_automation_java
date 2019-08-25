package ru.ulko.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

/**
 * Created by yulia on 20.08.2019.
 */
public class ApplicationManager {
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
    public WebDriver driver;
    public String baseUrl;
    public StringBuffer verificationErrors = new StringBuffer();
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        // выбрать браузер для запуска тестов
        if (browser.equals(BrowserType.FIREFOX)) {
            driver = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            driver = new ChromeDriver();
        } else if (browser.equals(BrowserType.IEXPLORE)) {
            driver = new InternetExplorerDriver();
        }
        baseUrl = "https://www.katalon.com/";

        // имплицидное ожидание - ожидание, если страница не сразу прогрузилась
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        // с какой страницы начать запуск тестов
        driver.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        sessionHelper = new SessionHelper(driver);
        contactHelper = new ContactHelper(driver);

        // залогиниться на сайте
        sessionHelper.login("admin", "secret");
    }


    // закончить работу с веб-драйвером
    public void stop() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    // метод для выбора "да" во всплывающем окне
    public void closeAlert() {
        driver.switchTo().alert().accept();
    }


    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }


}
