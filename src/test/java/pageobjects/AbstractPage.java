package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AbstractPage {
    private final WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public void scrollTo(WebElement element) {
        ((JavascriptExecutor)getWebDriver()).executeScript("arguments[0].scrollIntoView();", element);
    }
}
