package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class P02_LoginPage extends PageBase {

    public P02_LoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By signUpButton = By.xpath("//a[@href=\"#/signup\"]");
    private final By emailInput = By.xpath("//input[@data-testid='email-input']");
    private final By passwordInput = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath("//button[@data-testid='login-button']");

    public P02_LoginPage clickOnSignUp() {
        WebElement button = shortWait(driver).until(
                ExpectedConditions.elementToBeClickable(signUpButton)
        );
        button.click();
        return this;
    }
    public P02_LoginPage enterEmail(String name) {
        WebElement firstName = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        firstName.sendKeys(name);
        return this;
    }

    public P02_LoginPage enterPassword(String name) {
        WebElement lastName = shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        lastName.sendKeys(name);
        return this;
    }
    public P02_LoginPage clickOnLoginButton() {
        WebElement button = shortWait(driver).until(
                ExpectedConditions.elementToBeClickable(loginButton)
        );
        button.click();
        return this;
    }
}
