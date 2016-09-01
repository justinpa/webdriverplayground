package com.seleniumsimplified.webdriver.tests.pageobjecttests;

import com.seleniumsimplified.webdriver.manager.CookieManager;
import com.seleniumsimplified.webdriver.pageobjects.*;
import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

//Import driver and test environment above


/**
 * Created by juspack on 09/06/16.
 */


public class CookiesExercisePageObjectTest {

    private static SearchEnginePageObject searchEngingPage;
    private static CookieManager cookieManager;

    @BeforeClass
    public static void setupDriver(){
        WebDriver driver = Driver.get();
        searchEngingPage = new SearchEnginePageObject(driver);
        searchEngingPage.get();
        cookieManager = new CookieManager(driver);

    }


    @Test
    public void testGetCookieText(){
        /*
         This tests some cookie actions
         */
        searchEngingPage.enterSearchQuery("cookie");
        searchEngingPage.submitSearchQuery();
        cookieManager.getCookieNamed("seleniumSimplifiedLastSearch");
        String firstResultText = searchEngingPage.getSearchResultText(0);
        assertEquals("Check first result", "Cookie Clicker - DashNet", firstResultText);
        searchEngingPage.clickSearchResultLink(0);
    }

    @Test
    public void testBuildCookie(){
        //This test builds a cookie in the browser
        searchEngingPage.get();
        cookieManager.deleteCookie("buildCookieTestCookie");
        cookieManager.createCookie("buildCookieTestCookie", "buildCookieTestCookie_value");
        cookieManager.getCookieNamed("buildCookieTestCookie");
    }

    @AfterClass
    public static void afterTest(){
        System.out.println("running afterTest");
        Driver.quit();
    }


}
