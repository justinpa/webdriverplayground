package com.seleniumsimplified.webdriver.tests.pageobjecttests;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import com.seleniumsimplified.webdriver.pageobjects.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by juspack on 09/06/16.
 */
public class AlertExercisePageObjectTest {

    private static AlertPageObject alertPage;


    @BeforeClass
    public static void setupDriver(){
        WebDriver driver = Driver.get();
        alertPage = new AlertPageObject(driver);
        alertPage.get();
    }


    @Test
    public void testClickAlertButtonAndAcceptAlert(){
        //This test clicks OK on an alert

        alertPage.get();
        alertPage.clickAlertButton();
        assertEquals("Assert text change on drop", "I am an alert box!", alertPage.getAlertText());
        alertPage.acceptAlert();
    }


    @Test
    public void testClickConfirmButtonAndDismissAndAccept(){
        //This test clicks OK and cancel on an alert

        alertPage.get();
        alertPage.clickConfirmButton();
        assertEquals("Assert text change on drop", "I am a confirm alert", alertPage.getAlertText());
        alertPage.dismissAlert();
        assertEquals("Assert text change on dismiss", "false", alertPage.getConfirmReturnText());
        alertPage.clickConfirmButton();
        alertPage.acceptAlert();
        assertEquals("Assert text change on accept", "true", alertPage.getConfirmReturnText());
    }


    @Test
    public void testClickPromptAndChangeText(){
        //This test changes text on an alert

        alertPage.get();
        alertPage.clickPromptButton();
        assertEquals("Assert text on prompt", "I prompt you", alertPage.getAlertText());
        alertPage.sendAlertKeys("this is new text");
        alertPage.acceptAlert();
        assertEquals("Assert text change on accept", "this is new text", alertPage.getPromptReturnText());
    }


    @AfterClass
    public static void afterTest(){
        System.out.println("running afterTest");
        Driver.quit();
    }



}
