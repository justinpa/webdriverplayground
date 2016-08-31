package com.seleniumsimplified.webdriver.tests;

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


public class cookiesExercisesPageObjectTest {

    //private Driver driver;
    private static SearchEnginePageObject searchEngingPage;
    private static CookieManager cookieManager;

    @BeforeClass
    public static void setupDriver(){
        WebDriver driver = Driver.get();
        searchEngingPage = new SearchEnginePageObject(driver);
        searchEngingPage.get();
    }


    @Test
    public void cookieTest(){
        /*
         This tests some cookie actions
         */
        try {
            searchEngingPage.enterSearchQuery("cookie");
            searchEngingPage.submitSearchQuery();
            cookieManager.get("seleniumSimplifiedLastSearch");
            String firstResultText = searchEngingPage.getSearchResultText(0);
            assertEquals("Check first result", "Cookie Clicker - DashNet", firstResultText);
            searchEngingPage.clickSearchResultLink();

        }catch(NoSuchElementException e){
            System.out.println("Failed");
            fail("Test failed");
        }
    }



    @AfterClass
    public static void afterTest(){
        System.out.println("running afterTest");
        Driver.quit();
    }


}