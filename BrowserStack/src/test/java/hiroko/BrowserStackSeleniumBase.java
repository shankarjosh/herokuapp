package hiroko;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;

public class BrowserStackSeleniumBase {

    protected WebDriver driver;

    private static final String USERNAME = "shankarjoshi_K9fmFk";
    private static final String ACCESS_KEY = "VJpse4S59yiLfuiGqbAT";
    private static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub.browserstack.com/wd/hub";

    // NOTICE: Only ITestContext argument! No @Parameters annotation!
    @BeforeMethod
    public void setUp(ITestContext context) {
        try {
            String browser = context.getCurrentXmlTest().getParameter("browser");
            String browserVersion = context.getCurrentXmlTest().getParameter("browserVersion");
            String os = context.getCurrentXmlTest().getParameter("os");
            String osVersion = context.getCurrentXmlTest().getParameter("osVersion");
            String deviceName = context.getCurrentXmlTest().getParameter("deviceName");

            DesiredCapabilities caps = new DesiredCapabilities();
            HashMap<String, Object> bstackOptions = new HashMap<>();

            bstackOptions.put("os", os);
            bstackOptions.put("osVersion", osVersion);
            bstackOptions.put("browserVersion", browserVersion != null ? browserVersion : "latest");
            bstackOptions.put("projectName", "HerokuApp Selenium Suite");
            bstackOptions.put("buildName", "TestNG Selenium Build 1.0");
            bstackOptions.put("sessionName", "HerokuApp Tests");

            if (deviceName != null && !deviceName.trim().isEmpty()) {
                bstackOptions.put("deviceName", deviceName);
            }

            caps.setCapability("browserName", browser);
            caps.setCapability("bstack:options", bstackOptions);

            URL gridUrl = URI.create(URL).toURL();
            driver = new RemoteWebDriver(gridUrl, caps);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}