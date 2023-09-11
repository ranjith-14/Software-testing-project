package stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.text.SimpleDateFormat;
import java.util.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.PersonalDetails;
import utils.ConfigReader;
import utils.DriverFactory;

public class PersonalDetailsStepDefiniton {

    private static final Logger LOGGER = LogManager.getLogger(PersonalDetailsStepDefiniton.class);
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ConfigReader configs;
    private Properties props;
    private PersonalDetails personalDetails;
    private JavascriptExecutor js;

    @Given("The User is Logged in using valid ESS credentials")
    public void the_user_is_logged_in_using_valid_ess_credentials() {
        LOGGER.info("Starting the_user_is_logged_in_using_valid_ess_credentials step...");
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);

        configs = new ConfigReader();
        props = configs.getProperties();
        js = (JavascriptExecutor) driver;

//		loginPage.checkPrivateError();
        Assert.assertTrue(loginPage.usernamefieldDisplayed());
        loginPage.instantLogin();

        homePage = new HomePage(driver);
        Assert.assertFalse(homePage.getrowOfBtns().isEmpty());

        LOGGER.info("Login successful. User is on the home page.");
    }

    @When("The User Clicks My Info Button")
    public void the_user_clicks_my_info_button() {
        LOGGER.info("Starting the_user_clicks_my_info_button step...");
        homePage.clickMyInfoBtn();
        Assert.assertTrue(homePage.getPersonalDetailsElement().isDisplayed());

        LOGGER.info("Clicked the My Info button. User is on the My Info page.");
    }

    @When("The User Clicks on Personal Details Button")
    public void the_user_clicks_on_personal_details_button() {
        LOGGER.info("Starting the_user_clicks_on_personal_details_button step...");
        homePage.clickPersonalDetailsBtn();
        personalDetails = new PersonalDetails(driver);
//        Assert.assertTrue(personalDetails.getfirstNamefield().isDisplayed());
        personalDetails.waitFullSpinner();
        LOGGER.info("Clicked on Personal Details button.");
    }
    
    @Then("Only First Name, Middle Name, Last Name,Other Id, Licence Expiry date,Nationality, Marital Status, Gender are Editable")
    public void only_first_name_middle_name_last_name_other_id_licence_expiry_date_nationality_marital_status_gender_are_editable() {
        LOGGER.info("Verifying editable fields...");
        Assert.assertTrue(personalDetails.getfirstNamefield().isEnabled());
        Assert.assertTrue(personalDetails.getlastNamefield().isEnabled());
        Assert.assertTrue(personalDetails.getmiddleNamefield().isEnabled());
        Assert.assertTrue(personalDetails.getOtherIDfield().isEnabled());
        Assert.assertTrue(personalDetails.getlicenceExpiryDatefield().isEnabled());
        Assert.assertTrue(personalDetails.getnationalityDropdown().isEnabled());
        Assert.assertTrue(personalDetails.getMaritalStatusDropdown().isEnabled());
        Assert.assertTrue(personalDetails.getMaleRadioBtn().isEnabled());
        Assert.assertTrue(personalDetails.getFemaleRadioBtn().isEnabled());
        LOGGER.info("Fields verification completed.");
    }

    @Then("Employee Id, Driver Licence Number are not editable")
    public void employee_id_driver_licence_number_are_not_editable() {
        LOGGER.info("Verifying non-editable fields...");
        Assert.assertFalse(personalDetails.getEmpIdField().isEnabled());
        Assert.assertFalse(personalDetails.getDriverLicenceField().isEnabled());
        Assert.assertFalse(personalDetails.getDateOfBirthField().isEnabled());
        LOGGER.info("Non-editable fields verification completed.");
    }

    @When("Fill the {string} field with credential {string}")
    public void fill_the_field_with_credential(String whereToTest, String valueToFill) {
        LOGGER.info("Filling field: " + whereToTest + " with value: " + valueToFill);
        personalDetails.clearAndFillField(whereToTest, valueToFill);
        LOGGER.info("Field filled.");
    }

    @Then("Click save and Verify {string} field with {string}")
    public void click_save_and_verify_field_with(String whereToVerify, String value) {
        LOGGER.info("Clicking save and verifying field: " + whereToVerify);
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", personalDetails.getDateOfBirthField());
        personalDetails.clickSaveBtn();
        personalDetails.waitFullSpinner();
        Assert.assertEquals(personalDetails.fieldVerify(whereToVerify), value.strip());
        LOGGER.info("Verification completed.");
    }

    @Then("Click save and Verify the {string} field with error_message {string}")
    public void click_save_and_verify_the_field_with_error_message(String whereToVerify, String errorMsg) {
        LOGGER.info("Clicking save and verifying field: " + whereToVerify);
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", personalDetails.getMaritalStatusDropdown());
        personalDetails.clickSaveBtn();
        Assert.assertEquals(personalDetails.fieldErrorMsgVerify(whereToVerify), errorMsg);
        LOGGER.info("Verification completed.");
    }

    @When("The User clicks on Licence Expiry Date Field")
    public void the_user_clicks_on_licence_expiry_date_field() {
        LOGGER.info("Clicking on Licence Expiry Date Field...");
        personalDetails.clicklicenceExpiryDateField();
        Assert.assertTrue(personalDetails.getlicenceExpiryDatePicker().isDisplayed());
        LOGGER.info("Licence Expiry Date Field clicked.");
    }

    @When("The User clicks on close button to close the Date Picker")
    public void the_user_clicks_on_close_button_to_close_the_date_picker() {
        LOGGER.info("Closing Date Picker...");
        personalDetails.clickDatePickerCloseBtn();
        Assert.assertTrue(personalDetails.getlicenceExpiryDatePickerclosed());
        LOGGER.info("Date Picker closed.");
    }

    @When("The User clicks on Date Picker Icon Button")
    public void the_user_clicks_on_date_picker_icon_button() {
        LOGGER.info("Clicking Date Picker Icon Button...");
        personalDetails.clickLicenceCalenderIcon();
        Assert.assertTrue(personalDetails.getlicenceExpiryDatePicker().isDisplayed());
        LOGGER.info("Date Picker Icon Button clicked.");
    }

    @When("The User clicks on Today Button and Verify")
    public void the_user_clicks_on_today_button_and_verify() {
        LOGGER.info("Clicking on Today Button...");
        personalDetails.clickDatePickerTodayBtn();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Assert.assertEquals(personalDetails.getlicenceExpiryDatefield().getAttribute("value"), formatter.format(date));
        LOGGER.info("Today Button clicked and verified.");
    }

    @Then("The User clicks on Clear Button and verify")
    public void the_user_clicks_on_clear_button_and_verify() {
        LOGGER.info("Clicking on Clear Button...");
        personalDetails.clickLicenceCalenderIcon();
        personalDetails.clickDatePickerClearBtn();
        Assert.assertEquals(personalDetails.getlicenceExpiryDatefield().getAttribute("value"), "");
        LOGGER.info("Clear Button clicked and verified.");
    }

    @Then("Picking a valid date as {string} and Verify")
    public void picking_a_valid_date_as_and_verify(String date) {
        LOGGER.info("Picking a date in Date Picker...");
        personalDetails.pickDateinDatePicker(date);
        Assert.assertEquals(personalDetails.getlicenceExpiryDatefield().getAttribute("value"), date);
        LOGGER.info("Date picked and verified.");
    }

    @When("Click the Help Button")
    public void click_the_help_button() {
        LOGGER.info("Clicking the Help Button...");
        personalDetails.clickHelpBtn();
        LOGGER.info("Help Button clicked.");
    }

    @Then("Verify the Help page")
    public void verify_the_help_page() {
        LOGGER.info("Switching to the Help page...");
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(driver.getWindowHandle())) {
                driver.switchTo().window(handle);
            }
        }
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("starterhelp.orangehrm.com"));
        LOGGER.info("Help page verified.");
    }

    @When("Select Nation as {string}")
    public void select_nation_as(String nation) {
        LOGGER.info("Selecting Nation as: " + nation);
        personalDetails.selectNation(nation);
        LOGGER.info("Nation selected.");
    }

    @Then("Verify the Nation is selected as {string}")
    public void verify_the_nation_is_selected_as(String nation) {
        LOGGER.info("Verifying selected Nation...");
        Assert.assertEquals(personalDetails.getSelectedNation().getText(), nation);
        LOGGER.info("Nation verified.");
    }

    @When("Select Marital Status as {string}")
    public void select_marital_status_as(String maritalStatus) {
        LOGGER.info("Selecting Marital Status as: " + maritalStatus);
        personalDetails.selectMaritalStatus(maritalStatus);
        LOGGER.info("Marital Status selected.");
    }

    @Then("Verify the Marital Status is selected as {string}")
    public void verify_the_marital_status_is_selected_as(String maritalStatus) {
        LOGGER.info("Verifying selected Marital Status...");
        Assert.assertEquals(personalDetails.getSelectedMaritalStatus().getText(), maritalStatus);
        LOGGER.info("Marital Status verified.");
    }

    @When("Select the Male radio btn and verify")
    public void select_the_male_radio_btn_and_verify() {
        LOGGER.info("Selecting the Male radio button...");
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", personalDetails.genderMaleBtn());
        personalDetails.genderMaleBtn().click();
        personalDetails.clickSaveBtn();
        personalDetails.waitFullSpinner();
        Assert.assertTrue(personalDetails.genderMaleInputBtn().isSelected());
        Assert.assertFalse(personalDetails.genderFeMaleInputBtn().isSelected());
        LOGGER.info("Male radio button selected and verified.");
    }

    @When("Select the Female radio btn and verify")
    public void select_the_female_radio_btn_and_verify() {
        LOGGER.info("Selecting the Female radio button...");
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", personalDetails.genderFeMaleBtn());
        personalDetails.genderFeMaleBtn().click();
        personalDetails.clickSaveBtn();
        personalDetails.waitFullSpinner();
        Assert.assertTrue(personalDetails.genderFeMaleInputBtn().isSelected());
        Assert.assertFalse(personalDetails.genderMaleInputBtn().isSelected());
        LOGGER.info("Female radio button selected and verified.");
    }

    @When("The User Upload a {string} file size {string} as {string}")
    public void the_user_upload_a_file_size_as(String fileName, String commentVerification, String comment) {
        LOGGER.info("Uploading a file: " + fileName + " with comment: " + comment);
        personalDetails.attachementUploader(fileName, commentVerification, comment);
        LOGGER.info("File uploaded.");
    }

    @Then("Verify the {string} file size {string} as {string}")
    public void verify_the_file_size_as(String fileName, String commentVerification, String comment) {
        LOGGER.info("Verifying uploaded file...");
        String[] values = personalDetails.attachementVerifier();
        Assert.assertEquals(values[0], fileName);
        Assert.assertEquals(values[1], comment);
        LOGGER.info("Uploaded file verified.");
    }

    @Then("The User attempts to upload the file_size {string} and verify the error message")
    public void the_user_attempts_to_upload_the_file_size_and_verify_the_error_message(String fileName) {
        LOGGER.info("Attempting to upload a file: " + fileName + " and verifying the error message...");
        personalDetails.attachementUploader(fileName, "", "");
        Assert.assertTrue(personalDetails.getAttachementErrorMsg().isDisplayed());
        LOGGER.info("Error message verified.");
    }
}
