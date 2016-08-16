package com.seleniumsimplified.webdriver;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by juspack on 09/06/16.
 */
public class WindowsTabsExercisesTest {

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
    public void windowTest(){
        /*
         This test clicks around on frames
         */

        System.out.println("running frameTest");

        try {
            driver.navigate().to("http://compendiumdev.co.uk/selenium/frames");
            driver.switchTo().frame("content");

            System.out.println("Page title = " + driver.getTitle());

            String originalWindowHandle = driver.getWindowHandle();
            String secondaryWindowHandle = originalWindowHandle;

            System.out.println("originalWindowHandle=" + originalWindowHandle);

            WebElement linkToClick = driver.findElement(By.cssSelector("a[id='goevil']"));



            //WebElement linkToClick = driver.findElement(By.linkText("Load green page"));
            linkToClick.click();

            System.out.println(driver.getWindowHandles().size());
            Iterator<String> myWindows = driver.getWindowHandles().iterator();

            while (secondaryWindowHandle.equals(originalWindowHandle)){
                secondaryWindowHandle = myWindows.next();
                System.out.print("secondaryWindowHandle=" + secondaryWindowHandle);
            }

            System.out.println(secondaryWindowHandle);

            driver.switchTo().window(secondaryWindowHandle);

            System.out.println("Page title = " + driver.getTitle());
            driver.close();
            driver.switchTo().window(originalWindowHandle);

            //Example of waiting until some text shown
            //wait.until(presenceOfElementLocated(By.cssSelector("h1[id='green']")));

            //driver.switchTo().frame("content");
            //WebElement frameTitle = driver.findElement(By.cssSelector("h1[id='green']"));
            //assertEquals("Assert text on frame", "Green Page", frameTitle.getText());
            //WebElement linkToClickBack = driver.findElement(By.linkText("Back to original page"));
            //linkToClickBack.click();
            


        }catch(NoSuchElementException e){
            System.out.println("Failed");
            fail("frameTest failed");
        }
    }




    @Test
    public void manageWindowTest(){
        /*
         This test manages the window
         */

    System.out.println("running manageWindowTest");

    try {
        driver.navigate().to("http://compendiumdev.co.uk/selenium/frames");
        driver.switchTo().frame("content");

        System.out.println("Page title = " + driver.getTitle());


        Dimension originalDimention = driver.manage().window().getSize();
        driver.manage().window().setPosition(new Point(10, 20));
        driver.manage().window().setSize(new Dimension(400,350));
        driver.manage().window().setSize(originalDimention);
        driver.manage().window().maximize();


    }catch(NoSuchElementException e){
        System.out.println("Failed");
        fail("manageWindowTest failed");
    }
}




    @AfterClass
    public static void afterTest(){
        System.out.println("running afterTest");
        driver.quit();
    }


}
