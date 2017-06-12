import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IFrameTest extends TestBase {
    private static final By YOUR_CONTENT_GOES_HERE = By.cssSelector("#tinymce");
    private static final By BOLD_BUTTON = By.xpath("//div[@id='mceu_3']");
    private static final By STRONG_LABEL = By.xpath(".//*[@id='mceu_29']//div[contains(text(), \"strong\")]");
    private static String siteURL = "https://the-internet.herokuapp.com/iframe";

    public IFrameTest() {
        super(siteURL);
    }

    @Test
    public void clearText() {
        driver.get(siteURL);
        driver.switchTo().frame("mce_0_ifr");
        Actions builder = new Actions(driver);
        driver.findElement(YOUR_CONTENT_GOES_HERE).
                click();
        builder.sendKeys().
                sendKeys(Keys.CONTROL).
                sendKeys(Keys.chord("A")).
                sendKeys(Keys.BACK_SPACE).
                build().
                perform();
        Assert.assertTrue(driver.findElement(YOUR_CONTENT_GOES_HERE).getText().isEmpty(), "the text field is not empty");
    }

    @Test(dependsOnMethods = "clearText")
    public void enterText() {
        driver.findElement(YOUR_CONTENT_GOES_HERE).sendKeys("Hello world!");
        driver.switchTo().defaultContent();

        Actions builder = new Actions(driver);
        builder.
                sendKeys(Keys.CONTROL).
                sendKeys(Keys.ARROW_LEFT).
                sendKeys(Keys.CONTROL).
                sendKeys(Keys.ARROW_RIGHT).
                build().perform();

        driver.findElement(BOLD_BUTTON).click();
        builder.sendKeys(Keys.END). //select the "world!" text by the steps below to verify the text is displayed in bold
                sendKeys(Keys.SHIFT).
                sendKeys(Keys.CONTROL).
                sendKeys(Keys.LEFT).
                build().perform();
        Assert.assertTrue(driver.findElement(STRONG_LABEL).isDisplayed());
    }
}
