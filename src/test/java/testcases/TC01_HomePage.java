package testcases;

import io.qameta.allure.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_HomePage;
import pages.P07_WishlistPage;

import java.io.IOException;
import java.util.List;

import static drivers.DriverHolder.getDriver;
import static util.Utility.getRandomItemFromJsonArray;
import static util.Utility.getSingleJsonData;

@Epic("Home Page Functionalities")
@Story("User interactions from the home page")
public class TC01_HomePage extends TestBase {

    /**
     * ✅ Test #3: Change currency from USD to Euro and verify the change appears.
     */
    @Test(priority = 1, description = "Change currency to Euro and verify it is applied")
    public void verifyCurrencyChangeToEuro() throws InterruptedException {
        P01_HomePage homePage = new P01_HomePage(getDriver());

        homePage.changeCurrencyToEuro();

        Assert.assertTrue(homePage.isCurrencyChangedToEuro(),
                "❌ Currency did not change to Euro as expected.");
    }

    /**
     * ✅ Test #4: Search for product (e.g., Nike) and verify the result appears.
     */
    @Test(priority = 2, description = "Search for 'nike' and verify results contain expected keyword")
    public void verifySearchFunctionalityDisplaysExpectedResult() throws InterruptedException, IOException, ParseException {

        Object result = getSingleJsonData("D:\\iqra\\automation\\tasks\\test-shop-automation\\src\\test\\resources\\data\\products.json", "products");
        JSONArray productsArray = (JSONArray) result;  // ✅ cast to JSONArray

       String randomProduct = getRandomItemFromJsonArray(productsArray);
        P01_HomePage homePage = new P01_HomePage(getDriver());
        homePage.enterSearchInput(randomProduct);

        Assert.assertTrue(homePage.isSearchResultContains(randomProduct),
                "❌ Search result does not contain expected text 'nike'.");
    }
    /**
     * ✅ Test #2: Add random items to wishlist and verify they appear after navigation.
     */
    @Test(priority = 3, description = "Add random items to wishlist and verify they persist using data-testid")
    public void verifyWishlistItemsPersistAfterNavigation() throws InterruptedException {
        P01_HomePage homePage = new P01_HomePage(getDriver());

        // Step 1: Add 3 random items to wishlist and collect their data-testid attributes
        List<String> expectedTestIds = homePage.clickRandomWishlistItemsAndGetTestIds(3);
        System.out.println("✅ Expected Wishlist Test IDs: " + expectedTestIds);

        // Step 2: Navigate to the wishlist page
        homePage.clickOnWishListButton();

        // Step 3: Verify all added items exist in wishlist
        P07_WishlistPage wishlistPage = new P07_WishlistPage(getDriver());
        boolean allItemsPresent = wishlistPage.areWishlistItemsPresent(expectedTestIds);
        System.out.println("✅ All Items Present: " + allItemsPresent);

        // Step 4: Assertion
        Assert.assertTrue(allItemsPresent,
                "\n❌ Not all expected wishlist items were found.\nExpected IDs: " + expectedTestIds);

    }
}
