package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class P03_SignupPage extends PageBase {

    public P03_SignupPage(WebDriver driver) {
        super(driver);
    }

    private final By maleGender = By.xpath("//input[@data-testid='gender-male']");
    private final By firstNameText = By.xpath("//input[@data-testid='firstname-input']");
    private final By lastNameText = By.xpath("//input[@data-testid='lastname-input']");
    private final By daySelect = By.xpath("//select[@data-testid='birth-day']");
    private final By monthSelect = By.xpath("//select[@data-testid='birth-month']");
    private final By yearSelect = By.xpath("//select[@data-testid='birth-year']");
    private final By emailInput = By.xpath("//input[@data-testid='email-input']");
    private final By companyInput = By.xpath("//input[@data-testid='company-input']");
    private final By passwordInput = By.xpath("//input[@data-testid='password-input']");
    private final By confirmationPasswordInput = By.xpath("//input[@data-testid=\"confirm-password-input\"]");
    private final By createAccountButton = By.xpath("//button[@data-testid='signup-button']");

    public P03_SignupPage clickOnMaleGender() {
        WebElement maleRadio = shortWait(driver).until(ExpectedConditions.elementToBeClickable(maleGender));
        maleRadio.click();
        return this;
    }

    public P03_SignupPage enterFirstName(String name) {
        WebElement firstName = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(firstNameText));
        firstName.sendKeys(name);
        return this;
    }

    public P03_SignupPage enterLastName(String name) {
        WebElement lastName = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(lastNameText));
        lastName.sendKeys(name);
        return this;
    }

    public P03_SignupPage enterEmail(String email) {
        WebElement emailField = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        emailField.sendKeys(email);
        return this;
    }

    public P03_SignupPage enterCompany(String company) {
        WebElement companyField = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(companyInput));
        companyField.sendKeys(company);
        return this;
    }

    public P03_SignupPage enterPassword(String password) {
        WebElement passwordField = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passwordField.sendKeys(password);
        return this;
    }

    public P03_SignupPage enterConfirmationPassword(String password) {
        WebElement confirmPasswordField = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(confirmationPasswordInput));
        confirmPasswordField.sendKeys(password);
        return this;
    }

    public P03_SignupPage clickOnCreateAccountButton() {
        WebElement createButton = shortWait(driver).until(ExpectedConditions.elementToBeClickable(createAccountButton));
        createButton.click();
        return this;
    }

    public P03_SignupPage selectDay(int dayIndex) {
        WebElement dayDropdown = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(daySelect));
        new Select(dayDropdown).selectByIndex(3);
        return this;
    }

    public P03_SignupPage selectRandomMonth() {
        WebElement monthDropdown = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(monthSelect));
        Select select = new Select(monthDropdown);
        List<WebElement> months = select.getOptions();
        if (months.size() > 1) {
            int randomIndex = new Random().nextInt(months.size() - 1) + 1;
            select.selectByIndex(randomIndex);
        }
        return this;
    }

    public P03_SignupPage selectRandomYear() {
        WebElement yearDropdown = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(yearSelect));
        Select select = new Select(yearDropdown);
        List<WebElement> options = select.getOptions();
        List<String> validYears = new ArrayList<>();

        for (WebElement option : options) {
            String yearText = option.getText().trim();
            try {
                int year = Integer.parseInt(yearText);
                if (year >= 1950 && year <= 2021) {
                    validYears.add(yearText);
                }
            } catch (NumberFormatException ignored) {}
        }

        if (!validYears.isEmpty()) {
            String selectedYear = validYears.get(new Random().nextInt(validYears.size()));
            select.selectByVisibleText(selectedYear);
        }

        return this;
    }
}