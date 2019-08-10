package appiumLoginTestLomotif;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginHappyPass extends BaseLomotif {

    private String mail;
    private String password;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    @AndroidFindBy(id = "com.lomotif.android:id/badge_notif_count")
    private AndroidElement notif_count;
    @AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
    private AndroidElement allow_button;
    @AndroidFindBy(id = "android:id/button1")
    private AndroidElement button1;
    @AndroidFindBy(id = "com.lomotif.android:id/action_signup")
    private AndroidElement action_signup;
    @AndroidFindBy(id = "com.lomotif.android:id/action_login")
    private AndroidElement action_login;
    // Sign Up: koz84075@gmail.com  alexuta 3954korsa
    @AndroidFindBy(id = "com.lomotif.android:id/field_mail")
    private AndroidElement field_mail;
    @AndroidFindBy(id = "com.lomotif.android:id/field_password")
    private AndroidElement field_password;
    @AndroidFindBy(id = "com.lomotif.android:id/icon_show_password")
    private AndroidElement show_password;
    @AndroidFindBy(xpath = "//android.widget.Button[@text = 'Log in']")
    private AndroidElement login;
    @AndroidFindBy(id = "com.lomotif.android:id/label_screen_title")
    private AndroidElement screen_title;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Log Out']")
    private AndroidElement logOut;
    @AndroidFindBy(id = "android:id/button1")
    private AndroidElement button1LogOut;
    @AndroidFindBy(xpath = "//android.widget.Button[@text = 'OK']")
    private AndroidElement buttonOK;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Featured']")
    private AndroidElement homePage;

    @Parameters({ "mail", "password", "x1", "y1", "x2", "y2"})
    @Test (priority = 1)

    public void loginHP(String mail, String password, int x1, int y1, int x2, int y2) throws InterruptedException{

        testPattern(mail, password, notif_count, allow_button, button1, action_signup, action_login,
                field_mail, field_password, show_password, login, screen_title, x1, y1, x2, y2, logOut, button1LogOut,
                buttonOK, homePage);
    }
    @Parameters({ "mail", "password", "x1", "y1", "x2", "y2"})
    @Test (priority = 2)

    public void loginHP2(String mail, String password, int x1, int y1, int x2, int y2) throws InterruptedException{

        testPattern(mail, password, notif_count, allow_button, button1, action_signup, action_login,
                field_mail, field_password, show_password, login, screen_title, x1, y1, x2, y2, logOut, button1LogOut,
                buttonOK, homePage);
    }



    @Parameters({ "password"})
    @Test (priority = 3)
    public void inputTypePassword(String password) throws InterruptedException{
        testPattern2(password, notif_count, allow_button, button1, action_signup, action_login, field_password,
                show_password);
    }

}
