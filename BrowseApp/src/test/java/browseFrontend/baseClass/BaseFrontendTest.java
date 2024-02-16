package browseFrontend.baseClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.Map;

public class BaseFrontendTest {

    private String browserName;
    private static WebDriver webDriver = null;
    private  String webURL ;
    private static final ThreadLocal<WebDriver> webDriverHolder = new InheritableThreadLocal<WebDriver>();
    private ChromeOptions getChromeOptionsCaps() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBrowserVersion("latest");// take latest browser
        chromeOptions.setPageLoadTimeout(Duration.ofSeconds(20));
//        chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(10));
        WebDriverManager.chromedriver().setup();// match with system browser and Driver browser
        return chromeOptions;
    }

    private FirefoxOptions getFirefoxCapsOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBrowserVersion("latest");
        firefoxOptions.setPageLoadTimeout(Duration.ofSeconds(10));
        firefoxOptions.setImplicitWaitTimeout(Duration.ofSeconds(10));
        WebDriverManager.firefoxdriver().setup();
        return firefoxOptions;
    }

    private void initWebDriver(){
        if (browserName.equalsIgnoreCase("Chrome")) {
            webDriver = new ChromeDriver(getChromeOptionsCaps());
        } else if (browserName.equalsIgnoreCase("firefox")) {
            webDriver = new FirefoxDriver(getFirefoxCapsOptions());
        } else {
            throw new RuntimeException("No browser input provided");
        }
        webDriverHolder.set(webDriver);
        webDriver.manage().window().maximize();
        webDriver.get(webURL);

    }

    @BeforeSuite(alwaysRun = true)// it will run always and will not skip at any case
    protected void initTest(ITestContext testContext){
        Map<String, String> temp = testContext.getCurrentXmlTest().getAllParameters();
        browserName = temp.get("selenium.browser");
        webURL = temp.get("web.url");

    }

    public WebDriver getWebdriver(){
        if(webDriverHolder.get() instanceof WebDriver){
            return webDriverHolder.get();
        }
        else{
            initWebDriver();
            return webDriverHolder.get();
        }


    }

    /**
     * Clears the thread local cache.
     */
    private void clearThreadLocals() {

        try {
            webDriverHolder.remove();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @BeforeMethod
    protected void setup(){
        clearThreadLocals();
    }

    @AfterMethod
    protected void tearDown(){
        webDriverHolder.get().quit();
    }
}
