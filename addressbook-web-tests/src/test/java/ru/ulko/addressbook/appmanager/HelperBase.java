package ru.ulko.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by yulia on 21.08.2019.
 */
public class HelperBase {
    protected WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }

    protected void type(By locator, String name) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(name);
    }

    protected void click(By locator) {
        driver.findElement(locator).click();
    }
}
