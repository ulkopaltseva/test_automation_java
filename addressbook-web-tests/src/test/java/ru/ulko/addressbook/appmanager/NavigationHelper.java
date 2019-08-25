package ru.ulko.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by yulia on 20.08.2019.
 */

// переход по страницам сайта из любого места
public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    // перейти на страницу групп
    public void gotoGroupPage() {
        // проверка, какая страница уже открыта, если группы, выйти из метода
        if (isElementPresent(By.tagName("h1"))
                && getDriver().findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }

        // перейти на страницу группы, нажатием на меню в шапке сайта
        click(By.linkText("groups"));
    }

    // перейти на главную страницу (контакты)
    public void gotoHomePage() {
        // проверка, какая страница уже открыта, если контакты, выйти из метода
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    // принудительно перейти на страницу контакты без проверки, не открыта ли она уже
    public void gotoHomePageWithoutCheck() {
        click(By.linkText("home"));
    }
}
