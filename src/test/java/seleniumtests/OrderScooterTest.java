package seleniumtests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobjects.HomePage;

@RunWith(Parameterized.class)
public class OrderScooterTest extends AbstractTest {
    private final String customerName;
    private final String customerSurname;
    private final String customerAddress;
    private final String customerPhoneNumber;

    public OrderScooterTest (String customerName, String customerSurname, String customerAddress,
                             String customerPhoneNumber) {
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                { "Савелий", "Петров", "Москва, улица Шарикоподшипниковская, дом 5", "+7953017627"},
                { "Ангелина", "Иванова", "Санкт-Петербург, улица Александра Невского, дом 1", "+79530176211"}
        };
    }
    @Test
    public void orderScooterHeaderOrderButtonFillOnlyRequiredFieldsTest() {
        new HomePage(getWebDriver())
                .clickHeaderOrderButton()
                .fillAboutCustomerForm(customerName, customerSurname, customerAddress, customerPhoneNumber)
                .fillRentFormOnlyRequiredFields()
                .createOrder()
                .checkOrderHasCreated();
    }
    @Test
    public void orderScooterBottomOrderButtonFillOnlyRequiredFieldsTest() {
        new HomePage(getWebDriver())
                .clickBottomOrderButton()
                .fillAboutCustomerForm(customerName, customerSurname, customerAddress, customerPhoneNumber)
                .fillRentFormOnlyRequiredFields()
                .createOrder()
                .checkOrderHasCreated();
    }
}
