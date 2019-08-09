package day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GetAllLinks {

    String baseUrl = "http://stgconsulting.com/";

    WebDriver driver;

    @BeforeTest

    public void launchBrowser() {

        String driverPath = "C:\\geckodriver.exe";

        System.setProperty("webdriver.gecko.driver", driverPath);

        driver = new FirefoxDriver();

        driver.get(baseUrl);

    }

    @AfterTest

    public void terminateBrowser(){

        driver.quit();

    }

    @Test(priority = 0)

    public void testAllLinks() {

        List<WebElement> linkElements = driver.findElements(By.xpath("//a"));

        int numberLinks = linkElements.size();

        System.out.println("number of Links: " + numberLinks);

        String[] linkTexts = new String[numberLinks];

        String[] linkUrls = new String[numberLinks];

        int i = 0;

        for (WebElement link : linkElements) {

            linkTexts[i] = link.getText();

            System.out.println(i + " - link text = " + linkTexts[i]);

            linkUrls[i] = link.getAttribute("href");

            System.out.println("    link Attribute href =   " + linkUrls[i]);

            i++;

        }

        int m = 0;

        for (String t : linkUrls) {

            try {

                driver.navigate().to(t);

                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                System.out.println(m + " - pageTitle = " + driver.getTitle());

            }
            catch (RuntimeException e1) {

                System.err.println("Caught RuntimeException: " + e1.getMessage());

            }
            m++;
        }
    }
}
