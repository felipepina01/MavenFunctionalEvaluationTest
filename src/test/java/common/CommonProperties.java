package common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonProperties {

    public static WebDriver driver;

    // Wait for the element to be clickable
    public static WebElement waitForElementPresent(WebDriver driver, WebElement element, int timeOutInSeconds) {

        try{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait()

            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(element));

            driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS); //reset implicitlyWait
            return element; //return the element
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Wait for the web page to load by checking Java Script document.readyState property
    public static void waitForPageToBeReady(WebDriver driver)
    {
        JavascriptExecutor js = (JavascriptExecutor)driver;

        for (int i=0; i<4000; i++)
        {
            try
            {
                Thread.sleep(1000);
            }catch (InterruptedException e) {}
            //To check page ready state.

            if (js.executeScript("return document.readyState").toString().equals("complete"))
            {
                break;
            }
        }
    }

}