import dataprovider.DataProviderXML;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DDTTest extends DataProviderXML {
    public static final String BASE_URL = "https://192.168.100.26/";
    private static final By USERNAME_FIELD = By.id("Username");
    private static final By PASSWORD_FIELD = By.id("Password");
    private static final By LOGIN_BUTTON = By.id("SubmitButton");
    private static final By SIGN_OUT_BUTTON = By.xpath("//a[@title=\"Sign out\"]");
    private static final By USERNAME_VALIDATION_ERROR = By.id("user-box-validation");
    private static final By PASSWORD_VALIDATION_ERROR = By.id("password-box-validation");
    private static final By INVALID_CREDENTIALS_ERROR = By.cssSelector(".validation-summary-errors");
    public WebDriver driver;

    @BeforeMethod
    public void init() {
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @Test(dataProvider = "xmlDataProvider")
    public void login(String username, String password, String validationType) {
        driver.findElement(USERNAME_FIELD).sendKeys(username);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        if (validationType == "correctCreds") {
            Assert.assertTrue(driver.findElement(SIGN_OUT_BUTTON).isDisplayed());
        }
        if (validationType == "incorrectPass") {
            Assert.assertEquals("Password is required", driver.findElement(PASSWORD_VALIDATION_ERROR).getText());
        }
        if (validationType == "incorrectUsername") {
            Assert.assertEquals("*Invalid credentials.", driver.findElement(INVALID_CREDENTIALS_ERROR).getText());
        }
        if (validationType == "noUsernameAndPass") {
            Assert.assertEquals("Username is required", driver.findElement(USERNAME_VALIDATION_ERROR).getText());
            Assert.assertEquals("Password is required", driver.findElement(PASSWORD_VALIDATION_ERROR).getText());
        }
        if (validationType == "incorrectUsernameAndPass") {
            Assert.assertEquals("*Invalid credentials.", driver.findElement(INVALID_CREDENTIALS_ERROR).getText());
        }
    }

    @AfterMethod
    public void quit() {
        driver.close();
    }
}