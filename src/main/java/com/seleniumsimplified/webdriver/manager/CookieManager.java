package com.seleniumsimplified.webdriver.manager;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

/**
 * Created by juspack on 31/08/16.
 */
public class CookieManager {

    private WebDriver driver;

    public CookieManager(WebDriver aDriver) {
        driver = aDriver;
    }

    public void getCookieNamed(String cookieName){
        String cookieValue = driver.manage().getCookieNamed(cookieName).getValue();
        System.out.println("Cookie " + cookieName + " value is:" + cookieValue);
    }

    public void deleteCookie(String cookieNameToDelete) {
        Cookie cookieToDeleteCookie = driver.manage().getCookieNamed(cookieNameToDelete);
        if (cookieToDeleteCookie!=null){
            driver.manage().deleteCookie(cookieToDeleteCookie);
        }else{
            System.out.println("No cookie named " + cookieNameToDelete + " found, therefore no cookie to delete.");
        }
    }

    public void createCookie(String cookieName, String cookieValue) {
        Cookie newCookie = new Cookie.Builder(cookieName,cookieValue).build();
        driver.manage().addCookie(newCookie);
    }
}
