package day4;


import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class YahooSearchTest extends BaseTest {

    String queryString = "Portnov School";

    String url = "https://www.yahoo.com/";

    By searchBox = By.id("uh-search-box");

    //String cssValue = "#results .compPagination span";
    String xpathValue = "//*[@class='compPagination']/span";


    @Test

    public void testSearch() {

        openPage(url);

        sendKeysTo(searchBox, queryString);

        submitSearch(searchBox);

        //assertResults(cssValue);
        assertResults(xpathValue);


    }

}