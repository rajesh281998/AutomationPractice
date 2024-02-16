package browseFrontend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseWebPage{
    public LoginPage(WebDriver driver){
        super(driver);

    }

//    @FindBy(id = "login")
//    private WebElement loginBtn;

    @FindBy(xpath = "//button[text()=' Login with Email '] | //button[contains(text(),'Login with Email')]")
    private WebElement loginByEmail;

    @FindBy(xpath = "//input[@placeholder='Enter your email']")
    private WebElement enterEmail;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement enterPassword;

    @FindBy(xpath = "//button[@type='submit' and @class='common-btn']")
    private WebElement signIn;

//    public LoginPage clickLoginBtn() {
//        click(loginBtn, 10);
//        return this;
//    }

    public LoginPage clickLoginByEmail() {
        click(loginByEmail, 10);
        return this;
    }

    public LoginPage enterEmail(String usermail) {
        type(enterEmail,usermail,true);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(enterPassword,password,true);
        return this;
    }

    public LoginPage clickSignin() {
        click(signIn, 10);
        return this;
    }




}
