package com.seleniumsimplified.webdriver.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by juspack on 31/08/16.
 */
public class AlertPageObject {

    private WebDriver driver;
    public static WebDriverWait wait;

    public AlertPageObject(WebDriver aDriver){
        driver = aDriver;
        wait = new WebDriverWait(driver,10);
    }

    public void get(){
        driver.get("http://compendiumdev.co.uk/selenium/alerts.html");
        System.out.println("Page title = " + driver.getTitle());
    }

    public void clickAlertButton(){
        WebElement alertBoxButton = driver.findElement(By.id("alertexamples"));
        alertBoxButton.click();
    }

    public void clickConfirmButton(){
        WebElement alertButton = driver.findElement(By.id("confirmexample"));
        alertButton.click();
    }

    public void clickPromptButton(){
        WebElement promptButton = driver.findElement(By.id("promptexample"));
        promptButton.click();
    }

    public String getAlertText(){
        return driver.switchTo().alert().getText();
    }

    public String getConfirmReturnText(){
        WebElement confirmReturn = driver.findElement(By.id("confirmreturn"));
        return confirmReturn.getText();
    }

    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }

    public void dismissAlert(){
        driver.switchTo().alert().dismiss();
    }

    public void sendAlertKeys(String keysToSend){
        driver.switchTo().alert().sendKeys(keysToSend);
    }

    public String getPromptReturnText(){
        WebElement promptReturn = driver.findElement(By.id("promptreturn"));
        return promptReturn.getText();
    }

}
