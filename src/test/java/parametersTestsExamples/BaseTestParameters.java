package parametersTestsExamples;

import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utility.*;

import java.util.concurrent.TimeUnit;

public class BaseTestParameters {                        // Class B in POM
    public WebDriver driver;
    @BeforeTest
    public void suiteSetup()throws Exception{
        /*System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
                "\\src\\test\\resources\\geckodriver.exe");
        driver = new FirefoxDriver();*/

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +
                "\\src\\test\\resources\\chromedriver_76_0_3809_68.exe");
        ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"LoginShocase");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @AfterTest
    public void terminateBrowser() throws RuntimeException{
        try {
            driver.quit();
        }
        catch (RuntimeException e1) {
            System.err.println("Caught0 RuntimeException: " + e1.getMessage());
        }
    }
    public void testPattern(String Url, WebElement loginShocase, String email, String password,
                            WebElement inputLogin, WebElement inputPassword, WebElement submitLog,
                            WebElement profileElement, WebElement signOutElement) throws InterruptedException{
        openLoginPage(loginShocase, Url);
        typeCredentials (email, password, inputLogin, inputPassword);
        submitLogin(submitLog);
        assertResults(profileElement);
        logOut(profileElement, signOutElement, loginShocase);
    }
    public void testPatternNegative(String Url, WebElement loginShocase, String email, String password,
                                    WebElement inputLogin, WebElement inputPassword,
                                    WebElement submitLog) throws InterruptedException{
        openLoginPage(loginShocase, Url);
        typeCredentials(email, password, inputLogin, inputPassword);
        submitLogin(submitLog);
        assertResultsNegative(submitLog);
    }
    public void explicitWait(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void openLoginPage(WebElement loginShocase, String Url) {
        driver.get(Url);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        loginShocase.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    public void typeCredentials(String email, String password, WebElement inputLogin, WebElement inputPassword)
            throws InterruptedException{
        System.out.println(email + " " + password);
        inputLogin.clear();
        inputLogin.sendKeys(email);
        inputPassword.clear();
        inputPassword.sendKeys(password);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(3000);
    }
    public void submitLogin(WebElement submitLog) {
        for(int l=0; l<10; l++)
            try {
                submitLog.sendKeys(Keys.ENTER);
                System.out.println("submitLog was successful!" );
                break;
            } catch(StaleElementReferenceException e) {
                e.toString();
                System.out.println("Trying to recover from a stale element :" + e.getMessage());
            }
    }
    public void assertResults(WebElement profileElement) {
        for(int i=0; i<10; i++)
            try {
                System.out.println("Log In was successful - " + profileElement.getText());
                break;
            } catch(StaleElementReferenceException e) {
                e.toString();
                System.out.println("Trying to recover from a stale element :" + e.getMessage());
            }
    }
    public void assertResultsNegative(WebElement submitLog) {
        for(int m=0; m<10; m++)
            try {
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                Assert.assertTrue(submitLog.isDisplayed());
                System.out.println(submitLog.getText());
                break;
            } catch(StaleElementReferenceException e) {
                e.toString();
                System.out.println("Trying to recover from a stale element :" + e.getMessage());
            }
    }
    public void logOut(WebElement profileElement, WebElement signOutElement, WebElement loginShocase) throws InterruptedException{
        Actions builder = new Actions(driver);
        Action mouseOverHome  = builder
                .moveToElement(profileElement)
                .moveToElement(signOutElement)
                .build();
        for(int r=0; r<10; r++) {
            try {
                explicitWait(driver, profileElement);
                mouseOverHome.perform();
                explicitWait(driver, signOutElement);
                signOutElement.click();
                break;
            } catch(StaleElementReferenceException e) {
                e.toString();
                System.out.println("Trying to recover from a stale element :" + e.getMessage());
            }
        }
        explicitWait(driver, loginShocase);
        System.out.println("Get Out was successful!");
        Thread.sleep(3000);
    }
}
