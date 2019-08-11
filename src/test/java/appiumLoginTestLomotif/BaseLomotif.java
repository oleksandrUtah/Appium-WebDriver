package appiumLoginTestLomotif;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;

public class BaseLomotif {
    public AppiumDriver driver;
    @BeforeTest
    public void setup () throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("platformVersion", "6.0.1");
        capabilities.setCapability("deviceName","bac8a146");
        capabilities.setCapability("appPackage", "com.lomotif.android");
        capabilities.setCapability("appActivity","com.lomotif.android.view.LMLauncherActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @AfterTest
    public void teardown(){
        driver.quit();
    }

    public void testPattern(String mail, String password, AndroidElement notif_count, AndroidElement allow_button,
                            AndroidElement button1, AndroidElement action_signup, AndroidElement action_login,
                            AndroidElement field_mail, AndroidElement field_password, AndroidElement show_password,
                            AndroidElement login, AndroidElement screen_title, int x1, int y1, int x2, int y2,
                            AndroidElement logOut, AndroidElement button1LogOut, AndroidElement buttonOK, AndroidElement homePage) throws InterruptedException{
        System.out.println("Mail: " + mail + " + Password: " + password);
        tapByElement(notif_count);
        tapByElement(allow_button);
        driver.navigate().back();
        tapByElement(button1);
        tapByElement(action_signup);
        tapByElement(action_login);
        sendKeysInput(field_mail, mail);
        sendKeysInput(field_password, password);
        tapByElement(show_password);
        tapByElement(login);
        new TouchAction(driver).waitAction(waitOptions(Duration.ofMillis(10000)));
        tapByElement(screen_title);
        assert screen_title.getText().equals("Notifications"):"Actual text is : "
                + screen_title.getText() + " did not match with expected text: Notifications";
        tapByCoordinates (x1, y1);
        tapByCoordinates (x2, y2);
        tapByElement(logOut);
        tapByElement(button1LogOut);
        tapByElement(buttonOK);
        assert homePage.getText().equals("Featured"):"Actual text is : "
                + homePage.getText() + " did not match with expected text: Featured";
    }
    public void testPattern2 (String password, AndroidElement notif_count, AndroidElement allow_button,
                              AndroidElement button1, AndroidElement action_signup, AndroidElement action_login,
                              AndroidElement field_password, AndroidElement show_password) {
        System.out.println("Password must be masked and has show icon");
        tapByElement(notif_count);
        tapByElement(action_signup);
        tapByElement(action_login);
        sendKeysInput(field_password, password);
        tapByElement(field_password);
        System.out.println(field_password.getText());
        assert field_password.getText().isEmpty():"Actual password input text is : "
                + field_password.getText() + " did not masked";
        tapByElement(show_password);
        assert field_password.getText().equals(password):"Show password icon did not working";
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
    }
    public void testPattern3(String mail, String password, AndroidElement notif_count, AndroidElement action_signup,
                             AndroidElement action_login, AndroidElement field_mail, AndroidElement field_password,
                             AndroidElement show_password, AndroidElement login, AndroidElement screen_title, int x1,
                             int y1, int x2, int y2, AndroidElement logOut, AndroidElement button1LogOut,
                             AndroidElement buttonOK, AndroidElement homePage) throws InterruptedException{
        System.out.println("Mail: " + mail + " + Password: " + password);
        tapByElement(notif_count);
        tapByElement(action_signup);
        tapByElement(action_login);
        sendKeysInput(field_mail, mail);
        sendKeysInput(field_password, password);
        tapByElement(show_password);
        tapByElement(login);
        new TouchAction(driver).waitAction(waitOptions(Duration.ofMillis(10000)));
        tapByElement(screen_title);
        assert screen_title.getText().equals("Notifications"):"Actual text is : "
                + screen_title.getText() + " did not match with expected text: Notifications";
        tapByCoordinates (x1, y1);
        tapByCoordinates (x2, y2);
        tapByElement(logOut);
        tapByElement(button1LogOut);
        tapByElement(buttonOK);
        assert homePage.getText().equals("Featured"):"Actual text is : "
                + homePage.getText() + " did not match with expected text: Featured";
    }
    public void tapByElement (AndroidElement androidElement) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(androidElement));
        new TouchAction(driver)
                .tap(tapOptions().withElement(element(androidElement)))
                .waitAction(waitOptions(Duration.ofMillis(250))).perform();
    }
    public void tapByCoordinates (int x,  int y) {
        new TouchAction(driver)
                .tap(point(x,y))
                .waitAction(waitOptions(Duration.ofMillis(1000))).perform();
    }
    public void sendKeysInput (AndroidElement androidElement, String string){
        tapByElement(androidElement);
        androidElement.clear();
        androidElement.sendKeys(string);

    }
}
