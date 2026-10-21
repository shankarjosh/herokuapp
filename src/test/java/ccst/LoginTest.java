package ccst;
import ccst.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
public class LoginTest extends BaseTest {
    @Test(priority = 1, description = "Verify successful login with valid credentials")
    public void testPositiveLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"),
                "User was not redirected to the inventory page. Current URL: " + currentUrl);
        Assert.assertTrue(loginPage.isInventoryDisplayed(),
                "Product catalog container is not visible post-login.");
        markTestStatus("passed", "User logged in successfully", driver);
    }
    @Test(priority = 2, description = "Verify error message when logging in with invalid credentials")
    public void testNegativeLogin() {
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("locked_out_user", "invalid_password");
            String actualError = loginPage.getErrorMessage();
            String expectedError = "Epic sadface: Username and password do not match any user in this service";
            Assert.assertEquals(actualError, expectedError,
                    "Validation message does not match expected text.");
            Assert.assertTrue(loginPage.getCurrentUrl().equals(BASE_URL) || loginPage.getCurrentUrl().equals(BASE_URL + "index.html"),
                    "User should remain on the login page after a failed attempt.");
            markTestStatus("Passed", "Invalid credentials used for login", driver);
        }catch (Exception e) {
            markTestStatus("failed", "Login Test Failed "+ e.getMessage(), driver);
        }
    }
}