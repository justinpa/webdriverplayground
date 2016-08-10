package com.seleniumsimplified.webdriver;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by juspack on 09/06/16.
 */
public class NavigationTest {

    private static String marionetteLocation = "/usr/local/marionette/wires-0.7.1-OSX";
    private static String myString="HelloWorld";
    private static int expectedResult = 1;
    private static int actualResult = 1;
    public static WebDriver driver;

    @BeforeClass
    public static void startDriver(){
        System.out.println("running startDriver");
        System.setProperty("webdriver.gecko.driver", marionetteLocation);
        driver = new MarionetteDriver();
    }

    /*
    private static String marionetteLocation = "/usr/local/marionette/wires-0.7.1-OSX";
    static WebDriver driver = new MarionetteDriver();

    public FirstTest(){
        System.out.println("running Contructor");
    }

    private static void setProperties(){
        System.out.println("running setProperties");
        System.setProperty("webdriver.gecko.driver", marionetteLocation);
    }
*/

    @Test
    public void driverIsTheKing(){
        System.out.println("running driverIsTheKing");

        //assertEquals("My message", expectedResult, actualResult);
        //assertTrue("My message", expectedResult==actualResult);


        driver.get("http://compendiumdev.co.uk");
        driver.navigate().to("http://compendiumdev.co.uk/selenium");
        assertTrue(driver.getTitle().startsWith("Selenium"));
        driver.navigate().to("http://compendiumdev.co.uk/selenium/refresh.php");
        driver.navigate().refresh();
        System.out.println("Current page is " + driver.getCurrentUrl());
        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_web_page.html");
        assertTrue("Assert page contains something ", driver.getPageSource().contains("paragraph"));
        //assertTrue(driver.getTitle().startsWith("Google"));
        //driver.close();
        //driver.quit();

    }


    @AfterClass
    public static void afterTest(){
        System.out.println("running afterTest");
        driver.quit();
    }



}
