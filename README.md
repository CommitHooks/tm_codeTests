# tm_codeTests

### Prerequisites

- IntelliJ IDE
- Java 1.8
- Gradle 4.8
- The following web drivers have been installed
    - ChromeDriver 2.45
    - MicrosoftWebDriver 10.0
    - GeckoDriver 0.24
    
### Code Structure

- Sources: `src/main/java/pages`

    ```
    Super class - PageObject.java
    Classes the represent individual pages:
        - HomePage.java
        - LoginPage.java
        - MakeAppointmentPage.java
        - AppointmentConfirmationPage.java
    ```

- Tests: `src/test/java/pages`

    ```
    Super class - SeleniumTest.java
    Test Class - MakeAppointmentTest.java
    Util Class - DateAndTimeHelper.java
    ```

 ## Run the tests

   1. Import tm_codeTests as a gradle project to IntelliJ
   2. Under `src/test`, find the file called `test_across_browsers.xml`
   3. Right click on the file name and choose `Run <path to test_across_browsers.xml>`
   4. Test result is showed as below:
   
        ![Screenshot](images/testResult.jpg)

