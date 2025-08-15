package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class P01_HomePage extends PageBase {

    public P01_HomePage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By loginButton = By.xpath("//button[@data-testid='login-link']");
    private final By logoutButton = By.xpath("//button[@data-testid='logout-button']");
    private final By searchInput = By.xpath("//input[@data-testid='search-input']");
    private final By searchResult = By.xpath("//div[@class='font-medium']");
    private final By wishlistButton = By.xpath("//a[@data-testid=\"wishlist-link\"]");
    private final By cartButton = By.xpath("//a[@data-testid=\"cart-link\"]");
    private final By currency = By.xpath("//select[@data-testid=\"currency-selector\"]");
    private final By priceElement = By.xpath("//p[contains(@class, 'font-bold') and contains(text(), '€')]");

    private final List<String> addedWishlistTestIds = new ArrayList<>();
    private final List<String> addedCartTestIds = new ArrayList<>();

    private final By wishListIcons = By.xpath("//*[starts-with(@data-testid, 'wishlist-button-')]");
    private final By addToCartButtons = By.xpath("//*[starts-with(@data-testid, 'add-to-cart-')]");

    public P01_HomePage clickOnLoginButton() {
        WebElement button = shortWait(driver).until(
                ExpectedConditions.elementToBeClickable(loginButton)
        );
        button.click();
        return this;
    }

    public P01_HomePage clickOnLogoutButton() {
        WebElement button = shortWait(driver).until(
                ExpectedConditions.elementToBeClickable(logoutButton)
        );
        button.click();
        return this;
    }

    public P01_HomePage clickOnWishListButton() {
        WebElement button = shortWait(driver).until(
                ExpectedConditions.elementToBeClickable(wishlistButton)
        );
        button.click();
        return this;
    }
    public P01_HomePage clickOnCartButton() {
        WebElement button = shortWait(driver).until(
                ExpectedConditions.elementToBeClickable(cartButton)
        );
        button.click();
        return this;
    }

    public String getDisplayedUsername() {
        By usernameLocator = By.xpath("//span[@class='text-sm text-gray-600']");

        try {
            WebElement userElement = longWait(driver).until(
                    ExpectedConditions.visibilityOfElementLocated(usernameLocator)
            );
            return userElement.getText().trim();

        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("Username element not found or not visible: " + e.getMessage());
            return null;  // or "" depending on how you want to handle missing username
        }
    }


    // New methods for input interactions

    public P01_HomePage enterSearchInput(String value) {
        WebElement input = shortWait(driver).until(
                ExpectedConditions.visibilityOfElementLocated(searchInput)
        );
        input.clear();
        input.sendKeys(value);
        return this;
    }



    public List<String> clickRandomWishlistItemsAndGetTestIds(int howManyToClick) {
        addedWishlistTestIds.clear();

        List<WebElement> allWishlistButtons = driver.findElements(wishListIcons);
        if (allWishlistButtons.size() < howManyToClick) {
            throw new IllegalArgumentException("Not enough wishlist buttons.");
        }

        Collections.shuffle(allWishlistButtons);
        List<WebElement> selectedButtons = allWishlistButtons.subList(0, howManyToClick);

        for (WebElement button : selectedButtons) {
            String testId = button.getAttribute("data-testid"); // e.g. wishlist-button-3
            addedWishlistTestIds.add(testId.replace("wishlist-button", "wishlist-item"));
            button.click();
        }

        return new ArrayList<>(addedWishlistTestIds);
    }

    public List<String> clickRandomAddToCartItemsAndGetTestIds(int howManyToClick) {
        addedCartTestIds.clear();

        List<WebElement> allCartButtons = driver.findElements(addToCartButtons);
        if (allCartButtons.size() < howManyToClick) {
            throw new IllegalArgumentException("Not enough add-to-cart buttons.");
        }

        Collections.shuffle(allCartButtons);
        List<WebElement> selectedButtons = allCartButtons.subList(0, howManyToClick);

        for (WebElement button : selectedButtons) {
            String testId = button.getAttribute("data-testid"); // e.g. add-to-cart-7
            addedCartTestIds.add(testId.replace("add-to-cart", "cart-item"));
            button.click();
        }

        return new ArrayList<>(addedCartTestIds);
    }
    public P01_HomePage changeCurrencyToEuro() {
        WebElement currencyDropdown = shortWait(driver).until(
                ExpectedConditions.elementToBeClickable(currency)
        );
        Select select = new Select(currencyDropdown);
        select.selectByValue("EUR");
        return this;
    }

    // التحقق من تغيير العلامة لـ €
    public boolean isCurrencyChangedToEuro() {
        WebElement price = shortWait(driver).until(
                ExpectedConditions.visibilityOfElementLocated(priceElement)
        );
        return price.getText().contains("€");
    }

    public boolean isSearchResultContains(String expectedText) {
        WebElement result = shortWait(driver).until(
                ExpectedConditions.visibilityOfElementLocated(searchResult)
        );
        return result.getText().toLowerCase().contains(expectedText.toLowerCase());
    }
}
