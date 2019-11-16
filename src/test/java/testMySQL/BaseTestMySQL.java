package testMySQL;

import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
public class BaseTestMySQL {                        // Class B in POM
    public WebDriver driver;
        // 1. Retrieve data from 1 table:
    /*String query = "select email_value,  password_value from login_mysql.test where priority=";*/
        // 2. Retrieve data from 3 tables through INNER JOIN:
    /*String query = "SELECT A.`email_value`,  C.`password_value` " +
                        "FROM `login_mysql`.`email` AS A " +
                            "INNER JOIN `login_mysql`.`test` AS B " +
                            "ON A.`email_id` = B.`email_id` " +
                            "INNER JOIN `login_mysql`.`password` AS C " +
                            "ON C.`password_id` = B.`password_id` " +
                        "WHERE B.`priority`=";*/
       // 3. We can use "USING" clause instead of "ON" clause
       // - only for identical names for matched columns in both tables (`email_id` and `password_id`)!
    /*String query = "SELECT A.`email_value`,  C.`password_value` " +
            "FROM `login_mysql`.`email` AS A " +
            "INNER JOIN `login_mysql`.`test` AS B " +
            "USING (`email_id`) " +
            "INNER JOIN `login_mysql`.`password` AS C " +
            "USING (`password_id`) " +
            "WHERE B.`priority`=";*/
        // 4. Now we create VIEW test_credentials:
                        /*
                    CREATE VIEW `test_credentials` AS
                    SELECT B.`priority`, A.`email_value`,  C.`password_value`
                    FROM `login_mysql`.`email` AS A
                    INNER JOIN `login_mysql`.`test` AS B
                    USING (`email_id`)
                    INNER JOIN `login_mysql`.`password` AS C
                    USING (`password_id`)
                        */
        // We can use created VIEW as a table in the regular SELECT statement:
    String query = "SELECT email_value,  password_value " +
            "FROM login_mysql.test_credentials " +
            "WHERE priority=";
    String email_value;
    String password_value;

    String dbMyUrl = "jdbc:mysql://localhost:3306/login_mysql";
    String dbUsername = "root";
    String dbPassword = "3957Kors@MySQL";
    // Make the database connection
    String dbClass = "com.mysql.cj.jdbc.Driver";


    @BeforeTest
    public void suiteSetup()throws Exception{
        /*System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
                "\\src\\test\\resources\\geckodriver.exe");
        driver = new FirefoxDriver();*/

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +
                "\\src\\test\\resources\\chromedriver.exe");

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @AfterTest
    public void terminateBrowser() throws RuntimeException{
        try {
            driver.quit();
        }
        catch (RuntimeException e1) {
            System.err.println("Caught0 RuntimeException: " + e1.getMessage());
        }
    }
    public void getMySQLdata(int pr)throws Exception{
        Class.forName(dbClass);
        // Get connection to DB
        Connection con = DriverManager.getConnection(dbMyUrl,dbUsername,dbPassword);
        // Statement object to send the SQL statement to the Database
        Statement stmt = con.createStatement();
        // Execute the SQL Query. Store results in ResultSet
        ResultSet rs = stmt.executeQuery(query + pr + ";");
        while (rs.next()){
            email_value = rs.getString(1);
            password_value = rs.getString(2);
            System. out.println("   Starts test priority = " + pr);
            System. out.println("From database: " + email_value);
            System. out.println("From database: " + password_value);
        }
        if (con != null)
            con.close();

    }
    public void testPattern(String Url, WebElement loginShocase, String email, String password,
                            WebElement inputLogin, WebElement inputPassword, WebElement submitLog,
                            WebElement profileElement, WebElement signOutElement) throws InterruptedException{
        openLoginPage(loginShocase, Url);
        typeCredentials (email, password, inputLogin, inputPassword);
        submitLogin(submitLog);
        assertResults(profileElement);
        logOut(profileElement, signOutElement, loginShocase);
    }
    public void testPatternNegative(String Url, WebElement loginShocase, String email, String password,
                                    WebElement inputLogin, WebElement inputPassword,
                                    WebElement submitLog) throws InterruptedException{
        openLoginPage(loginShocase, Url);
        typeCredentials(email, password, inputLogin, inputPassword);
        submitLogin(submitLog);
        assertResultsNegative(submitLog);
    }
    public void explicitWait(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void openLoginPage(WebElement loginShocase, String Url) {
        driver.get(Url);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        loginShocase.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    public void typeCredentials(String email, String password, WebElement inputLogin, WebElement inputPassword)
            throws InterruptedException{
        System.out.println("Start typeCredentials: " + email + " + " + password);
        inputLogin.clear();
        inputLogin.sendKeys(email);
        inputPassword.clear();
        inputPassword.sendKeys(password);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(3000);
    }
    public void submitLogin(WebElement submitLog) {
        for(int l=0; l<30; l++)
            try {
                submitLog.sendKeys(Keys.ENTER);
                System.out.println("submitLog was successful!" );
                break;
            } catch(StaleElementReferenceException e) {
                e.toString();
                System.out.println("Trying to recover from a stale element :" + e.getMessage());
            }
    }
    public void assertResults(WebElement profileElement) {
        for(int i=0; i<10; i++)
            try {
                System.out.println("Log In was successful - " + profileElement.getText());
                break;
            } catch(StaleElementReferenceException e) {
                e.toString();
                System.out.println("Trying to recover from a stale element :" + e.getMessage());
            }
    }
    public void assertResultsNegative(WebElement submitLog) throws InterruptedException{
        for(int m=0; m<10; m++)
            try {
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                Assert.assertTrue(submitLog.isDisplayed());
                System.out.println(submitLog.getText());
                Thread.sleep(3000);
                break;
            } catch(StaleElementReferenceException e) {
                e.toString();
                System.out.println("Trying to recover from a stale element :" + e.getMessage());
                Thread.sleep(3000);
            }
    }
    public void logOut(WebElement profileElement, WebElement signOutElement, WebElement loginShocase) throws InterruptedException{
        Actions builder = new Actions(driver);
        Action mouseOverHome  = builder
                .moveToElement(profileElement)
                .moveToElement(signOutElement)
                .build();
        for(int r=0; r<30; r++) {
            try {
                explicitWait(driver, profileElement);
                mouseOverHome.perform();
                explicitWait(driver, signOutElement);
                signOutElement.click();
                break;
            } catch(StaleElementReferenceException e) {
                e.toString();
                System.out.println("Trying to recover from a stale element :" + e.getMessage());
            }
        }
        explicitWait(driver, loginShocase);
        System.out.println("Get Out was successful!");
        Thread.sleep(3000);
    }
}
