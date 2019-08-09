package appiumSampleTests;

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

public class BaseScreen {
    private AppiumDriver driver;

    @BeforeTest
    public void setup () throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("platformVersion", "6.0.1");
        capabilities.setCapability("deviceName","bac8a146");
        capabilities.setCapability("appPackage", "com.android.calculator2");
        capabilities.setCapability("appActivity","com.android.calculator2.Calculator");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @AfterTest
    public void teardown(){
        driver.quit();
    }
    public void testBaseScreenCalc(AndroidElement first, AndroidElement  plus, AndroidElement  second, AndroidElement  equalTo, AndroidElement  results){
        tapByElement(first);
        tapByElement(plus);
        tapByElement(second);
        tapByElement(equalTo);
        assert results.getText().equals("6"):"Actual value is : "
                + results.getText() + " did not match with expected value: 6";
    }
    public void tapByElement (AndroidElement androidElement) {
        new TouchAction(driver)
                .tap(tapOptions().withElement(element(androidElement)))
                .waitAction(waitOptions(Duration.ofMillis(250))).perform();
    }
}
