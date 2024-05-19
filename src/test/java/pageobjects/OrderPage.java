package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderPage extends AbstractPage {
    // Поле "Имя"
    private final By nameInput = By.xpath(".//input[contains(@placeholder, 'Имя')]");

    // Поле "Фамилия"
    private final By surnameInput = By.xpath(".//input[contains(@placeholder, 'Фамилия')]");

    // Поле "Адрес"
    private final By addressInput = By.xpath(".//input[contains(@placeholder, 'Адрес')]");

    // Поле "Станция метро"
    private final By metroStationInput = By.xpath(".//input[contains(@placeholder, 'Станция')]");

    // Кнопка выбора первой станции метро в выпадающем списке
    private final By firstMetroStationSelectOption = By.xpath(
            ".//button[contains(@class, 'select-search__option') and @value='1']");

    // Поле "Телефон"
    private final By phoneNumberInput = By.xpath(".//input[contains(@placeholder, 'Телефон')]");

    // Кнопка "Далее"
    private final By nextButton = By.xpath(".//div[contains(@class, 'Order_NextButton')]/button");

    // Поле "Когда привезти самокат"
    private final By dateInput = By.xpath(".//div[@class = 'react-datepicker__input-container']/input");

    // Поле "Срок аренды"
    private final By termButton = By.xpath(".//div[@class = 'Dropdown-control']");

    // Кнопка выбора опции "сутки" в выпадающем списке
    private final By termOneDayOption = By.xpath(".//div[@class = 'Dropdown-option' and text() = 'сутки']");

    // Кнопка "Заказать"
    private final By orderButton = By.xpath(".//div[contains(@class, 'Order_Buttons')]/button[text() = 'Заказать']");

    // Кнопка "Да" в модальном окне подтверждения оформления заказа
    private final By submitButton = By.xpath(".//div[contains(@class, 'Order_Modal')]//button[text() = 'Да']");

    // Сообщение об успешном создании заказа в модальном окне
    private final By orderCreatedText = By.xpath(".//div[contains(@class, 'Order_Modal')]/div[text() = 'Заказ оформлен']");

    public OrderPage(WebDriver driver) {
        super(driver);
        new WebDriverWait(getWebDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(nameInput));
    }

    public OrderPage inputName(String name) {
        getWebDriver().findElement(nameInput).sendKeys(name);
        return this;
    }

    public OrderPage inputSurname(String surname) {
        getWebDriver().findElement(surnameInput).sendKeys(surname);
        return this;
    }

    public OrderPage inputAddress(String address) {
        getWebDriver().findElement(addressInput).sendKeys(address);
        return this;
    }

    public OrderPage selectFirstMetroStation() {
        getWebDriver().findElement(metroStationInput).click();
        getWebDriver().findElement(firstMetroStationSelectOption).click();
        return this;
    }

    public OrderPage inputPhoneNumber(String phoneNumber) {
        getWebDriver().findElement(phoneNumberInput).sendKeys(phoneNumber);
        return this;
    }

    public OrderPage clickNextButton() {
        getWebDriver().findElement(nextButton).click();
        new WebDriverWait(getWebDriver(), Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(dateInput));
        return this;
    }

    public OrderPage inputDateNextDay() {
        String date = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        getWebDriver().findElement(dateInput).sendKeys(date);
        getWebDriver().findElement(dateInput).sendKeys(Keys.ENTER);
        return this;
    }

    public OrderPage selectTermOptionOneDay() {
        getWebDriver().findElement(termButton).click();
        new WebDriverWait(getWebDriver(), Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(termOneDayOption));
        getWebDriver().findElement(termOneDayOption).click();
        return this;
    }

    public OrderPage clickOrderButton() {
        getWebDriver().findElement(orderButton).click();
        return this;
    }

    public OrderPage clickSubmitButton() {
        new WebDriverWait(getWebDriver(), Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(submitButton));
        getWebDriver().findElement(submitButton).click();
        return this;
    }

    public void checkOrderHasCreated() {
        new WebDriverWait(getWebDriver(), Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(orderCreatedText));
        Assert.assertTrue("Не отобразилось сообщение об успешном создании заказа",
                getWebDriver().findElement(orderCreatedText).isDisplayed());
    }

    public OrderPage fillAboutCustomerForm(String name, String surname, String address, String phoneNumber) {
        inputName(name);
        inputSurname(surname);
        inputAddress(address);
        selectFirstMetroStation();
        inputPhoneNumber(phoneNumber);
        clickNextButton();
        return this;
    }

    public OrderPage fillRentFormOnlyRequiredFields() {
        inputDateNextDay();
        selectTermOptionOneDay();
        return this;
    }

    public OrderPage createOrder() {
        clickOrderButton();
        clickSubmitButton();
        return this;
    }
}
