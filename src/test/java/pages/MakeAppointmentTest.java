package pages;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.DateAndTimeHelper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MakeAppointmentTest extends SeleniumTest {
    HomePage homePage;
    LoginPage loginPage;
    MakeAppointmentPage makeApptPage;
    AppointmentConfirmationPage confirmationPage;

    DateAndTimeHelper dtHelper = new DateAndTimeHelper();

    @BeforeMethod
    public void landOnMakeApptPage() {
        driver.get("http://demoaut.katalon.com");

        homePage = new HomePage(driver);
        assertTrue(homePage.isInitialized());

        loginPage = homePage.submit();
        assertTrue(loginPage.isInitialized());
        loginPage.enterUserName("John Doe");
        loginPage.enterPassword("ThisIsNotAPassword");

        makeApptPage = loginPage.submit();
        assertTrue(makeApptPage.isInitialized());
    }

    @Test
    public void makeApptHappyPathTest(){
        makeApptPage.selectFacilityByText("Hongkong CURA Healthcare Center");
        makeApptPage.checkMedicaidRadio();

        //book an appointment 10 days from now
        makeApptPage.enterVisitDate(dtHelper.getFutureDaysInString(10));
        makeApptPage.enterComment("book me in for the future");

        confirmationPage = makeApptPage.submit();
        assertTrue(confirmationPage.isInitialized());
        assertEquals(confirmationPage.getConfirmationText(), "Appointment Confirmation");

    }

    @Test
    public void makeApptInThePastTest(){
        makeApptPage.selectFacilityByText("Tokyo CURA Healthcare Center");
        makeApptPage.checkMedicareRadio();

        //book an appointment 10 days in the past
        DateAndTimeHelper dtHelper = new DateAndTimeHelper();
        makeApptPage.enterVisitDate(dtHelper.getPastDaysInString(10));
        makeApptPage.enterComment("book me in for the past");

        confirmationPage = makeApptPage.submit();
        assertTrue(confirmationPage.isInitialized());
        assertEquals(confirmationPage.getConfirmationText(), "Appointment Confirmation");
    }

    @Test
    public void makeApptFunkyCharactersInCommentTest(){
        makeApptPage.selectFacilityByText("Seoul CURA Healthcare Center");
        makeApptPage.checkNoneRadio();

        //book an appointment 12 days from now
        makeApptPage.enterVisitDate(dtHelper.getPastDaysInString(12));
        makeApptPage.enterComment("book me #$%^$^&%&%^*^*^*^*$@:");

        confirmationPage = makeApptPage.submit();
        assertTrue(confirmationPage.isInitialized());
        assertEquals(confirmationPage.getConfirmationText(), "Appointment Confirmation");
    }

    @AfterMethod
    public void logout() {
        confirmationPage.logout();
    }
}
