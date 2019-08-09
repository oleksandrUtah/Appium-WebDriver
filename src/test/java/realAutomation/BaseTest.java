package realAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;

    @BeforeSuite
    public void suiteSetup(){
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
                "\\src\\test\\resources\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @AfterTest
    public void closeBrowser() {
        try {
            driver.close();
        }
        catch (RuntimeException e1) {
            System.err.println("Caught0 RuntimeException: " + e1.getMessage());
        }
    }
    @AfterSuite
    public void terminateBrowser() {
        try {
            driver.quit();
        }
        catch (RuntimeException e1) {
            System.err.println("Caught0 RuntimeException: " + e1.getMessage());
        }
    }
    public void testPattern(String baseUrl, String username, String password, By inputLogin,
                            By inputPassword, By submitLog, By resultsElement, By logoutElement){
        openLoginPage(baseUrl);
        typeCredentials(username, password, inputLogin, inputPassword);
        submitLogin(submitLog);
        assertResults(resultsElement);
        logOut(resultsElement, logoutElement);
    }
    public void testPatternNegative(String baseUrl, String username, String password,
                             By inputLogin, By inputPassword, By submitLog, By spanElement){
        openLoginPage(baseUrl);
        typeCredentials(username, password, inputLogin, inputPassword);
        submitLogin(submitLog);
        assertResultsNegative(spanElement);
    }
    public void explicitWait(WebDriver driver, By element){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    public void openLoginPage(String baseUrl) {
        driver.get(baseUrl);
        Tools.waitForPageLoaded(driver);
    }
    public void typeCredentials(String username, String password, By inputLogin, By inputPassword) {
        WebElement element1 = driver.findElement(inputLogin);
        element1.sendKeys(username);
        WebElement element2 = driver.findElement(inputPassword);
        element2.sendKeys(password);
    }
    public void submitLogin(By submitLog) {
        WebElement element = driver.findElement(submitLog);
        element.submit();
    }
    public void assertResults(By resultsElement) {
        WebElement resultElement = driver.findElement(resultsElement);
        explicitWait(driver, resultsElement);
        System.out.println("Log In was successful - " + resultElement.getText());
        Assert.assertTrue(resultElement.isDisplayed());
    }
    public void assertResultsNegative(By spanElement) {
        WebElement spanMessage = driver.findElement(spanElement);
        explicitWait(driver, spanElement);
        Assert.assertTrue(spanMessage.isDisplayed());
        System.out.println(spanMessage.getText());
    }
    public void logOut(By resultsElement, By logoutElement) {
        WebElement resultElement = driver.findElement(resultsElement);
        explicitWait(driver, resultsElement);
        resultElement.click();
        WebElement logoutElem = driver.findElement(logoutElement);
        logoutElem.click();
    }
}