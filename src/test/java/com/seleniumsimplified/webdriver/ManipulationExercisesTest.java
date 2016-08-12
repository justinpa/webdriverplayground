package com.seleniumsimplified.webdriver;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by juspack on 09/06/16.
 */
public class ManipulationExercisesTest {

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
        System.out.println("running exerciseOneTest");

        try {

            driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_html_form.html");
            WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
            submitButton.click();
            new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));
            assertEquals("Assert submitted page title", "Processed Form Details", driver.getTitle());


        }catch(NoSuchElementException e){
            System.out.println("Failed");
            fail("exerciseOneTest failed");
        }
    }


    @Test
    public void exerciseTwoTest(){
        System.out.println("running exerciseTwoTest");

        try {
            driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_html_form.html");
            WebElement commentArea = driver.findElement(By.cssSelector("textarea[name='comments']"));
            commentArea.clear();
            commentArea.sendKeys("TestComment");

            WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
            submitButton.click();
            new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));
            assertEquals("Assert submitted page title", "Processed Form Details", driver.getTitle());

            WebElement commentResult = driver.findElement(By.cssSelector("li[id='_valuecomments']"));
            assertEquals("Assert submitted comments", "TestComment", commentResult.getText());

        }catch(NoSuchElementException e){
            System.out.println("Failed");
            fail("exerciseTwoTest failed");
        }
    }


    @Test
    public void exerciseThreeTest(){
        /*
         This test submits radio 2 only
         */

        System.out.println("running exerciseThreeTest");

        try {
            driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_html_form.html");

            WebElement radioButton2 = driver.findElement(By.cssSelector("input[type='radio'][value='rd2']"));
            radioButton2.click();

            WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
            submitButton.click();
            new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));
            assertEquals("Assert submitted page title", "Processed Form Details", driver.getTitle());

            WebElement radioResult = driver.findElement(By.cssSelector("li[id='_valueradioval']"));
            assertEquals("Assert submitted radio", "rd2", radioResult.getText());

        }catch(NoSuchElementException e){
            System.out.println("Failed");
            fail("exerciseThreeTest failed");
        }
    }



    @Test
    public void exerciseFourTest(){
        /*
         This test submits checkbox 3 only
         */

        System.out.println("running exerciseFourTest");

        try {
            driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_html_form.html");

            WebElement checkbox1 = driver.findElement(By.cssSelector("input[type='checkbox'][value='cb1']"));
            WebElement checkbox2 = driver.findElement(By.cssSelector("input[type='checkbox'][value='cb2']"));
            WebElement checkbox3 = driver.findElement(By.cssSelector("input[type='checkbox'][value='cb3']"));

            if (checkbox1.isSelected()){
                checkbox1.click();
            }

            if (checkbox2.isSelected()){
                checkbox2.click();
            }

            if (!checkbox3.isSelected()){
                checkbox3.click();
            }

            WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
            submitButton.click();
            new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));
            assertEquals("Assert submitted page title", "Processed Form Details", driver.getTitle());

            WebElement checkboxResult = driver.findElement(By.id("_valuecheckboxes0"));
            assertEquals("Assert submitted checkbox", "cb3", checkboxResult.getText());

        }catch(NoSuchElementException e){
            System.out.println("Failed");
            fail("exerciseFourTest failed");
        }
    }



    @Test
    public void exerciseFiveTest(){
        /*
         This test submits selected dropdown item 5 only
         */

        System.out.println("running exerciseFiveTest");

        try {
            driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_html_form.html");

            //WebElement dropDown = driver.findElement(By.cssSelector("select[name='dropdown']"));
            //dropDown.click();
            WebElement dropDownOption = driver.findElement(By.cssSelector("option[value='dd5']"));
            dropDownOption.click();

            WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
            submitButton.click();
            new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));
            assertEquals("Assert submitted page title", "Processed Form Details", driver.getTitle());

            WebElement dropdownResult = driver.findElement(By.cssSelector("li[id='_valuedropdown']"));
            assertEquals("Assert submitted dropdown", "dd5", dropdownResult.getText());

        }catch(NoSuchElementException e){
            System.out.println("Failed");
            fail("exerciseFiveTest failed");
        }
    }


    @Test
    public void exerciseSixTest(){
        /*
         This test submits multiselect options 1,2,3 but not 4
         */

        System.out.println("running exerciseSixTest");

        try {
            driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_html_form.html");

            WebElement multiSelect = driver.findElement(By.cssSelector("select[multiple='multiple']"));
            List<WebElement> multiSelectOptions = multiSelect.findElements(By.tagName("option"));

            multiSelectOptions.get(0).click();
            multiSelectOptions.get(1).click();
            multiSelectOptions.get(2).click();
            if (multiSelectOptions.get(3).isSelected()){
                multiSelectOptions.get(3).click();
            }

            /*

            Actions builder = new Actions(driver);
            builder.keyDown(multiSelectOptions.get(0),Keys.CONTROL);
            builder.build().perform();

            Select selectBox = new Select(driver.findElement(By.cssSelector("select[multiple='multiple']")));
            selectBox.selectByIndex(0);
            selectBox.selectByIndex(1);
            selectBox.selectByIndex(2);

            builder.keyUp(Keys.LEFT_CONTROL);
            builder.build().perform();

            */

            WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
            submitButton.click();
            new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));
            assertEquals("Assert submitted page title", "Processed Form Details", driver.getTitle());

            WebElement multiResult1 = driver.findElement(By.cssSelector("li[id='_valuemultipleselect0']"));
            WebElement multiResult2 = driver.findElement(By.cssSelector("li[id='_valuemultipleselect1']"));
            WebElement multiResult3 = driver.findElement(By.cssSelector("li[id='_valuemultipleselect2']"));

            assertEquals("Assert submitted dropdown", "ms1", multiResult1.getText());
            assertEquals("Assert submitted dropdown", "ms2", multiResult2.getText());
            assertEquals("Assert submitted dropdown", "ms3", multiResult3.getText());


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
