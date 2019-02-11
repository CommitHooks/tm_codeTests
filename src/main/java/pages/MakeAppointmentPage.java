package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class MakeAppointmentPage extends PageObject {

    @FindBy(xpath="//select[@id='combo_facility']")
    private WebElement facilityDropDownMenu;

    @FindBy(xpath="//input[@id='radio_program_medicare']")
    private WebElement medicareRadio;

    @FindBy(xpath="//input[@id='radio_program_medicaid']")
    private WebElement medicaidRadio;

    @FindBy(xpath="//input[@id='radio_program_none']")
    private WebElement noneRadio;

    @FindBy(xpath="//input[@id='txt_visit_date']")
    private WebElement visitDate;

    @FindBy(xpath="//textarea[@id='txt_comment']")
    private WebElement commentArea;

    @FindBy(xpath="//button[@id='btn-book-appointment']")
    private WebElement bookApptBtn;

    public MakeAppointmentPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return facilityDropDownMenu.isDisplayed();
    }

    public void selectFacilityByText(String facility) {
        Select facilityItems = new Select(facilityDropDownMenu);
        facilityItems.selectByVisibleText(facility);
    }

    public void checkMedicareRadio() {
        if (!medicareRadio.isSelected()) {
            this.medicareRadio.click();
        }
    }

    public void checkMedicaidRadio() {
        if (!medicaidRadio.isSelected()) {
            this.medicaidRadio.click();
        }
    }

    public void checkNoneRadio() {
        if (!noneRadio.isSelected()) {
            this.noneRadio.click();
        }
    }

    public void enterVisitDate(String date){
        this.visitDate.clear();
        visitDate.sendKeys(date);
        visitDate.sendKeys(Keys.TAB);
    }

    public void enterComment(String comment) {
        this.commentArea.clear();
        this.commentArea.sendKeys(comment);
    }

    public AppointmentConfirmationPage submit(){
        bookApptBtn.click();
        return new AppointmentConfirmationPage(driver);
    }
}

