import com.sun.org.glassfish.gmbal.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RMSysTest extends TestBase {
    public static final String BASE_URL = "https://192.168.100.26/";
    private static final By USERNAME_FIELD = By.id("Username");
    private static final By PASSWORD_FIELD = By.id("Password");
    private static final By LOGIN_BUTTON = By.id("SubmitButton");
    private static final By SIGN_OUT_BUTTON = By.xpath("//a[@title=\"Sign out\"]");
    private static final By HOME_MENU_TAB = By.id("homeMenu");
    private static final By OFFICE_TAB_LINK = By.id("officeMenu");
    private static final By SEARCH_BY_OFFICE = By.id("input-search");
    private String username = "EugenBorisik";
    private String password = "qwerty12345";


    public RMSysTest() {
        super(BASE_URL);
    }

    @Description("Go to RMSsys login page, enter credentials and login")
    @Test
    public void login() {
        driver.findElement(USERNAME_FIELD).sendKeys(username);
        try {
            Thread.sleep(3000); //2.	Add Thread.sleep for login test. This is the Explicit Wait type
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();

        // 3.	Add explicit waiter for login test, which will wait until Sign out link appears (after login).
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(SIGN_OUT_BUTTON));
    }

    @Description("Login RMSys, got to Office tab wait for Search by office input to appear - wait 15 seconds, polling frequence - 2,7 seconds")
    @Test
    public void waitForSearchByOffice() {
        login();
        Assert.assertTrue(driver.findElement(HOME_MENU_TAB).isDisplayed());
        driver.findElement(OFFICE_TAB_LINK).click();
        new WebDriverWait(driver, 15, 2700).
                until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BY_OFFICE)); //4. wait 15 seconds, polling frequency - 2,7 seconds
    }
}

