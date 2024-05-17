package seleniumTests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AbstractTest {
    private WebDriver driver;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        getWebDriver().get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void teardown() {
        driver.quit();
    }

    public WebDriver getWebDriver() {
        return driver;
    }
}
