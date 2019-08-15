package parametersTestsExamples;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.ExcelUtils;
import java.util.concurrent.TimeUnit;

public class LoginDataProviderExcel extends BaseTestParameters{
    private String Url = "https://www.shocase.com/go/signup";
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
        String email1 = ExcelUtils.getCellData(1, 0);
        String password1 = ExcelUtils.getCellData(1, 1);
        email=email1;
        password=password1;
        testPattern(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog, profileElement, signOutElement);
    }
    @Test(priority = 2)
    //Positive: Verify masking Password type (type="password").
    public void loginTest2() throws Exception{
        String email2 = ExcelUtils.getCellData(2, 0);
        String password2 = ExcelUtils.getCellData(2, 1);
        email=email2;
        password=password2;
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
        String email3 = ExcelUtils.getCellData(3, 0);
        String password3 = ExcelUtils.getCellData(3, 1);
        email=email3;
        password=password3;
        testPattern(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog, profileElement, signOutElement);
    }
    @Test(priority = 4)
    //Positive: Make sure, that "Password" not Case Sensitive.
    public void loginTest4() throws Exception{
        String email4 = ExcelUtils.getCellData(4, 0);
        String password4 = ExcelUtils.getCellData(4, 1);
        email=email4;
        password=password4;
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
    @Test(priority = 5)
    //Negative: Verify registered "Username" + uncreated "Password" cannot be used.
    public void loginTest5() throws Exception{
        String email5 = ExcelUtils.getCellData(5, 0);
        String password5 = ExcelUtils.getCellData(5, 1);
        email=email5;
        password=password5;
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
    @Test(priority = 6)
    //Negative: Verify unregistered "Username" + created "Password" cannot be used.
    public void loginTest6() throws Exception{
        String email6 = ExcelUtils.getCellData(6, 0);
        String password6 = ExcelUtils.getCellData(6, 1);
        email = email6;
        password = password6;
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
    @Test(priority = 7)
    //Negative: Make sure, that "Username" field is required.
    public void loginTest7() throws Exception{
        String email7 = ExcelUtils.getCellData(7, 0);
        String password7 = ExcelUtils.getCellData(7, 1);
        email = email7;
        password = password7;
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
    @Test(priority = 8)
    //Negative: Make sure, that "Password" field is required.
    public void loginTest8() throws Exception{
        String email8 = ExcelUtils.getCellData(8, 0);
        String password8 = ExcelUtils.getCellData(8, 1);
        email = email8;
        password = password8;
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
}
