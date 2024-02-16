package testClasses.loginTest;

import baseUtilFrontend.dataProviderUtill.DataProviderUtil;
import browseFrontend.LoginPage;
import browseFrontend.baseClass.BaseFrontendTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseFrontendTest {
    @Test(priority = 0,dataProvider = "BrowseDataProvider",dataProviderClass = DataProviderUtil.class)
    public void loginTest(String Username , String Password , String addinfo)
    {
        WebDriver driver=getWebdriver();
        LoginPage login = new LoginPage(driver);
        login.clickLoginByEmail().enterEmail(Username).enterPassword(Password).clickSignin();


    }




}
