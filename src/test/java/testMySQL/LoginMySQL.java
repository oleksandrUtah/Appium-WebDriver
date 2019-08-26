package testMySQL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginMySQL extends BaseTestMySQL{

    private String Url = "https://www.shocase.com/go/signup";
    private int pr;
    private String email;
    private String password;


    //All WebElements are identified by @FindBy annotation:
    @FindBy(linkText = "SIGN IN")
    private WebElement loginShocase;
    @FindBy(xpath = "//input[@name='email_address']")
    private WebElement inputLogin;
    @FindBy(xpath = "//input[@name='j_password']")
    private WebElement inputPassword;
    @FindBy(xpath = "//button[@name='submit_login_button']")
    private WebElement submitLog;
    @FindBy(xpath = "//a[@class='sis-header-profile']")
    private WebElement profileElement;
    @FindBy(css = "#sis-header-container > div > div.sis-header-inner-wrap > div > ul > li.sis-header-tab.ng-scope > ul > li:nth-child(5) > a")
    private WebElement signOutElement;

    @Test(priority = 1)
    //Happy Path: Verify "Email" = "koz84075+007@gmail.com" + "Password" = "1234567a".
    public void loginTest1() throws Exception{
        pr = 1;
        getMySQLdata(pr);
        email=email_value;
        password=password_value;
        testPattern(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog, profileElement, signOutElement);
    }
    @Test(priority = 2)
    //Positive: Verify masking Password type (type="password").
    public void loginTest2() throws Exception{
        pr = 2;
        getMySQLdata(pr);
        email=email_value;
        password=password_value;
        openLoginPage(loginShocase, Url);
        System.out.println("Password type: " + inputPassword.getAttribute("type"));
        Assert.assertEquals(inputPassword.getAttribute("type"), "password");
        System.out.println(email + " " + password);
        driver.navigate().to(Url);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(5000);
    }
    @Test(priority = 3)
    //Positive: Make sure, that "Email" not Case Sensitive.
    public void loginTest3() throws Exception{
        pr = 3;
        getMySQLdata(pr);
        email=email_value;
        password=password_value;
        testPattern(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog, profileElement, signOutElement);
    }
    @Test(priority = 4)
    //Positive: Make sure, that "Password" not Case Sensitive.
    public void loginTest4() throws Exception{
        pr = 4;
        getMySQLdata(pr);
        email=email_value;
        password=password_value;
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
    @Test(priority = 5)
    //Negative: Verify registered "Username" + uncreated "Password" cannot be used.
    public void loginTest5() throws Exception{
        pr = 5;
        getMySQLdata(pr);
        email=email_value;
        password=password_value;
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
    @Test(priority = 6)
    //Negative: Verify unregistered "Username" + created "Password" cannot be used.
    public void loginTest6() throws Exception{
        pr = 6;
        getMySQLdata(pr);
        email=email_value;
        password=password_value;
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
    @Test(priority = 7)
    //Negative: Make sure, that "Username" field is required.
    public void loginTest7() throws Exception{
        pr = 7;
        getMySQLdata(pr);
        email=email_value;
        password=password_value;
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
    @Test(priority = 8)
    //Negative: Make sure, that "Password" field is required.
    public void loginTest8() throws Exception{
        pr = 8;
        getMySQLdata(pr);
        email=email_value;
        password=password_value;
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
}
