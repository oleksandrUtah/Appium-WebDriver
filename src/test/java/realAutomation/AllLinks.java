package realAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AllLinks {
    String baseUrl = "https://stgconsulting.com/job/qa-automation-engineer/";
    WebDriver driver;
    @BeforeTest
    public void launchBrowser() {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
                "\\src\\test\\resources\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }
    @AfterTest
    public void terminateBrowser(){
        driver.quit();
    }
    @Test(priority = 0)
    public void testAllLinks() {
        //create list of all linkelements in the web page:
        List<WebElement> linkElements = driver.findElements(By.xpath("//a"));
        //number of all links:
        int numberLinks = linkElements.size();
        System.out.println("number of Links: " + numberLinks);
        //create empty arrays for all linkTexts & linkUrls:
        String[] linkTexts = new String[numberLinks];
        String[] linkUrls = new String[numberLinks];
        //processing linkElements array elements
        int i = 0;
        for (WebElement link : linkElements) {
            linkTexts[i] = link.getText();
            System.out.println(i + " - link text = " + linkTexts[i]);
            linkUrls[i] = link.getAttribute("href");
            System.out.println("    link Attribute href =   " + linkUrls[i]);
            i++;
        }
        //processing linkUrls array elements
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

