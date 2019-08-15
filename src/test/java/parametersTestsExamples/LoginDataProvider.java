package parametersTestsExamples;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
public class LoginDataProvider extends BaseTestParameters{// Example of @DataProvider method (testng3.xml file)
    private String Url = "https://www.shocase.com/go/signup";
    private String email = "koz84075+007@gmail.com";
    private String password = "1234567a";
    private String email3 = "Koz84075+007@gmail.com";
    private Actions a;
    @DataProvider(name = "test4_8")
    public Object[][] LoginCredentials(){
        //Created two dimensional array with 8 rows and 2 columns.
        Object[][] Cred = new Object[5][2];

        Cred[0][0] = "koz84075+007@gmail.com";
        Cred[0][1] = "1234567A";

        Cred[1][0] = "koz84075+007@gmail.com";
        Cred[1][1] = "123a";

        Cred[2][0] = "xxxxx";
        Cred[2][1] = "1234567a";

        Cred[3][0] = "";
        Cred[3][1] = "1234567a";

        Cred[4][0] = "koz84075+007@gmail.com";
        Cred[4][1] = "";

        return Cred; //Returned Cred
    }
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
    @Test
    //Happy Path: Verify "Email" = "koz84075+007@gmail.com" + "Password" = "1234567a".
    public void loginTest1() throws InterruptedException{
        testPattern(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog, profileElement, signOutElement);
    }
    @Test
    //Positive: Verify masking Password type (type="password").
    public void loginTest2() throws InterruptedException{
        openLoginPage(loginShocase, Url);
        System.out.println("Password type: " + inputPassword.getAttribute("type"));
        Assert.assertEquals(inputPassword.getAttribute("type"), "password");
        System.out.println(email + " " + password);
        driver.navigate().to(Url);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(5000);
    }
    @Test
    public void loginTest3() throws InterruptedException{
        email = email3;
        testPattern(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog, profileElement, signOutElement);
    }
    @Test(dataProvider="test4_8")
    public void loginTest4_8(String email, String password) throws InterruptedException{
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
}

