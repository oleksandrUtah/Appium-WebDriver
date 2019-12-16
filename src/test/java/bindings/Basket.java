package bindings;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
public class Basket {
    WebDriver driver;
    String quantity = "20";
    String actualQuantity;

    @Given("^that i am on the shopping website$")
    public void that_i_am_on_the_shopping_website() throws Throwable {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +
                "\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://test.sugaringfactory.com/");
        driver.manage().window().maximize();
    }
    @When("^i add an item to the basket$")
    public void i_add_an_item_to_the_basket() throws Throwable {
        driver.findElement(By.xpath("//div[contains(text(),'Soft')]")).click();
        driver.findElement(By.xpath("//input[@name='quantity']")).clear();
        driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys(quantity);
        driver.findElement(By.xpath("//input[@id='button-cart']")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@class='success']//i[@class='icon-remove-sign']")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    @Then("^i can view the item in my basket$")
    public void i_can_view_the_item_in_my_basket() throws Throwable {
        Actions builder = new Actions(driver);
        Action mouseOverHome  = builder
                .moveToElement(driver.findElement(By.xpath("//div[@id='cart']")))
                .build();
        for(int r=0; r<10; r++) {
            try {
                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                mouseOverHome.perform();
                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                Thread.sleep(3000);
                actualQuantity = driver.findElement(By.xpath("//span[@class='quantity']")).getText();
                System.out.println("Added to cart: " + actualQuantity);
                assertEquals(actualQuantity,"X " + quantity);
                break;
            } catch(StaleElementReferenceException e) {
                e.toString();
                System.out.println("Trying to recover from a stale element :" + e.getMessage());
            }
        }
        Thread.sleep(3000);
        driver.quit();
    }
}