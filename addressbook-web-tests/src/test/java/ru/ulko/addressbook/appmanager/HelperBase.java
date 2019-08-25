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

    // метод для заполнения полей ввода
    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = driver.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(text);
            }
        }
    }

    // метод для клика по элементу с определенным локатором
    protected void click(By locator) {
        driver.findElement(locator).click();
    }


    // метод для проверки, есть ли на странице определенный элемент
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

    // метод для доступа к веб-драйверу из тестов
    public WebDriver getDriver() {
        return this.driver;
    }


}
