package pageFactoryExamples;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpPage extends BaseTestPF {

    private String baseUrl = "http://hrm.seleniumminutes.com";
    private String username = "admin";
    private String password = "Password";

    //All WebElements are identified by @FindBy annotation:
    @FindBy(id="txtUsername")
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

    @Test(priority = 1)
    //Happy Path: Verify "Username" = "admin" + "Password" = "Password".
    public void loginSuite1() {
        testPattern(baseUrl, username, password, inputLogin, inputPassword, submitLog,
                resultsElement, logoutElement);
    }
    @Test(priority = 2)
    //Positive: Verify masking Password type (type="password").
    public void loginSuite2() {
        openLoginPage(baseUrl);
        System.out.println("Password type: " + inputPassword.getAttribute("type"));
        Assert.assertEquals(inputPassword.getAttribute("type"), "password");
    }
    @Test(priority = 3)
    //Positive: Make sure, that "Username" not Case Sensitive.
    public void loginSuite3() {
        username = "Admin";
        testPattern(baseUrl, username, password, inputLogin, inputPassword, submitLog,
                resultsElement, logoutElement);
    }
    @Test(priority = 4)
    //Positive: Make sure, that "Password" not Case Sensitive.
    public void loginSuite4() {
        username = "admin";
        password = "password";
        testPattern(baseUrl, username, password, inputLogin, inputPassword, submitLog,
                resultsElement, logoutElement);
    }
    @Test(priority = 5)
    //Negative: Verify registered "Username" + uncreated "Password" cannot be used.
    public void loginSuite5() {
        username = "admin";
        password = "pass";
        testPatternNegative(baseUrl, username, password, inputLogin, inputPassword,
                submitLog, spanElement);
    }
    @Test(priority = 6)
    //Negative: Verify unregistered "Username" + created "Password" cannot be used.
    public void loginSuite6() {
        username = "xxxxx";
        password = "Password";
        testPatternNegative(baseUrl, username, password, inputLogin, inputPassword,
                submitLog, spanElement);
    }
    @Test(priority = 7)
    //Negative: Make sure, that "Username" field is required.
    public void loginSuite7() {
        username = "";
        password = "Password";
        testPatternNegative(baseUrl, username, password, inputLogin, inputPassword,
                submitLog, spanElement);
    }
    @Test(priority = 8)
    //Negative: Make sure, that "Password" field is required.
    public void loginSuite8() {
        username = "admin";
        password = "";
        testPatternNegative(baseUrl, username, password, inputLogin, inputPassword,
                submitLog, spanElement);
    }
}