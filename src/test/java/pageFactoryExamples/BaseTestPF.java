package pageFactoryExamples;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import realAutomation.Tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/*
The PageFactory class
Page Factory class is an inbuilt Page Object Model concept for Selenium WebDriver.
package org.openqa.selenium.support.PageFactory;
PageFactory reduce duplicate test code for finding WebElement.
We use annotations @FindBy  in class A.
We use .initElements() method in class B.
Step 1. PageFactory class we use in class A to find WebElement.
@FindBy can accept all locator strategies from id to xpath. Example:
Before: 		private By inputLogin = By.id("txtUsername");
After:  		@FindBy(id="txtUsername")
            private WebElement inputLogin;
Step 2. Also we use PageFactory class in class B as initElements() method to initialize web elements.
This method will used after driver creating inside of @BeforeSuite annotation. Example:
	PageFactory.initElements(driver, this);
Advantages of PageFactory class: we can create tests with less keystroke. Compare:
Before:
WebElement element1 = driver.findElement(inputLogin);
element1.sendKeys(username);
After:
	inputLogin.sendKeys(username);

Examples without PageFactory class:
1.	Class A.  https://github.com/oleksandrUtah/Selenium-Java-Webdriver/blob/master/src/test/java/RealAutomation/Login.java
2.	Class B. https://github.com/oleksandrUtah/Selenium-Java-Webdriver/blob/master/src/test/java/RealAutomation/BaseTest.java

Examples with PageFactory class:
1.	Class A.  https://github.com/oleksandrUtah/Selenium-Java-Webdriver/blob/master/src/test/java/PageFactoryExamples/SignUpPage.java
2.  Class B.  https://github.com/oleksandrUtah/Selenium-Java-Webdriver/blob/master/src/test/java/PageFactoryExamples/BaseTestPF.java
Examples with PageFactory class and
*/
public class BaseTestPF {
    protected WebDriver driver;

    @BeforeSuite
    public void suiteSetup(){
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
                "\\src\\test\\resources\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //This initElements method will initialize all WebElements:
        PageFactory.initElements(driver, this);
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

    public void testPattern(String baseUrl, String username, String password, WebElement inputLogin, WebElement inputPassword,
                            WebElement submitLog, WebElement resultsElement, WebElement logoutElement){
        openLoginPage(baseUrl);
        typeCredentials(username, password, inputLogin, inputPassword);
        submitLogin(submitLog);
        assertResults(resultsElement);
        logOut(resultsElement, logoutElement);
    }
    public void testPatternNegative(String baseUrl, String username, String password, WebElement inputLogin, WebElement inputPassword,
                                    WebElement submitLog, WebElement spanElement){
        openLoginPage(baseUrl);
        typeCredentials(username, password, inputLogin, inputPassword);
        submitLogin(submitLog);
        assertResultsNegative(spanElement);
    }

    public void explicitWait(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void openLoginPage(String baseUrl) {
        driver.get(baseUrl);
        Tools.waitForPageLoaded(driver);
    }
    public void typeCredentials(String username, String password, WebElement inputLogin, WebElement inputPassword) {
        inputLogin.sendKeys(username);
        inputPassword.sendKeys(password);
    }
    public void submitLogin(WebElement submitLog) {
        submitLog.submit();
    }

    public void assertResults(WebElement resultsElement) {
        explicitWait(driver, resultsElement);
        System.out.println("Log In was successful - " + resultsElement.getText());
        Assert.assertTrue(resultsElement.isDisplayed());
    }
    public void assertResultsNegative(WebElement spanElement) {
        explicitWait(driver, spanElement);
        Assert.assertTrue(spanElement.isDisplayed());
        System.out.println(spanElement.getText());
    }
    public void logOut(WebElement resultsElement, WebElement logoutElement) {
        resultsElement.click();
        explicitWait(driver, logoutElement);
        logoutElement.click();
    }
    public String ExcelRead (int indexSheet, int indexRow, int indexCell) throws IOException {
        FileInputStream fis = new FileInputStream("D:\\2019\\MyInterview_questions_31\\ExcelSheet.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(indexSheet);
        Row row = sheet.getRow(indexRow);
        Cell cell = row.getCell(indexCell);
        String cellval = cell.getStringCellValue();
        return cellval;
    }
}