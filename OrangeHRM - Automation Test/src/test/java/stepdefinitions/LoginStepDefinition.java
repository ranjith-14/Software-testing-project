package stepdefinitions;

import java.util.Properties;
import java.util.Set;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class LoginStepDefinition {

    private static final Logger LOGGER = LogManager.getLogger(LoginStepDefinition.class);
    public WebDriver driver;
    public LoginPage loginPage;
    public HomePage homePage;
    public ConfigReader configs;
    public Properties props;

    @Given("The Login Page is Opened")
    public void the_login_page_is_opened() {
        LOGGER.info("Opening the login page...");
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);

        configs = new ConfigReader();
        props = configs.getProperties();

		loginPage.checkPrivateError();
        Assert.assertTrue(loginPage.usernamefieldDisplayed());
        LOGGER.info("Login page is open.");
    }

    @When("I fill the valid Login Credentials {string} and {string} and clicked Login")
    public void i_fill_the_valid_login_credentials_and_and_clicked_login(String username, String password) {
        LOGGER.info("Filling valid login credentials...");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        LOGGER.info("Clicked Login button with valid credentials.");
    }

    @Then("The User is logged in") 
    public void the_user_is_logged_in() {
        LOGGER.info("Verifying that the user is logged in...");
        homePage = new HomePage(driver);
        Assert.assertFalse(homePage.getrowOfBtns().isEmpty());
        LOGGER.info("User is logged in.");
    }

    @When("I fill the invalid Login Credentials {string} and {string} and clicked Login")
    public void i_fill_the_invalid_login_credentials_and_and_clicked_login(String username, String password) {
        LOGGER.info("Filling invalid login credentials...");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        LOGGER.info("Clicked Login button with invalid credentials.");
    }

    @Then("An error message is shown on {string} as {string}")
    public void an_error_message_is_shown_on_as(String errorLocation, String errorMessage) {
        LOGGER.info("Verifying error message...");
        switch(errorLocation) {
            case "username":
                Assert.assertEquals(loginPage.getUsernameAlertText(), errorMessage);
                break;
            case "password":
                Assert.assertEquals(loginPage.getPasswordAlertText(), errorMessage);
                break;
            case "both":
                Assert.assertEquals(loginPage.getUsernameAlertText(), errorMessage);
                Assert.assertEquals(loginPage.getPasswordAlertText(), errorMessage);
                break;
            case "homepage":
                Assert.assertEquals(loginPage.getinvalidCredsAlertText(), errorMessage);
                break;
        }
        LOGGER.info("Error message verified.");
    }

    @When("I logged in with valid credentials") 
    public void i_logged_in_with_valid_credentials(){
        LOGGER.info("Logging in with valid credentials...");
        loginPage.instantLogin();
        LOGGER.info("Logged in with valid credentials.");
    }

    @When("The User clicks log out") 
    public void the_user_clicks_log_out(){
        LOGGER.info("Clicking log out...");
        homePage.logoutAction();
        LOGGER.info("Logged out.");
    }

    @Then("The User is logged out") 
    public void the_user_is_logged_out(){
        LOGGER.info("Verifying that the user is logged out...");
        Assert.assertTrue(loginPage.usernamefieldDisplayed());
        LOGGER.info("User is logged out.");
    }

    @When("The User closes and reopens the browser") 
    public void the_user_closes_and_reopens_the_browser(){
        LOGGER.info("Closing and reopening the browser...");
        Set<Cookie> allcookies = driver.manage().getCookies();
        driver.close();
        driver = new DriverFactory().init_driver(props.getProperty("browser"));

        driver.get(props.getProperty("url"));
        driver.manage().deleteAllCookies();

        for (Cookie cookie: allcookies) {
            driver.manage().addCookie(cookie);
        }

        driver.get(props.getProperty("url"));
        LOGGER.info("Browser closed and reopened.");
    }

    @Then("The User is still logged in") 
    public void the_user_is_still_logged_in() {
        LOGGER.info("Verifying that the user is still logged in...");
        homePage = new HomePage(driver);
        Assert.assertFalse(homePage.getrowOfBtns().isEmpty());
        driver.quit();
        LOGGER.info("User is still logged in.");
    }

    @Given("The Login Page is Navigated")
    public void the_login_page_is_navigated() {
        LOGGER.info("Navigating to the login page...");
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);

        configs = new ConfigReader();
        props = configs.getProperties();

        driver.navigate().to(props.getProperty("url"));

		loginPage.checkPrivateError();
        Assert.assertTrue(loginPage.usernamefieldDisplayed());
        LOGGER.info("Login page is navigated.");
    }

    @When("The Back Button is clicked")
    public void the_back_button_is_clicked(){
        LOGGER.info("Clicking the back button...");
        driver.navigate().back();
        LOGGER.info("Back button clicked.");
    }

    @Then("The User is still logged out") 
	public void the_user_is_still_logged_out() {
        LOGGER.info("Verifying that the user is still logged out...");
        Assert.assertTrue(loginPage.usernamefieldDisplayed());
        LOGGER.info("User is still logged out.");
    }
}
