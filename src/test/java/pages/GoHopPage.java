package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class GoHopPage {

    public GoHopPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public enum Month
    {
        OUTBOUND, FOLLOWING;
    }

    @FindBy(how = How.ID, using="sb3_cc_holiday_search_sb3_flighthotel_iataFrom")
    public WebElement txtOrigin;

    @FindBy(how = How.ID, using="sb3_cc_holiday_search_sb3_flighthotel_city")
    public WebElement txtDestination;

    @FindBy(how = How.ID, using="sb3_cc_holiday_search_sb3_flighthotel_outboundDate")
    public WebElement cndOutbound;

    @FindBy(how = How.CLASS_NAME, using="ui-datepicker-year")
    public WebElement ddlYear;

    @FindBy(how = How.CLASS_NAME, using="ui-datepicker-month")
    public WebElement ddlMonth;

    @FindBy(how = How.ID, using="sb3_cc_holiday_search_sb3_flighthotel_search_button")
    public WebElement btnSearch;

    @FindBy(how = How.CLASS_NAME, using="nbf_fancyimg_results_pageheader")
    public WebElement lblResults;

    @FindBy(how = How.ID, using="sb3_cc_holiday_search_returnDate_container")
    public WebElement cndReturn;

    @FindBy(how = How.XPATH, using="//div[@id=\"row_one_offers_iter_1_row_one_offers_imagegradient\"]//parent::a")
    public WebElement lnkFirstTopDeal;

    @FindBy(how = How.ID, using = "sb3_holiday_search_sb3_flighthotel_search_button")
    public WebElement btnSearchHolidays;

    @FindBy(how = How.ID, using = "level1_10_level1_item")
    public  WebElement menuHolidayByType;

    @FindBy(how = How.ID, using = "level1_10_level2_1_level2_item")
    public WebElement menuHoneymoons;

    @FindBy(how = How.ID, using = "level1_10_level2_1_level3_1_level3_item")
    public WebElement lnkHoneymoonPackages;

    @FindBy(how = How.XPATH, using = "//span[text()=\"Plan your perfect Honeymoon\"]")
    public WebElement lblPerfectHoneymoon;

    @FindBy(how = How.NAME, using = "menu1")
    public WebElement ddlHolidayFinder;

    @FindBy(how = How.ID, using = "tf_holidaydeals_copy")
    public WebElement sectionHolidayDeals;

    @FindBy(how = How.ID, using = "searchtext_cc_customersearch_sitesearch_copy")
    public WebElement txtMainPageSearch;

    @FindBy(how = How.XPATH, using = "//bdi[contains(.,'The Perfect Honeymoon – Venice, Florence & Rome')]")
    public WebElement lnkItalyHoliday;

    @FindBy(how = How.CSS, using = ".nbf_tpl_quicksearch_button")
    public WebElement btnMagnGlass;

    @FindBy(how = How.XPATH, using = "//a[@class=\"link\" and text()=\"Overview\"]")
    public WebElement lblOverviewTab;

    @FindBy(how = How.XPATH, using = "//ul[@id=\"sb3_cc_holiday_search_sb3_errorlist\"]/li[2]")
    public WebElement lblNoDestinationErrorMsg;

    public void selectCalendarDay(WebDriver driver, int day, Month calendar) throws InterruptedException {
        String strUsing = "";

        if (calendar == Month.OUTBOUND)
            strUsing = "/descendant::a[text()='" + day + "'][1]";
        else if (calendar == Month.FOLLOWING) {
            strUsing = "/descendant::a[text()='" + day + "'][2]";
        }
        Thread. sleep(1000);

        driver.findElement(By.xpath(strUsing)).click();
    }

}
