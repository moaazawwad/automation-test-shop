package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_HomePage;
import pages.P02_LoginPage;
import pages.P03_SignupPage;

import static drivers.DriverHolder.getDriver;
import static util.Utility.*;

public class TC03_SignupPage extends TestBase {

    int day = generateRandomNumber(31);
    String firstName = generateRandomName(5);
    String lastName = generateRandomName(4);
    String company = generateRandomName(4);
    public static String email = generateRandomEmail();
    public static String password = generateStrongPassword(12);

    /**
     * ✅ Test: Register a new user, login with same credentials, and verify username is displayed.
     */
    @Test(priority = 1, description = "Signup with valid data, then login and verify username is displayed")
    public void verifySignupAndLoginFlowWithValidData() throws InterruptedException {

        P01_HomePage homePage = new P01_HomePage(getDriver());
        P02_LoginPage loginPage = new P02_LoginPage(getDriver());
        P03_SignupPage signupPage = new P03_SignupPage(getDriver());

        // Step 1: Navigate to Signup page
        homePage.clickOnLoginButton();
        loginPage.clickOnSignUp();


        // Step 2: Fill out the signup form and submit
        signupPage.clickOnMaleGender()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterEmail(email)
                .enterCompany(company)
                .enterPassword(password)
                .enterConfirmationPassword(password)
                .selectDay(day)
                .selectRandomMonth()
                .selectRandomYear()
                .clickOnCreateAccountButton();

        // Step 3: Logout after successful signup
        homePage.clickOnLogoutButton();


        // Step 4: Login again with the same credentials
        homePage.clickOnLoginButton();
        loginPage.enterEmail(email)
                .enterPassword(password)
                .clickOnLoginButton();

        // Step 5: Verify displayed username matches full name
        String displayedUsername = homePage.getDisplayedUsername();
        String expectedUsername = firstName + " " + lastName;


        Assert.assertEquals(displayedUsername, expectedUsername,
                "❌ Displayed username does not match the expected full name after login.");
    }
}
