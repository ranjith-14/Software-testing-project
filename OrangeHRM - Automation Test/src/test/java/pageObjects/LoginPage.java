package pageObjects;


import static org.testng.Assert.assertEquals;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utils.ConfigReader;
public class LoginPage {

	
	public LoginPage(WebDriver webdriver) {
		driver = webdriver;
	};
	

	WebDriver driver;
	ConfigReader configs = new ConfigReader();
	Properties props = configs.getProperties();
	
	//By Locators
	
	private By usernameLocator = By.name("username");
	private By passwordLocator = By.name("password");
	private By loginBtnLocator = By.xpath("//div[@class='orangehrm-login-form']/form/div/button");
	private By forgotPassLinkLocator = By.xpath("//div[@class='orangehrm-login-form']/form/div/p");
	private By usernameAlertLocator = By.xpath("(//div[@class='oxd-form-row']/div/span)[1]");
	private By passwordAlertLocator = By.xpath("(//div[@class='oxd-form-row']/div/span)[1]");
	private By invalidCredAlertLocator = By.xpath("//div[@class='orangehrm-login-error']/div/div/p");
	
	//Page Method/ Actions
	
	//Basic Field actions
	public boolean usernamefieldDisplayed() {
		return driver.findElement(usernameLocator).isDisplayed();
	}
	
	public void enterUsername(String userId) {
		WebElement username = driver.findElement(usernameLocator);
		username.sendKeys(userId);
	}
	
	public void enterPassword(String password) {
		WebElement username = driver.findElement(passwordLocator);
		username.sendKeys(password);
	}
	
	public void clickLogin() {
		WebElement username = driver.findElement(loginBtnLocator);
		username.click();
	}
	
	//Login Instantly
	public void instantLogin(){
		enterUsername(props.getProperty("ESS_user"));
		enterPassword(props.getProperty("ESS_password"));
		clickLogin();

		if(new HomePage(driver).getrowOfBtns().isEmpty()) {
			createESSuser();
		}
	}
	
	//Field Alerts
	public String getUsernameAlertText() {
		WebElement usernameAlertElement = driver.findElement(usernameAlertLocator);
		return usernameAlertElement.getText();
	}

	public String getPasswordAlertText() {
		WebElement passwordAlertElement = driver.findElement(passwordAlertLocator);
		return passwordAlertElement.getText();
	}

	public String getinvalidCredsAlertText() {
		WebElement invalidCredsAlertElement = driver.findElement(invalidCredAlertLocator);
		return invalidCredsAlertElement.getText();
	}

	
	//Error Page Navigator
	By privateErrorLocator = By.xpath("//div[@id='main-message']/h1");
	public void checkPrivateError() {
		try {
			WebElement privateError = driver.findElement(privateErrorLocator);
			if(privateError.isDisplayed()) {
				driver.findElement(By.id("details-button")).click();
				driver.findElement(By.partialLinkText("Proceed to localhost (unsafe)")).click();
			}
		} catch (Exception e) {
			
		}
	}
	
	
	
	//Create ESS User 
	public void createESSuser() {
//		driver.get("http://localhost/orangehrm/web/index.php/pim/addEmployee");
		enterUsername(props.getProperty("Admin_user"));
		enterPassword(props.getProperty("Admin_password"));
		clickLogin();
		
		WebElement pimElement = driver.findElement(By.xpath("//ul[@class='oxd-main-menu']/li[.//span[text()='PIM']]"));
		pimElement.click();
		
		WebElement empAddBtn = driver.findElement(By.xpath("//div[@class='orangehrm-paper-container']/div/button"));
		empAddBtn.click();
		
		try {
			WebElement firstNameElement = driver.findElement(By.name("firstName"));
			WebElement lastNameElement = driver.findElement(By.name("lastName"));
			firstNameElement.sendKeys(props.getProperty("ESS_user").split(" ")[0]);
			lastNameElement.sendKeys(props.getProperty("ESS_user").split(" ")[1]);
			
			WebElement createLoginDetailsCheck = driver.findElement(By.xpath("//div[@class='oxd-switch-wrapper']/label/span"));
			createLoginDetailsCheck.click();
			
			WebElement usernameElement = driver.findElement(By.xpath("(//div[@class='oxd-form-row']/div/div/div/div[2]/input)[2]"));
			usernameElement.sendKeys(props.getProperty("ESS_user"));
			
			WebElement passwordElement = driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[1]/div/div[2]/input"));
			WebElement confirmPasswordElement = driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[2]/div/div[2]/input"));
			passwordElement.sendKeys(props.getProperty("ESS_password"));
			confirmPasswordElement.sendKeys(props.getProperty("ESS_password"));
			
			WebElement saveBtn = driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]"));
			saveBtn.click();
			
			WebElement toastMesssage = driver.findElement(By.xpath("//div[contains(@class,'oxd-toast-content')]/p[1]"));
			assertEquals(toastMesssage.getText(), "Success");
			
		} catch (Exception e) {
		}
		
		new HomePage(driver).logoutAction();
		
		enterUsername(props.getProperty("ESS_user"));
		enterPassword(props.getProperty("ESS_password"));
		clickLogin();
	}
}
