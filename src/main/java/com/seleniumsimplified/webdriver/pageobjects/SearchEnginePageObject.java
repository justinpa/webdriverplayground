package com.seleniumsimplified.webdriver.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

/**
 * Created by juspack on 31/08/16.
 */
public class SearchEnginePageObject {

    private WebDriver driver;
    public static WebDriverWait wait;
    public List<WebElement> results;


    public SearchEnginePageObject(WebDriver aDriver){
        driver = aDriver;
        wait = new WebDriverWait(driver,10);
    }

    public void get(){
        driver.get("http://compendiumdev.co.uk/selenium/search.php");
        System.out.println("Page title = " + driver.getTitle());
    }

    public void enterSearchQuery(String searchQuery){
        WebElement search = driver.findElement(By.cssSelector("input[type='text'][name='q']"));
        search.click();
        search.clear();
        search.sendKeys(searchQuery);
    }

    public void submitSearchQuery() {
        WebElement searchButton = driver.findElement(By.cssSelector("input[type='submit'][value='Search']"));
        searchButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul[id='resultList']")));
    }

    public String getSearchResultText(int indexOfSearchResult) {
        results = driver.findElements(By.cssSelector("li[class='resultlistitem']"));
        String firstResultText = results.get(indexOfSearchResult).findElement(By.cssSelector("em")).getText();
        return firstResultText;
    }

    public void clickSearchResultLink(int indexOfSearchResult) {
        results.get(indexOfSearchResult).findElement(By.cssSelector("a")).click();
    }

}
