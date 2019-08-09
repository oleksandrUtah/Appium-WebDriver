package day3;


import day2.Tools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class BasicAuth {



    WebDriver driver;



    @BeforeSuite

    public void suiteSetup(){

        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\geckodriver.exe");

        driver = new FirefoxDriver();

        //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        Tools.waitForPageLoaded(driver);

    }

    @Test

    public void testBasicAuth() {

        String login = "admin";

        String password = "admin";



        String URL = "http://the-internet.herokuapp.com/basic_auth";



        String basicAuthURL = "http://" + login + ":" + password + "@the-internet.herokuapp.com/basic_auth";

        driver.get(basicAuthURL);

        Boolean actualResult = driver.getPageSource().contains("Congratulations!");

        Assert.assertTrue(actualResult);

    }

}