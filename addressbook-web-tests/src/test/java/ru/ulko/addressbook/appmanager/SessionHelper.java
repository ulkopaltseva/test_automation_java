package ru.ulko.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by yulia on 20.08.2019.
 */
// логин на сайте http://localhost/addressbook
public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver driver) {
        super(driver);
    }


    public void login(String user, String password) {
        type(By.name("user"), user);
        click(By.id("LoginForm"));
        type(By.name("pass"), password);
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]"));
    }
}
