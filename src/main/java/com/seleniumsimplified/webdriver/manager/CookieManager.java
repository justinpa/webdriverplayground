package com.seleniumsimplified.webdriver.manager;

import org.openqa.selenium.WebDriver;

/**
 * Created by juspack on 31/08/16.
 */
public class CookieManager {

    private WebDriver driver;

    public driver(WebDriver aDriver) {
        driver = aDriver;
    }

    public void get(String cookieName){
        String cookieValue = driver.manage().getCookieNamed(cookieName).getValue();
        System.out.println("Cookie value is:" + cookieValue);
    }
}
