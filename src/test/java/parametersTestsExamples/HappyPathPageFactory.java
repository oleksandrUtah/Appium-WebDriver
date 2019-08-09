package parametersTestsExamples;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class HappyPathPageFactory extends BaseTestParameters {
       // Example of PageFactory class:
       // We use annotations @FindBy  to find WebElements in class A.
       // We use .initElements() method to initialize all WebElements in class B.
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
    private WebElement resultsElement;
    @FindBy(linkText = "Sign out")
    private WebElement outElement;


    @Test(priority = 1)
    //Happy Path: Verify "Username" = "admin" + "Password" = "Password".
    public void loginSuite1() throws InterruptedException{
        email = "koz84075+007@gmail.com";
        password = "1234567a";
        System.out.println(email + " " + password);
        testPattern(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog, resultsElement, outElement);

    }
}
