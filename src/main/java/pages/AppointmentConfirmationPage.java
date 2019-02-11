package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppointmentConfirmationPage extends PageObject {

    @FindBy(xpath = "//div[@class='container']//h2")
    private WebElement confirmText;

    @FindBy(xpath = "//a[contains(text(),'Go to Homepage')]")
    private WebElement goToHomeBtn;

    @FindBy(xpath = "//p[@id='facility']")
    private WebElement facility;

    @FindBy(xpath = "//p[@id='program']")
    private WebElement program;

    @FindBy(xpath = "//p[@id='visit_date']")
    private WebElement visit_date;

    @FindBy(xpath = "//a[@id='menu-toggle']")
    private WebElement menuBtn;

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    private WebElement logoutBtn;

    public AppointmentConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return goToHomeBtn.isDisplayed();
    }

    public String getConfirmationText() {
        return confirmText.getText();
    }

    public String getFacilityText() {
        return facility.getText();
    }

    public String getProgramText() {
        return program.getText();
    }

    public String getVisitDateText() {
        return visit_date.getText();
    }

    public void logout() {
        menuBtn.click();
        if (!logoutBtn.isDisplayed()) {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOf(logoutBtn));
        }
        logoutBtn.click();
    }
}
