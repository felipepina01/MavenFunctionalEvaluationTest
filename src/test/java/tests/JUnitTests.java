package tests;

import common.CommonActions;
import common.CommonProperties;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.GoHopPage;

public class JUnitTests {

//    private static WebDriver driver;
    private static GoHopPage ghPage;

    // Runs once before tests
    @BeforeClass
    public static void setUp() throws MalformedURLException {

        // Check if it's using Selenium Grid (expects node/hub URL) or not
        if (System.getProperty("url") == "") {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setCapability("browserName", "chrome");
            CommonProperties.driver = new RemoteWebDriver(new URL(System.getProperty("url")), chromeOptions);
        }
        else {
            CommonProperties.driver = new ChromeDriver();
        }

        // Implicitly wait for every browser operation
        CommonProperties.driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
        ghPage =  new GoHopPage(CommonProperties.driver);
    }

    // Runs before every test
    @Before
    public void beforeEachTest() {
        // Open gohop website
        CommonProperties.driver.navigate().to("https://www.gohop.ie/");
    }

    // Test = Perform a search
    @Test
    public void performSearchTest() throws InterruptedException {

        // Delete content of departure airport
        CommonActions.clearText(ghPage.txtOrigin);

        // Type Dublin in the departure field - Press enter to dismiss choosing extra options
        CommonActions.enterText(ghPage.txtOrigin, "Dublin");
        CommonActions.enterText(ghPage.txtOrigin, Keys.ENTER);
        CommonActions.enterText(ghPage.txtOrigin, Keys.ENTER);

        // Type Algarve in the destination field - Press enter to select extra (default) options
        CommonActions.enterText(ghPage.txtDestination, "Algarve");
        CommonActions.enterText(ghPage.txtDestination, Keys.ENTER);
        CommonActions.enterText(ghPage.txtDestination, Keys.ENTER);

        // Click the outbound date field
        CommonActions.clickOn(ghPage.cndOutbound);

        // Select outbound date year 2020
        CommonActions.optionTextSelect(ghPage.ddlYear, "2020");

        // Select outbound date month Feb
        CommonActions.optionTextSelect(ghPage.ddlMonth, "Feb");

        // Select outbound date day 20
        ghPage.selectCalendarDay(CommonProperties.driver, 20, GoHopPage.Month.OUTBOUND);

        // Click the return date field
        CommonActions.clickOn(ghPage.cndReturn);

        // Select outbound date day 22 (Keep Feb and 2020 as month and year)
        ghPage.selectCalendarDay(CommonProperties.driver, 22, GoHopPage.Month.FOLLOWING);

        // Click Search button
        CommonActions.clickOn(ghPage.btnSearch);

        // Wait for page to load and check if the results are being displayed
        CommonProperties.waitForPageToBeReady(CommonProperties.driver);
        CommonProperties.waitForElementPresent(CommonProperties.driver, ghPage.lblResults, 180);

        Assert.assertTrue(ghPage.lblResults.isDisplayed());

    }

    // Test = Check “Today’s top deals” section
    @Test
    public void topDealsTest() {

        //Click the first deal
        CommonActions.clickOn(ghPage.lnkFirstTopDeal);

        // Wait for the Search button to be present
        CommonProperties.waitForElementPresent(CommonProperties.driver, ghPage.btnSearchHolidays, 30);

        // Assert search button is being displayed
        Assert.assertTrue(ghPage.btnSearchHolidays.isDisplayed());

    }

    // Test = Test top menu
    @Test
    public void topMenuTest () {

        // Hover over Holidays By Type on top menu
        CommonActions.hoverOver(ghPage.menuHolidayByType);

        // Hover over Honeymoons submenu
        CommonActions.hoverOver(ghPage.menuHoneymoons);

        // Click Honeymoon Packages
        CommonActions.clickOn(ghPage.lnkHoneymoonPackages);

        // Check the Honeymoon Packages page has been properly loaded
        CommonProperties.waitForElementPresent(CommonProperties.driver, ghPage.lblPerfectHoneymoon, 30);
        Assert.assertTrue(ghPage.lblPerfectHoneymoon.isDisplayed());
    }

    // Test = Test quick holidays finder
    @Test
    public void quickHolidayFinderTest() {

        // In the Quick Holiday finder field
        // Select the "Algarve holidays" options
        CommonActions.optionTextSelect(ghPage.ddlHolidayFinder, "Algarve holidays");

        // Check if the Holiday Deals details is properly being displayed
        CommonProperties.waitForElementPresent(CommonProperties.driver, ghPage.sectionHolidayDeals, 30);
        Assert.assertTrue(ghPage.sectionHolidayDeals.isDisplayed());

    }

    // Test = Test Search field
    @Test
    public void searchFieldTest() throws InterruptedException {

        // Search for "Rome Italy"
        CommonActions.enterText(ghPage.txtMainPageSearch, "Rome Italy");
        Thread.sleep(1000);

        // Select “The Perfect Honeymoon - Venice, Florence & Rome” option
        CommonActions.clickOn(ghPage.lnkItalyHoliday);

        //Click magnifying glass search icon
        Thread.sleep(1000);
        CommonActions.clickOn(ghPage.btnMagnGlass);

        // Check Overview tab
        CommonProperties.waitForElementPresent(CommonProperties.driver, ghPage.lblOverviewTab, 30);
        Assert.assertTrue(ghPage.lblOverviewTab.isDisplayed());

    }


    // Test = Try to make a Search without specifying Destination (negative testing)
    @Test
    public void searchNoDestination() {
        // Click Search button
        CommonActions.clickOn(ghPage.btnSearch);

        // A message should be displayed stating that “You must choose a destination point”
        CommonProperties.waitForElementPresent(CommonProperties.driver, ghPage.lblNoDestinationErrorMsg, 30);
        System.out.println(CommonActions.getContent(ghPage.lblNoDestinationErrorMsg));
        Assert.assertTrue(CommonActions.getContent(ghPage.lblNoDestinationErrorMsg).contains("You must choose a destination point"));
    }

    // Runs after all the tests are finished
    @AfterClass
    public static void coolDown() {
        CommonProperties.driver.close();
    }
}