package ru.ulko.addressbook.appmanager;

import org.openqa.selenium.*;

/**
 * Created by yulia on 21.08.2019.
 */
public class HelperBase {
    private WebDriver driver;
    public boolean acceptNextAlert = true;

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

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
