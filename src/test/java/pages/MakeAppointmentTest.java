package pages;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.DateAndTimeHelper;

import static org.testng.Assert.*;

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
        String facility = "Hongkong CURA Healthcare Center";
        makeApptPage.selectFacilityByText(facility);
        makeApptPage.checkMedicaidRadio();

        //book an appointment 10 days from now
        String visitDate = dtHelper.getPastDaysInString(10);
        makeApptPage.enterVisitDate(visitDate);
        makeApptPage.enterComment("book me in for the future");

        confirmationPage = makeApptPage.submit();
        assertTrue(confirmationPage.isInitialized());
        validateConfirmationTest(facility, "Medicaid", visitDate);
    }

    @Test
    public void makeApptInThePastTest(){
        String facility = "Tokyo CURA Healthcare Center";
        makeApptPage.selectFacilityByText(facility);
        makeApptPage.checkMedicareRadio();

        //book an appointment 10 days in the past
        String visitDate = dtHelper.getPastDaysInString(10);
        makeApptPage.enterVisitDate(visitDate);
        makeApptPage.enterComment("book me in for the past");

        confirmationPage = makeApptPage.submit();
        assertTrue(confirmationPage.isInitialized());
        validateConfirmationTest(facility, "Medicare", visitDate);
    }

    @Test
    public void makeApptWithNoDateTest(){
        makeApptPage.selectFacilityByText("Tokyo CURA Healthcare Center");
        makeApptPage.checkMedicareRadio();
        makeApptPage.enterComment("book me in for the past");

        try {
            confirmationPage = makeApptPage.submit();
            assertFalse(confirmationPage.isInitialized());
        } catch(NoSuchElementException e) {
            System.err.println("----- Cannot initialize the Appointment Confirmation page.");
        }
    }

    @Test
    public void makeApptFunkyCharactersInCommentTest(){
        String facility = "Seoul CURA Healthcare Center";
        makeApptPage.selectFacilityByText(facility);
        makeApptPage.checkNoneRadio();

        //book an appointment 12 days from now
        String visitDate = dtHelper.getPastDaysInString(12);
        makeApptPage.enterVisitDate(visitDate);
        makeApptPage.enterComment("book me #$%^$^&%&%^*^*^*^*$@:");

        confirmationPage = makeApptPage.submit();
        assertTrue(confirmationPage.isInitialized());
        validateConfirmationTest(facility, "None", visitDate);
    }

    public void validateConfirmationTest(String facility, String program, String date){
        assertEquals(confirmationPage.getConfirmationText(), "Appointment Confirmation");
        assertEquals(confirmationPage.getFacilityText(), facility);
        assertEquals(confirmationPage.getProgramText(), program);
        assertEquals(confirmationPage.getVisitDateText(), date);
    }

    @AfterMethod
    public void logout() {
        confirmationPage.logout();
    }
}
