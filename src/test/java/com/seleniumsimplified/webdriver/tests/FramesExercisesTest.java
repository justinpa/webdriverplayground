package com.seleniumsimplified.webdriver.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by juspack on 09/06/16.
 */
public class FramesExercisesTest {

    //private static String marionetteLocation = "/usr/local/marionette/wires-0.7.1-OSX";

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


    }


    @Test
    public void frameTest(){
        /*
         This test clicks around on frames
         */

        System.out.println("running frameTest");

        try {
            driver.navigate().to("http://compendiumdev.co.uk/selenium/frames");
            driver.switchTo().frame("content");

            WebElement linkToClick = driver.findElement(By.linkText("Load green page"));
            linkToClick.click();

            //Example of waiting until some text shown
            wait.until(presenceOfElementLocated(By.cssSelector("h1[id='green']")));

            //driver.switchTo().frame("content");
            WebElement frameTitle = driver.findElement(By.cssSelector("h1[id='green']"));
            assertEquals("Assert text on frame", "Green Page", frameTitle.getText());
            WebElement linkToClickBack = driver.findElement(By.linkText("Back to original page"));
            linkToClickBack.click();



        }catch(NoSuchElementException e){
            System.out.println("Failed");
            fail("frameTest failed");
        }
    }


    @AfterClass
    public static void afterTest(){
        System.out.println("running afterTest");
        driver.quit();
    }


}
