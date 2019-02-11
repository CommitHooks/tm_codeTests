package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject {

    @FindBy(xpath="//input[@id='txt-username']")
    private WebElement uname;

    @FindBy(xpath="//input[@id='txt-password']")
    private WebElement password;

    @FindBy (xpath="//button[@id='btn-login']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return loginButton.isDisplayed();
    }

    public void enterUserName(String uname) {
        this.uname.clear();
        this.uname.sendKeys(uname);
    }

    public void enterPassword(String password) {
        this.password.clear();
        this.password.sendKeys(password);
    }

    public MakeAppointmentPage submit(){
        loginButton.click();
        return new MakeAppointmentPage(driver);
    }
}


