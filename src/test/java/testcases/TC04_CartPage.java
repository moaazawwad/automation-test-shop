package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_HomePage;
import pages.P02_LoginPage;
import pages.P05_CartPage;
import pages.P06_CheckoutPage;

import java.util.List;

import static drivers.DriverHolder.getDriver;

public class TC04_CartPage extends TestBase {

    String email = "test@example.com";
    String password = "Test123!";

    /**
     * ✅ Test: Complete purchase flow from login to successful payment.
     */
    @Test(description = "Login, add items to cart, checkout, and verify successful payment message")
    public void verifyCompletePurchaseFlow() throws InterruptedException {
        P01_HomePage homePage = new P01_HomePage(getDriver());
        P02_LoginPage loginPage = new P02_LoginPage(getDriver());
        P05_CartPage cartPage = new P05_CartPage(getDriver());
        P06_CheckoutPage checkoutPage = new P06_CheckoutPage(getDriver());

        // Step 1: Login with valid credentials
        homePage.clickOnLoginButton();
        loginPage.enterEmail(email)
                .enterPassword(password)
                .clickOnLoginButton();

        // Step 2: Add 2 random items to cart
        List<String> addedCartItems = homePage.clickRandomAddToCartItemsAndGetTestIds(2);

        // Step 3: Navigate to cart
        homePage.clickOnCartButton();

        // Step 4: Validate cart items are displayed
        boolean itemsPresent = cartPage.areCartItemsPresent(addedCartItems);
        Assert.assertTrue(itemsPresent, "❌ Not all selected items are present in the cart.\nExpected: " + addedCartItems);

        // Step 5: Fill in shipping information
        cartPage.clickOnCheckoutButton();


        checkoutPage.enterFirstName("Ahmed")
                .enterLastName("Adly")
                .enterStreet("123 Main St")
                .enterCity("Cairo")
                .enterState("Giza")
                .enterPostalCode("12345")
                .enterPhoneNumber("01000000000")
                .clickOnContinueToPaymentButton();

        // Step 6: Fill in payment details and submit
        checkoutPage.enterCardNumber("4242 4242 4242 4242")
                .enterCardHolder("moaaz awwad")
                .enterExpiryDate("12/25")
                .enterCVV("123")
                .clickOnPayNowButton();

        // Step 7: Validate success message after payment
        String actualMessage = checkoutPage.getSuccessMessage();
        Assert.assertEquals(actualMessage, "Payment Successful!", "❌ Payment did not succeed as expected.");
    }
}
