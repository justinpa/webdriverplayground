package com.seleniumsimplified.webdriver.tests.standalonetests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by juspack on 09/06/16.
 */
public class syncExercisesTest {

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
    public void syncAjaxTest(){
        /*
         This test syncs with some ajax actions
         */

    try {
        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_ajax.html");

        System.out.println("Page title = " + driver.getTitle());

        WebElement categoryCombo = driver.findElement(By.id("combo1"));
        List<WebElement> categoryComboOptions = categoryCombo.findElements(By.tagName("option"));
        categoryComboOptions.get(1).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select[name='language_id']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("option[value='12']")));


        WebElement languageCombo = driver.findElement(By.cssSelector("select[name='language_id']"));
        List<WebElement> languageComboOptions = languageCombo.findElements(By.tagName("option"));
        languageComboOptions.get(2).click();

        WebElement submitButton = driver.findElement(By.cssSelector("input[name='submitbutton']"));
        submitButton.click();

        wait.until(ExpectedConditions.titleIs("Processed Form Details"));

        assertEquals("Check page title", "Processed Form Details", driver.getTitle());


    }catch(NoSuchElementException e){
        System.out.println("Failed");
        fail("Test failed");
    }
}





    @AfterClass
    public static void afterTest(){
        System.out.println("running afterTest");
        driver.quit();
    }


}
