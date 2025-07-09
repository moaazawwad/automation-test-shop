package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class P05_CartPage extends PageBase {

    public P05_CartPage(WebDriver driver) {
        super(driver);
    }

    private final By checkoutButton = By.xpath("//button[@data-testid='checkout-button']");

    // === Action Methods ===

    public P05_CartPage clickOnCheckoutButton() {
        WebElement button = shortWait(driver).until(ExpectedConditions.elementToBeClickable(checkoutButton));
        button.click();
        return this;
    }
    // === Utility Method ===

    public boolean areCartItemsPresent(List<String> expectedTestIds) {
        for (String testId : expectedTestIds) {
            List<WebElement> elements = driver.findElements(By.cssSelector("[data-testid='" + testId + "']"));
            System.out.println("Checking cart item with testId: " + testId);
            if (elements.isEmpty()) {
                System.out.println("❌ Missing item in cart: " + testId);
                return false;
            }
        }
        return true;
    }
}
