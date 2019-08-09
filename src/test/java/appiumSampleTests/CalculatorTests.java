package appiumSampleTests;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.annotations.Test;

public class CalculatorTests extends BaseScreen {
    private String firstId;
    private String secondId;

    @AndroidFindBy(id = "com.android.calculator2:id/digit_2")
    private AndroidElement first;
    @AndroidFindBy(accessibility = "plus")
    private AndroidElement  plus;
    @AndroidFindBy(id = "com.android.calculator2:id/digit_4")
    private AndroidElement  second;
    @AndroidFindBy(id = "equals")
    private AndroidElement  equalTo;
    @AndroidFindBy(id = "com.android.calculator2:id/result")
    private AndroidElement  results;

    @Test(priority = 1)
    public void testParamCalculator(){
        testBaseScreenCalc(first, plus, second, equalTo, results);
    }
}
