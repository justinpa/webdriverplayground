package com.seleniumsimplified.webdriver;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by juspack on 09/06/16.
 */
public class cookiesExercisesTest {

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
    public void cookieTest(){
        /*
         This tests some cookie actions
         */

    try {
        driver.navigate().to("http://compendiumdev.co.uk/selenium/search.php");

        System.out.println("Page title = " + driver.getTitle());

        WebElement search = driver.findElement(By.cssSelector("input[type='text'][name='q']"));
        search.click();
        search.clear();
        search.sendKeys("cookie");

        WebElement searchButton = driver.findElement(By.cssSelector("input[type='submit'][value='Search']"));
        searchButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul[id='resultList']")));

        String cookieValue = driver.manage().getCookieNamed("seleniumSimplifiedLastSearch").getValue();
        System.out.println("Cookie value is:" + cookieValue);

        List<WebElement> results = driver.findElements(By.cssSelector("li[class='resultlistitem']"));
        String firstResultText = results.get(0).findElement(By.cssSelector("em")).getText();

        assertEquals("Check first result", "Cookie Clicker - DashNet", firstResultText);

        results.get(0).findElement(By.cssSelector("a")).click();

    }catch(NoSuchElementException e){
        System.out.println("Failed");
        fail("Test failed");
    }
}


    @Test
    public void cookieBuildTest(){
        /*
         This tests some cookie actions
         */

        try {
            driver.navigate().to("http://compendiumdev.co.uk/selenium/search.php");

            System.out.println("Page title = " + driver.getTitle());

            Cookie myNewCookie = new Cookie.Builder("newcookie","newvalue").build();

            Cookie getMyNewCookie = driver.manage().getCookieNamed("newcookie");

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
