package appiumLoginTestLomotif;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class BaseLomotif {
    private AppiumDriver driver;
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
                            AndroidElement field_mail, AndroidElement field_password, AndroidElement show_password, AndroidElement login,
                            AndroidElement screen_title) throws InterruptedException{
        //System.out.println("Mail: " + mail + " + Password: " + password);
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




    }
    public void tapByElement (AndroidElement androidElement) {
        /*WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(androidElement));*/
        new TouchAction(driver)
                .tap(tapOptions().withElement(element(androidElement)))
                .waitAction(waitOptions(Duration.ofMillis(250))).perform();
    }
    public void sendKeysInput (AndroidElement androidElement, String string){
        tapByElement(androidElement);
        androidElement.clear();
        androidElement.sendKeys(string);

    }
}
