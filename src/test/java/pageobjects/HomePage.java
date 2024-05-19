package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends AbstractPage{

    // Шаблон локатора кнопки вопроса
    private final String questionButtonXpathTemplate = ".//div[@id='accordion__heading-%s']/parent::div";

    // Шаблон локатора ответа
    private final String answerXpathTemplate = ".//div[@id='accordion__panel-%s']";

    // Шаблон локатора кнопки заказа
    private final String orderButtonXpathTemplate = "(.//button[text() = 'Заказать'])[%s]";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage checkAnswerIsHidden(int index) {
        Assert.assertFalse(getWebDriver().findElement(By.xpath(String.format(answerXpathTemplate, index))).isDisplayed());
        return this;
    }

    public void checkAnswerIsDisplayedWithText(int index, String expectedText) {
        By answer = By.xpath(String.format(answerXpathTemplate, index));
        new WebDriverWait(getWebDriver(), Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(answer));
        String actualText = getWebDriver().findElement(answer).getText();
        Assert.assertEquals("Фактический текст ответа не совпал с ожидаемым", expectedText, actualText);
    }

    public HomePage clickQuestionButton(int index) {
        WebElement question = getWebDriver().findElement(By.xpath(String.format(questionButtonXpathTemplate, index)));
        scrollTo(question);
        question.click();
        return this;
    }

    public OrderPage clickHeaderOrderButton() {
        getWebDriver().findElement(By.xpath(String.format(orderButtonXpathTemplate, 1))).click();
        return new OrderPage(getWebDriver());
    }

    public OrderPage clickBottomOrderButton() {
        WebElement orderButton = getWebDriver().findElement(By.xpath(String.format(orderButtonXpathTemplate, 1)));
        scrollTo(orderButton);
        orderButton.click();
        return new OrderPage(getWebDriver());
    }
}
