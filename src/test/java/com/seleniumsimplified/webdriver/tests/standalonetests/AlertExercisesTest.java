package com.seleniumsimplified.webdriver.tests.standalonetests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by juspack on 09/06/16.
 */
public class AlertExercisesTest {

    //private static String marionetteLocation = "/usr/local/marionette/wires-0.7.1-OSX";

    private static String marionetteLocation = "/usr/local/marionette/geckodriver-0.18.0-OSX";



    public static WebDriver driver;

    @BeforeClass
    public static void startDriver(){
        System.out.println("running startDriver");
        System.setProperty("webdriver.gecko.driver", marionetteLocation);

        //The folowing is for gecko 0.18.0
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        driver = new FirefoxDriver(capabilities);


        //driver = new MarionetteDriver();
        driver.get("http://compendiumdev.co.uk");
        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_html_form.html");
        assertEquals("Assert initial page title", driver.getTitle(), "HTML Form Elements");

    }


    @Test
    public void exerciseOneTest(){
        /*
         This test clicks OK on an alert
         */

        System.out.println("running exerciseOneTest");

        try {
            driver.navigate().to("http://compendiumdev.co.uk/selenium/alerts.html");

            WebElement alertButton = driver.findElement(By.id("alertexamples"));
            alertButton.click();

            assertEquals("Assert text change on drop", "I am an alert box!", driver.switchTo().alert().getText());

            driver.switchTo().alert().accept();


        }catch(NoSuchElementException e){
            System.out.println("Failed");
            fail("exerciseOneTest failed");
        }
    }


    @Test
    public void confirmExampleTest(){
        /*
         This test clicks OK and cancel on an alert
         */

        System.out.println("running confirmExampleTest");

        try {
            driver.navigate().to("http://compendiumdev.co.uk/selenium/alerts.html");

            WebElement alertButton = driver.findElement(By.id("confirmexample"));
            alertButton.click();

            assertEquals("Assert text change on drop", "I am a confirm alert", driver.switchTo().alert().getText());

            driver.switchTo().alert().dismiss();

            WebElement confirmReturn = driver.findElement(By.id("confirmreturn"));
            assertEquals("Assert text change on dismiss", "false", confirmReturn.getText());

            alertButton.click();
            driver.switchTo().alert().accept();
            assertEquals("Assert text change on accept", "true", confirmReturn.getText());



        }catch(NoSuchElementException e){
            System.out.println("Failed");
            fail("confirmExampleTest failed");
        }
    }


    @Test
    public void changeTextTest(){
        /*
         This test changes text on an alert
         */

        System.out.println("running changeTextTest");

        try {
            driver.navigate().to("http://compendiumdev.co.uk/selenium/alerts.html");

            WebElement alertButton = driver.findElement(By.id("promptexample"));
            alertButton.click();

            assertEquals("Assert text on prompt", "I prompt you", driver.switchTo().alert().getText());

            driver.switchTo().alert().sendKeys("this is new text");
            driver.switchTo().alert().accept();

            WebElement promptReturn = driver.findElement(By.id("promptreturn"));
            assertEquals("Assert text change on accept", "this is new text", promptReturn.getText());


        }catch(NoSuchElementException e){
            System.out.println("Failed");
            fail("changeTextTest failed");
        }
    }


    @AfterClass
    public static void afterTest(){
        System.out.println("running afterTest");
        driver.quit();
    }



}
