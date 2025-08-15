package testcases;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_HomePage;
import pages.P02_LoginPage;

import java.io.IOException;

import static drivers.DriverHolder.getDriver;
import static util.Utility.getSingleJsonData;

public class TC02_LoginPage extends TestBase{
  String credentials = "D:\\iqra\\automation\\tasks\\test-shop-automation\\src\\test\\resources\\data\\credentials.json";
  String email = getSingleJsonData(credentials, "email").toString();
  String password = getSingleJsonData(credentials, "password").toString();
    public TC02_LoginPage() throws IOException, ParseException {
    }

    /**
   * ✅ Test: Login with valid credentials and verify successful redirection to home page
   */
  @Test(priority = 1, description = "Login with valid email and password, and verify successful login")
  public void verifyLoginWithValidCredentials() throws InterruptedException {
    // Arrange: Initialize home and login page objects
    P01_HomePage homePage = new P01_HomePage(getDriver());
    P02_LoginPage loginPage = new P02_LoginPage(getDriver());

    // Act: Navigate to login and submit form with valid credentials
    homePage.clickOnLoginButton();
    loginPage.enterEmail(email)
            .enterPassword(password)
            .clickOnLoginButton();

    // Assert: Check that the user is redirected to home/dashboard page
    String currentUrl = getDriver().getCurrentUrl();
    Assert.assertTrue(currentUrl.contains("/test-shop/#/"),
            "❌ Login failed or did not redirect to the expected home page URL.");


  }
}
