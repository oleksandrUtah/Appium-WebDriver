package pageFactoryExamples;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SignUpPageExcel extends BaseTestPF{

    private String baseUrl = "http://hrm.seleniumminutes.com";
    private int indexSheet;
    private int indexRow;
    private int indexCell;
    private String username;
    private String password;

    //All WebElements are identified by @FindBy annotation:
    @FindBy (id="txtUsername")
    private WebElement inputLogin;
    @FindBy(id="txtPassword")
    private WebElement inputPassword;
    @FindBy(id="btnLogin")
    private WebElement submitLog;
    @FindBy(id="welcome")
    private WebElement resultsElement;
    @FindBy(css = "#welcome-menu > ul:nth-child(1) > li:nth-child(3) > a:nth-child(1)")
    private WebElement logoutElement;
    @FindBy(id="spanMessage")
    private WebElement spanElement;

    @Test (priority = 1)
    //Happy Path: Verify "Username" = "admin" + "Password" = "Password".
    public void loginSuite1() throws IOException {
        username = ExcelRead(1,1, 1);
        password = ExcelRead(1,2, 1);
        testPattern(baseUrl, username, password, inputLogin, inputPassword, submitLog,
                resultsElement, logoutElement);
    }
    @Test(priority = 2)
    //Positive: Verify masking Password type (type="password").
    public void loginSuite2() throws IOException {
        username = ExcelRead(1,1, 2);
        password = ExcelRead(1,2, 2);
        openLoginPage(baseUrl);
        System.out.println("Password type: " + inputPassword.getAttribute("type"));
        Assert.assertEquals(inputPassword.getAttribute("type"), "password");
    }
    @Test(priority = 3)
    //Positive: Make sure, that "Username" not Case Sensitive.
    public void loginSuite3() throws IOException {
        username = ExcelRead(1,1, 3);
        password = ExcelRead(1,2, 3);
        testPattern(baseUrl, username, password, inputLogin, inputPassword, submitLog,
                resultsElement, logoutElement);
    }
    @Test(priority = 4)
    //Positive: Make sure, that "Password" not Case Sensitive.
    public void loginSuite4() throws IOException {
        username = ExcelRead(1,1, 4);
        password = ExcelRead(1,2, 4);
        testPattern(baseUrl, username, password, inputLogin, inputPassword, submitLog,
                resultsElement, logoutElement);
    }
    @Test(priority = 5)
    //Negative: Verify registered "Username" + uncreated "Password" cannot be used.
    public void loginSuite5() throws IOException {
        username = ExcelRead(1,1, 5);
        password = ExcelRead(1,2, 5);
        testPatternNegative(baseUrl, username, password, inputLogin, inputPassword,
                submitLog, spanElement);
    }
    @Test(priority = 6)
    //Negative: Verify unregistered "Username" + created "Password" cannot be used.
    public void loginSuite6() throws IOException {
        username = ExcelRead(1,1, 6);
        password = ExcelRead(1,2, 6);
        testPatternNegative(baseUrl, username, password, inputLogin, inputPassword,
                submitLog, spanElement);
    }
    @Test(priority = 7)
    //Negative: Make sure, that "Username" field is required.
    public void loginSuite7() throws IOException {
        username = ExcelRead(1,1, 7);
        password = ExcelRead(1,2, 7);
        testPatternNegative(baseUrl, username, password, inputLogin, inputPassword,
                submitLog, spanElement);
    }
    @Test(priority = 8)
    //Negative: Make sure, that "Password" field is required.
    public void loginSuite8() throws IOException {
        username = ExcelRead(1,1, 8);
        password = ExcelRead(1,2, 8);
        testPatternNegative(baseUrl, username, password, inputLogin, inputPassword,
                submitLog, spanElement);
    }
}
