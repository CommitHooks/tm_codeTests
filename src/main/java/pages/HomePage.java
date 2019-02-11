package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class HomePage extends PageObject {

    @FindBy(xpath="//a[@id='btn-make-appointment']")
    private WebElement makeApptBtn;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return makeApptBtn.isDisplayed();
    }

    public LoginPage submit(){
        makeApptBtn.click();
        return new LoginPage(driver);
    }
}
