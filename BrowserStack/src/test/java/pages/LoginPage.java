package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
public class LoginPage extends BasePage {

    // Locators
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton   = By.id("login-button");
    private final By errorMessage  = By.cssSelector("[data-test='error']");
    private final By inventoryContainer = By.id("inventory_container");
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public void enterUsername(String username) {
        sendKeys(usernameField, username);
    }
    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }
    public void clickLogin() {
        click(loginButton);
    }
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
    public String getErrorMessage() {
        return getText(errorMessage);
    }
    public boolean isInventoryDisplayed() {
        return waitForVisibility(inventoryContainer).isDisplayed();
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}