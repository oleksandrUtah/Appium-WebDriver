package appiumSampleTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class FirstAppiumClass {
    private AppiumDriver driver;
    private String id2;
    private String id4;
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
    }
    @AfterTest
    public void teardown(){
        driver.quit();
    }

    @Parameters({"id1", "id2"})
    @Test
    public void testCalculator(String id1, String id2)  /*throws MalformedURLException, InterruptedException*/ {
        //locate the Text on the calculator by using By.name()
        WebElement two=driver.findElementById(id1);
        two.click();
        WebElement plus=driver.findElementByAccessibilityId("plus");
        plus.click();
        WebElement four=driver.findElementById(id2);
        four.click();
        WebElement equalTo=driver.findElementByAccessibilityId("equals");
        equalTo.click();
        //locate the edit box of the calculator by using By.tagName()
        WebElement results=driver.findElementById("com.android.calculator2:id/result");
        //Check the calculated value on the edit box
        assert results.getText().equals("6"):"Actual value is : "
                + results.getText() + " did not match with expected value: 6";
    }
}
