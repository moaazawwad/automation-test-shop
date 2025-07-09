package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_ProductDetails extends PageBase{

    public P04_ProductDetails(WebDriver driver) {
        super(driver);
    }

    private final By productName = By.xpath("//h1[@data-testid='product-name']");
    private final By addToCartButton = By.xpath("//button[@data-testid='add-to-cart']");
    private final By toggleWishlist = By.xpath("//button[@data-testid='toggle-wishlist']");
    private final By addToCompare = By.xpath("//button[@data-testid='add-to-compare']");

}
