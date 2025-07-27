package testcases;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_HomePage;
import pages.P02_LoginPage;
import pages.P05_CartPage;
import pages.P06_CheckoutPage;

import java.io.IOException;
import java.util.List;

import static drivers.DriverHolder.getDriver;
import static util.Utility.*;

public class TC04_CartPage extends TestBase {

    String credentials = "D:\\iqra\\automation\\tasks\\test-shop-automation\\src\\test\\resources\\data\\credentials.json";
    String email = getSingleJsonData(credentials, "email").toString();
    String password = getSingleJsonData(credentials, "password").toString();

    String paymentData = "D:\\iqra\\automation\\tasks\\test-shop-automation\\src\\test\\resources\\data\\payment_data.json";
    String cardNumber = getSingleJsonData(paymentData, "cardNumber").toString();
    String expireDate = getSingleJsonData(paymentData, "expireDate").toString();
    String cardHolder = getSingleJsonData(paymentData, "cardHolder").toString();
    String cvv = getSingleJsonData(paymentData, "cvv").toString();



    int day = generateRandomNumber(31);
    String firstName = generateRandomName(5);
    String lastName = generateRandomName(4);
    String street = generateRandomAddress();
    String city = generateRandomCity();
    String state = generateRandomState();
    String postalCode = generateRandomPostalCode();
    String phone = generateRandomSaudiNumber();
    public TC04_CartPage() throws IOException, ParseException {
    }

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


        checkoutPage.enterFirstName(firstName)
                .enterLastName(lastName)
                .enterStreet(street)
                .enterCity(city)
                .enterState(state)
                .enterPostalCode(postalCode)
                .enterPhoneNumber(phone)
                .clickOnContinueToPaymentButton();

        // Step 6: Fill in payment details and submit
        checkoutPage.enterCardNumber(cardNumber)
                .enterCardHolder(cardHolder)
                .enterExpiryDate(expireDate)
                .enterCVV(cvv)
                .clickOnPayNowButton();

        // Step 7: Validate success message after payment
        String actualMessage = checkoutPage.getSuccessMessage();
        Assert.assertEquals(actualMessage, "Payment Successful!", "❌ Payment did not succeed as expected.");
    }
}
