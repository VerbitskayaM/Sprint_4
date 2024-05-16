package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomeFourPart {
    private final WebDriver driver;

    // Шаблон локатора кнопки вопроса
    private final String questionTemplate = ".//div[@id='accordion__heading-%s']/parent::div";

    // Шаблон локатора ответа
    private final String answerTemplate = ".//div[@id='accordion__panel-%s']";

    public HomeFourPart(WebDriver driver) {
        this.driver = driver;
    }

    public void checkAnswerIsHidden(int index) {
        Assert.assertFalse(driver.findElement(By.xpath(String.format(answerTemplate, index))).isDisplayed());
    }

    public void checkAnswerIsDisplayedWithText(int index, String expectedText) {
        By answer = By.xpath(String.format(answerTemplate, index));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(answer));
        String actualText = driver.findElement(answer).getText();
        Assert.assertEquals("Фактический текст ответа не совпал с ожидаемым", expectedText, actualText);

    }

    public void clickQuestionButton(int index) {
        By question = By.xpath(String.format(questionTemplate, index));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(question));
        driver.findElement(question).click();
    }
}
