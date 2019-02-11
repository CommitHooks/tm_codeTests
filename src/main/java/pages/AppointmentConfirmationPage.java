package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppointmentConfirmationPage extends PageObject {

    @FindBy(xpath ="//div[@class='container']//h2")
    private WebElement confirmText;

    @FindBy(xpath ="//a[@id='menu-toggle']")
    private WebElement menuBtn;

    @FindBy(xpath ="//a[contains(text(),'Logout')]")
    private WebElement logoutBtn;

    public AppointmentConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return confirmText.isDisplayed();
    }

    public String getConfirmationText(){
        return confirmText.getText();
    }

    public void logout(){
        menuBtn.click();
        if (!logoutBtn.isDisplayed()) {
            WebDriverWait wait = new WebDriverWait(driver,5);
            wait.until(ExpectedConditions.visibilityOf(logoutBtn));
        }
        logoutBtn.click();
    }
}
