import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertsTest extends TestBase {
    private static final By CLICK_FOR_JS_ALERT_BUTTON = By.xpath("//button[@onclick=\"jsAlert()\"]");
    private static final By CLICK_FOR_JS_CONFIRM_BUTTON = By.xpath("//button[@onclick=\"jsConfirm()\"]");
    private static final By CLICK_FOR_JS_PROMPT_BUTTON = By.xpath("//button[@onclick=\"jsPrompt()\"]");
    private static final By RESULT_MESSAGE = By.xpath("//*[@id='result']");
    private static String siteURL = "https://the-internet.herokuapp.com/javascript_alerts";
    private String alertPromptText = "Hello world!";

    public AlertsTest() {
        super(siteURL);
    }

    @Test
    public void clickJSAlerts() {
        driver.findElement(CLICK_FOR_JS_ALERT_BUTTON).click();
        try {
            Alert alert = driver.switchTo().alert();
            String textOnAlert = alert.getText();
            Assert.assertEquals(textOnAlert, "I am a JS Alert");
            alert.accept();
            Assert.assertEquals(driver.findElement(RESULT_MESSAGE).getText(), "You successfuly clicked an alert");

        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void clickJSConfirm() {
        driver.findElement(CLICK_FOR_JS_CONFIRM_BUTTON).click();
        try {
            Alert alert = driver.switchTo().alert();
            String textOnAlert = alert.getText();
            Assert.assertEquals(textOnAlert, "I am a JS Confirm");
            alert.accept();
            Assert.assertEquals(driver.findElement(RESULT_MESSAGE).getText(), "You clicked: Ok");

        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void clickJSPrompt() {
        driver.findElement(CLICK_FOR_JS_PROMPT_BUTTON).click();
        try {
            Alert alert = driver.switchTo().alert();
            String textOnAlert = alert.getText();
            Assert.assertEquals(textOnAlert, "I am a JS prompt");
            alert.sendKeys(alertPromptText);
            alert.accept();
            Assert.assertEquals(driver.findElement(RESULT_MESSAGE).getText(), "You entered: " + alertPromptText);

        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }
}
