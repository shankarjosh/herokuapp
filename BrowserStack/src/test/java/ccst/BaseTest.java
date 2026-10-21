package ccst;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
public class BaseTest {
    protected WebDriver driver;
    protected  static final String USERNAME = "shankarjoshi_K9fmFk";
    protected  static final String ACCESS_KEY = "VJpse4S59yiLfuiGqbAT";
    protected  static final String BROWSERSTACK_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
    protected final String BASE_URL = "https://www.saucedemo.com/";
    public void markTestStatus(String status, String reason, WebDriver diver){
        JavascriptExecutor js = (JavascriptExecutor) diver;
        js.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"" + status + "\", \"reason\": \"" + reason + "\"}}");
    }
    @Parameters({"os", "os_version", "browserName", "browserVersion"})

    @BeforeMethod
    public void setUp(@Optional("") String os,
                      @Optional("") String os_version,
                      @Optional("") String browserName,
                      @Optional("") String browserVersion) throws Exception {
        DesiredCapabilities obj_caps = new DesiredCapabilities();
        HashMap<String, Object> obj_stackOptions = new HashMap<>();
        obj_stackOptions.put("os", os);
        obj_stackOptions.put("osVersion", os_version);
        obj_stackOptions.put("projectName","CCST BrowserStack Demo");
        obj_stackOptions.put("buildName","TestNG Build 1.0");
        obj_stackOptions.put("sessionName","BrowserStack Demo");

//attach browserStack Options
        obj_caps.setCapability("bstack:options",obj_stackOptions);

// W3C browser capability
        obj_caps.setCapability("browserName", browserName);
        obj_caps.setCapability("browserVersion", browserVersion);

//OLD Script chrome options
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--start-maximized");
//        driver = new ChromeDriver(options);
        driver = new RemoteWebDriver(new URL(BROWSERSTACK_URL), obj_caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

