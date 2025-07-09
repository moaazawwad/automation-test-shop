package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class P06_CheckoutPage extends PageBase{

    public P06_CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private final By continueToPayment = By.xpath("//button[@data-testid='submit-shipping']");
    private final By firstNameText = By.xpath("//input[@data-testid='firstname-input']");
    private final By lastNameText = By.xpath("//input[@data-testid='lastname-input']");
    private final By street = By.xpath("//input[@data-testid='address-input']");
    private final By city = By.xpath("//input[@data-testid='city-input']");
    private final By state = By.xpath("//input[@data-testid='state-input']");
    private final By postalCode = By.xpath("//input[@data-testid='postal-code-input']");
    private final By phoneNumber = By.xpath("//input[@data-testid='phone-input']");
    private final By cardNumber = By.xpath("//input[@data-testid='card-number-input']");
    private final By cardHolder = By.xpath("//input[@data-testid='card-holder-input']");
    private final By expiryDate = By.xpath("//input[@data-testid='expiry-date-input']");
    private final By cvv = By.xpath("//input[@data-testid='cvv-input']");
    private final By payNowButton = By.xpath("//button[@data-testid='submit-payment']");
    private final By successMessage = By.xpath("//h1[text()=\"Payment Successful!\"]");




    public P06_CheckoutPage clickOnContinueToPaymentButton() {
        WebElement button = shortWait(driver).until(ExpectedConditions.elementToBeClickable(continueToPayment));
        button.click();
        return this;
    }

    public P06_CheckoutPage enterFirstName(String firstName) {
        WebElement input = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(firstNameText));
        input.sendKeys(firstName);
        return this;
    }

    public P06_CheckoutPage enterLastName(String lastName) {
        WebElement input = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(lastNameText));
        input.sendKeys(lastName);
        return this;
    }

    public P06_CheckoutPage enterStreet(String address) {
        WebElement input = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(street));
        input.sendKeys(address);
        return this;
    }

    public P06_CheckoutPage enterCity(String cityName) {
        WebElement input = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(city));
        input.sendKeys(cityName);
        return this;
    }

    public P06_CheckoutPage enterState(String stateName) {
        WebElement input = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(state));
        input.sendKeys(stateName);
        return this;
    }

    public P06_CheckoutPage enterPostalCode(String code) {
        WebElement input = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(postalCode));
        input.sendKeys(code);
        return this;
    }

    public P06_CheckoutPage enterPhoneNumber(String number) {
        WebElement input = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(phoneNumber));
        input.sendKeys(number);
        return this;
    }

    public P06_CheckoutPage enterCardNumber(String card) {
        WebElement input = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(cardNumber));
        input.sendKeys(card);
        return this;
    }

    public P06_CheckoutPage enterCardHolder(String holder) {
        WebElement input = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(cardHolder));
        input.sendKeys(holder);
        return this;
    }

    public P06_CheckoutPage enterExpiryDate(String date) {
        WebElement input = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(expiryDate));
        input.sendKeys(date);
        return this;
    }

    public P06_CheckoutPage enterCVV(String cvvCode) {
        WebElement input = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(cvv));
        input.sendKeys(cvvCode);
        return this;
    }

    public P06_CheckoutPage clickOnPayNowButton() {
        WebElement button = shortWait(driver).until(ExpectedConditions.elementToBeClickable(payNowButton));
        button.click();
        return this;
    }

    public String getSuccessMessage() {
        WebElement message = longWait(driver).until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return message.getText().trim();
    }
}