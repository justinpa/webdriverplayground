package com.seleniumsimplified.webdriver;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by juspack on 09/06/16.
 */
public class NavigationTest {


    private static String marionetteLocation = "/usr/local/marionette/geckodriver-0.9.0-OSX";
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public static void startDriver(){
        System.out.println("running startDriver");

        System.setProperty("webdriver.gecko.driver", marionetteLocation);

        //The folowing is for gecko 0.9.0
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        driver = new FirefoxDriver(capabilities);

        //driver = new MarionetteDriver();
        driver.get("http://compendiumdev.co.uk");
        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_html_form.html");
        assertEquals("Assert initial page title", driver.getTitle(), "HTML Form Elements");

        //Setup the waiting time
        wait = new WebDriverWait(driver,10);
        driver.get("http://compendiumdev.co.uk");
        
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
