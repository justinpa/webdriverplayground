package com.seleniumsimplified.webdriver;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by juspack on 09/06/16.
 */
public class ManipulationExercisesTest {

    private static String marionetteLocation = "/usr/local/marionette/wires-0.7.1-OSX";
    public static WebDriver driver;

    @BeforeClass
    public static void startDriver(){
        System.out.println("running startDriver");
        System.setProperty("webdriver.gecko.driver", marionetteLocation);
        driver = new MarionetteDriver();
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





    @AfterClass
    public static void afterTest(){
        System.out.println("running afterTest");
        driver.quit();
    }



}
