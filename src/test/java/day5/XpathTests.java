package day5;
import day4.BaseTest;
import org.testng.annotations.Test;

public class XpathTests extends BaseTest {

    @Test
    public void test001() {
        //We will work on the "Ristorante" application with address:
        String url = "http://52.9.182.211:3001/v1/log-in";

        // CSS Selector from Firefox Inspector:
        String restID_CSS = "div.MuiFormControl-root-96:nth-child(2) > div:nth-child(2) > input:nth-child(1)";

        // Absolute XPath from Firefox Inspector:
        String restID_XPath = "/html/body/div/div/form/div/div[1]/div/input";

        // Relative XPath with code fragment:
        String restID_XPath2 = "//form/div/div[1]/div/input";

        // Single attribute in HTML tag
        String restID_XPath3 = "//input[@name='restaurant_id']";

        // Single attribute in any tag
        String restID_XPath4 = "//*[@name='restaurant_id']";

        // Multiple Attributes
        String restID_XPath5 = "//*[@name='restaurant_id'][@type='text']";

        // XPath operator "and".  Both (all) conditions should be true to find the element:
        String restID_XPath6 = "//*[@name='restaurant_id' and @type='text']";

        // XPath operator "or". One of those conditions should be true:
        String restID_XPath7 = "//*[@name='restaurant_id' or @type='text334234234']";

        // Substring (partial text of the attributes value) is  used in the method contains():
        String restID_XPath8 = "//*[contains(@name,'rest')]";

        // starts-with() method contains of first letters of the attribute value:
        String restID_XPath9 = "//*[starts-with(@name,'rest')]";

        // text() method for visible text:
        String restID_XPath10 = "//*[text()='Log In']";

        // last() from the kind of list
        String restID_XPath11 = "(//input[@type='text'])[last()-1]";

        // position() from the kind of list
        String restID_XPath12 = "(//*[@type='text'])[position()=1]";

        // using index starts from [1]
        String restID_XPath12A = "(//*[@type='text'])[1]";

        // /following::
        String restID_XPath13 = "(//*[@name='restaurant_id']/following::input)";

        // //preceding::
        String restID_XPath14 = "(//*[@name='email']//preceding::input)";

        openPage(url); //I will make a brake point near to the openPage.
    }
}
//Debug 'XpathTests'.
//Click on Evaluate Expression.
//Paste expression: openPage(url).
// Evaluate.