package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P07_WishlistPage extends PageBase{

    public P07_WishlistPage(WebDriver driver) {
        super(driver);
    }

    public boolean areWishlistItemsPresent(List<String> expectedTestIds) {
        for (String testId : expectedTestIds) {
            List<WebElement> elements = driver.findElements(By.cssSelector("[data-testid='" + testId + "']"));
            System.out.println("elements111" + elements);
            if (elements.isEmpty()) {
                System.out.println("❌ Missing item in wishlist: " + testId);
                return false;
            }
        }
        return true;
    }
}
