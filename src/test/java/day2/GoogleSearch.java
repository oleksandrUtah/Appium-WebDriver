package day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GoogleSearch {

    WebDriver driver;

    String mainPage;

    By searchInput;

    String queryString;

    By resultStats;

    String expectedText;



    @BeforeSuite

    public void suiteSetup(){

        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\geckodriver.exe");

        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

    @AfterSuite

    public void terminateBrowser() {

        driver.quit();

    }

    @Test

    public void testHomeWork() {

        //TODO: automate search at Yahoo.com2

        mainPage = "https://www.yahoo.com/";

        searchInput = By.id("uh-search-box");

        queryString = "Portnov school";

        resultStats = By.linkText("portnov school mountain view");

        expectedText = "portnov school mountain view";



        openMainPage();

        typeQuery(queryString);

        submitSearch();

        assertResults();

    }

    @Test

    public void testSearch() {

        mainPage = "https://www.google.com";

        searchInput = By.cssSelector(".gLFyf");

        queryString = "Portnov school";

        resultStats = By.id("resultStats");

        expectedText = "About 196,000 results (0.40 seconds)";



        openMainPage();

        typeQuery(queryString);

        submitSearch();

        assertResults();

    }

    @Test

    public void testSearch02() {

        mainPage = "https://www.google.com";

        searchInput = By.cssSelector(".gLFyf");

        queryString = "Portnov Computer School";

        resultStats = By.id("resultStats");

        expectedText = "About 196,000 results (0.40 seconds)";



        openMainPage();

        typeQuery(queryString);

        submitSearch();

        assertResults();

    }

    private void assertResults() {

        WebElement resultStatsElement = driver.findElement(resultStats);

        String textFromResults = resultStatsElement.getText();



        //TODO: change assertions



        String actualText = textFromResults;

        Assert.assertEquals(actualText, expectedText);

    }

    private void submitSearch() {

        WebElement element = driver.findElement(searchInput);

        element.submit();

    }

    private void typeQuery(String queryString) {

        WebElement element = driver.findElement(searchInput);

        element.sendKeys(queryString);
    }

    private void openMainPage() {

        driver.get(mainPage);

    }

    public void sleepThread(){

        try {

            Thread.sleep(3000);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }

    public void explicitWait(WebDriver driver){

        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfElementLocated(resultStats));

    }

}
