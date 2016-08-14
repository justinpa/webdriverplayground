package com.seleniumsimplified.webdriver;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by juspack on 09/06/16.
 */
public class InteractionExercisesTest {

    //private static String marionetteLocation = "/usr/local/marionette/wires-0.7.1-OSX";

    private static String marionetteLocation = "/usr/local/marionette/geckodriver-0.9.0-OSX";



    public static WebDriver driver;

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

    }


    @Test
    public void exerciseOneTest(){
        /*
         This test drags from one place to another
         */

        System.out.println("running exerciseOneTest");

        try {
            driver.navigate().to("http://compendiumdev.co.uk/selenium/gui_user_interactions.html");

            WebElement dragable1 = driver.findElement(By.cssSelector("div[id='draggable1']"));
            WebElement droppable1 = driver.findElement(By.cssSelector("div[id='droppable1']"));

            Actions dragAndDrop = new Actions(driver);
            dragAndDrop.clickAndHold(dragable1).moveToElement(droppable1).release();

            dragAndDrop.perform();

            assertEquals("Assert text change on drop", "Dropped!", droppable1.getText());


        }catch(NoSuchElementException e){
            System.out.println("Failed");
            fail("exerciseSixTest failed");
        }
    }


    @Test
    public void drawOnCanvasTest(){
        /*
         This test drags from one place to another
         */

        System.out.println("running exerciseOneTest");

        try {
            driver.navigate().to("http://compendiumdev.co.uk/selenium/gui_user_interactions.html");

            WebElement canvas = driver.findElement(By.id("canvas"));


            Actions draw = new Actions(driver);
            draw.clickAndHold(canvas).moveByOffset(10,10).release();
            draw.perform();

            System.out.println("Hello");

        }catch(NoSuchElementException e){
            System.out.println("Failed");
            fail("exerciseSixTest failed");
        }
    }





    @AfterClass
    public static void afterTest(){
        System.out.println("running afterTest");
        driver.quit();
    }



}
