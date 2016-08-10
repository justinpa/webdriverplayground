package com.seleniumsimplified.webdriver;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.MarionetteDriver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by juspack on 09/06/16.
 */
public class XPathInterrogationTest {

    private static String marionetteLocation = "/usr/local/marionette/wires-0.7.1-OSX";
    public static WebDriver driver;

    @BeforeClass
    public static void startDriver(){
        System.out.println("running startDriver");
        System.setProperty("webdriver.gecko.driver", marionetteLocation);
        driver = new MarionetteDriver();
    }


    @Test
    public void driverIsTheKingTest(){
        System.out.println("running driverIsTheKingTest");

        try {


            driver.get("http://compendiumdev.co.uk");
            driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_web_page.html");
            WebElement para1 = driver.findElement(By.id("para1"));
            assertTrue("Assert page contains something ", driver.getPageSource().contains("paragraph"));
            assertEquals(para1.getText(), "A paragraph of text");
            System.out.println(para1.getAttribute("id"));


        }catch(NoSuchElementException e){
            System.out.println("Failed");
            fail("driverIsTheKing failed");
        }
    }

    @Test
    public void findElementsTest(){
        System.out.println("running driverIsTheKing");
        int divCount = 0;

        try {

            driver.get("http://compendiumdev.co.uk");
            driver.navigate().to("http://compendiumdev.co.uk/selenium/find_by_playground.php");

            List<WebElement> elements;
            elements = driver.findElements(By.xpath("//p[@id='p3']"));
            System.out.println(elements.size());

            assertTrue("Is there a a tag", elements.size() == 1);


        }catch(NoSuchElementException e){
            System.out.println("Failed");
            fail("findElementsTest failed");
        }
    }




    @AfterClass
    public static void afterTest(){
        System.out.println("running afterTest");
        driver.quit();
    }



}
