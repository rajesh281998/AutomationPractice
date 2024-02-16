package browseFrontend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseWebPage<T extends BaseWebPage<T>>{
    protected WebDriver driver;
    public BaseWebPage(final WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);// this indicating the current class where @findBy is used.
    }
    public void explicitWait(WebElement element, int timeout){
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ele -> element.isDisplayed());
    }
    public BaseWebPage click(WebElement element, int timeout) {
        String stepName = "Click  on element - " + element;
        //log.info(stepName);
        try {
            explicitWait(element, timeout);
            element.click();
            //extentTest.pass(stepName);
        } catch (Exception ex) {
            // log.error("Unable to click element - "+element + "Exception thrown :::"+ex.getMessage());
            // extentReportFailureSetup(extentTest,stepName + "--->" + ex);
            throw ex;
        }
        return this;
    }

    /**
     * verify if exists.
     *
     * @param element WebElement, timeout
     */
    public void isElementVisible(WebElement element, int timeOutInSeconds) {
        String stepName = "Verifying visibility of element - " + element;
        try {
            //log.info(stepName);
            explicitWait(element, timeOutInSeconds);
           // Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            //wait.until(ExpectedConditions.visibilityOf(element));
            //extentTest.pass(stepName);
        } catch (Exception ex) {
            throw ex;
        }

    }
    public BaseWebPage type(WebElement element, String text, boolean clear) {
        String stepName = "Enter text as - " + text;
        //log.info(stepName.replaceAll("Enter", "Entering"));
        try {
            if (clear == true) element.clear();
            element.sendKeys(text);
            // extentTest.pass(stepName);
        } catch (Exception ex) {
            throw ex;
            // log.error("Unable to enter text - "+text + "Exception thrown :::"+ex.getMessage());
            // extentReportFailureSetup(extentTest
        }
        return this;
    }


}
