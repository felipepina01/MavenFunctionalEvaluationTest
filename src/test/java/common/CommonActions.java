package common;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CommonActions {

    //Typing text on a text field
    public static void enterText(WebElement element, String text) {
        CommonProperties.waitForElementPresent(CommonProperties.driver, element, 120).sendKeys(text);
    }

    // Overloading enterText with Keys instead of String
    public static void enterText(WebElement element, Keys key) throws InterruptedException {
        Thread.sleep(500);
        CommonProperties.waitForElementPresent(CommonProperties.driver, element, 120).sendKeys(key);
    }

    // Clear text from a text field
    public static void clearText(WebElement element) {
        CommonProperties.waitForElementPresent(CommonProperties.driver, element, 120).clear();
    }

    //Click on a link, button, etc.
    public static void clickOn(WebElement element) {
        CommonProperties.waitForElementPresent(CommonProperties.driver, element, 120).click();
        CommonProperties.waitForPageToBeReady(CommonProperties.driver);
    }

    //Hover over elements, such as menus
    public static void hoverOver(WebElement element) {
        CommonProperties.waitForElementPresent(CommonProperties.driver, element, 120);
        Actions action = new Actions(CommonProperties.driver);
        action.moveToElement(element).build().perform();
    }

    //Select option in a combobox
    public static void optionTextSelect(WebElement element, String value) {
        //Select combo = new Select(CommonProperties.waitForElementPresent(CommonProperties.driver, element, 120));
        Select combo = new Select(element);
        combo.selectByVisibleText(value);
        CommonProperties.waitForPageToBeReady(CommonProperties.driver);
    }

    //Get the text of a label, text field, etc.
    public static String getContent(WebElement element) {
        return CommonProperties.waitForElementPresent(CommonProperties.driver, element, 120).getText();
    }
}