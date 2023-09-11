package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	public HomePage(WebDriver webdriver) {
		driver = webdriver;
	};
	

	WebDriver driver;
	
	//By Locators
	By usernameLocator = By.className("oxd-userdropdown-name");
	By rowOfBtnsLocator = By.xpath("//ul[@class='oxd-main-menu']");
	By userDropDownLocator = By.className("oxd-userdropdown-tab");
	By LogoutBtnLocator = By.xpath("//ul[@class='oxd-dropdown-menu']/li[4]");
	By locatorOfPIM = By.xpath("//ul[@class='oxd-main-menu']/li[.//span[text()='PIM']]");
	By locatorofMyInfo = By.xpath("//ul[@class='oxd-main-menu']/li[.//span[text()='My Info']]");
	By personalDetailsLocator =	By.xpath("//div[@class='orangehrm-tabs-wrapper'][./a[text()='Personal Details']]");


	//Page Elements
	public WebElement getUsername() {
        return driver.findElement(usernameLocator);
	}

	public List<WebElement> getrowOfBtns() {
        return driver.findElements(rowOfBtnsLocator);
	}
	public WebElement getPersonalDetailsElement() {
		return driver.findElement(personalDetailsLocator);
	}

	//Page Methods/Actions
	public void logoutAction() {
		WebElement userDropDownBtn = driver.findElement(userDropDownLocator);
		userDropDownBtn.click();
		WebElement logoutBtn = driver.findElement(LogoutBtnLocator);
		logoutBtn.click();
	}

	public void clickMyInfoBtn(){
		WebElement myInfoBtn = driver.findElement(locatorofMyInfo);
		myInfoBtn.click();
	}

	public void clickPersonalDetailsBtn(){
		WebElement personalDetailsBtn = driver.findElement(personalDetailsLocator);
		personalDetailsBtn.click();
	}
	
	
}
