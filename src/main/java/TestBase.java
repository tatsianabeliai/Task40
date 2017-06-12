import dataprovider.DataProviderXML;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class TestBase extends DataProviderXML {
    public WebDriver driver;

    protected String baseUrl;

    public TestBase(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @BeforeSuite
    public void init() {
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //1.	Add implicit waiter for WebDriver.
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @AfterSuite
    public void quit() {
        driver.quit();
    }
}
