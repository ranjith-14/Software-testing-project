package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;

public class PersonalDetails {
    WebDriver driver;
    WebDriverWait wait;
    public Actions action;
    JavascriptExecutor js;
    Robot robo;
    public PersonalDetails(WebDriver webdriver) {
        driver = webdriver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        action = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    //By Locators
    By firstNameLocator = By.name("firstName");
    By firstNameErrorMsgLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div/div/div[2]/div[1]/span");
    
    By middleNameLocator = By.name("middleName");
    By middleNameErrorMsgLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div/div/div[2]/div[2]/span");
    
    By lastNameLocator = By.name("lastName");
    By lastNameErrorMsgLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div/div/div[2]/div[3]/span");
    By saveBtnLocator = By.xpath("//div[@class='oxd-form-actions']/button");
    By otherIdLocator = By.xpath("//div[@class='oxd-form-row']/div[1]/div[2]/div/div[2]/input");
    By otherIdErrorMsgLocator = By.xpath("//div[@class='oxd-form-row']/div[1]/div[2]/div/span");

    By licenceExpiryDateFieldLocator = By.xpath("//div[@class='oxd-date-wrapper']/div/input");
    By licenceExpiryDateErrorMsgLocator = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[2]/div[2]/div/span");
    By licenceDateIconLocator = By.xpath("//div[@class='oxd-date-wrapper']/div/i");
    By licenceDatePickerLocator = By.xpath("//div[@class='oxd-calendar-wrapper']");
    By todayBtnDatePicker = By.xpath("//div[@class='oxd-date-input-links']/div[1]");
    By clearBtnDatePicker = By.xpath("//div[@class='oxd-date-input-links']/div[2]");
    By closeBtnDatePicker = By.xpath("//div[@class='oxd-date-input-links']/div[3]");
//    By datePickerforDate = By.xpath("//div[@class='oxd-calendar-dates-grid']/div");
//    By datePickerforMonth = By.xpath("//ul[@class='oxd-calendar-selector']/li[1]/ul/li");
    By datePickerMonthBtn = By.xpath("//ul[@class='oxd-calendar-selector']/li");
//    By datePickerforYear = By.xpath("//ul[@class='oxd-calendar-selector']/li[2]/ul/li[text()='2000']");
    By datePickerYearBtn = By.xpath("//ul[@class='oxd-calendar-selector']/li[2]");

    By nationalityDropdownLocator = By.xpath("(//div[@class='oxd-select-wrapper'])[1]");
    By selectedNation = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[1]/div/div[2]/div/div/div[1]");
    By maritalStatusDropdownLocator = By.xpath("(//div[@class='oxd-select-wrapper'])[2]");
    By selectedMaritalStatus = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[2]/div/div[2]/div/div/div[1]");
    By genderMaleRadioLocator = By.xpath("(//div[@class='oxd-radio-wrapper']/label/span)[1]");
    By genderMaleRadioInputLocator = By.xpath("(//div[@class='oxd-radio-wrapper']/label/input)[1]");
    By genderFemaleRadioLocator = By.xpath("(//div[@class='oxd-radio-wrapper']/label/span)[2]");
    By genderFeMaleRadioInputLocator = By.xpath("(//div[@class='oxd-radio-wrapper']/label/input)[2]");
    By empIdLocator = By.xpath("(//div[@class='oxd-form-row']/div/div/div/div/input)[1]");
    By driverLicenceNumberLocator = By.xpath("(//div[@class='oxd-form-row']/div/div/div/div/input)[3]");
    By dateOfBirthLocator = By.xpath("(//div[@class='oxd-date-wrapper']/div/input)[2]");
    By toastMsgFullContainer = By.id("oxd-toaster_1");
    By loadingSpinnerLocator = By.className("oxd-loading-spinner-container");
    By helpBtn = By.xpath("//button[@class='oxd-icon-button']");
    
    By attachementAddBtnLocator = By.xpath("//div[@class='orangehrm-action-header']/button");
    By inputInvisibleElelementLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/form/div[1]/div/div/div/div[2]/input");
    By attachementBrowserBtnLocator = By.xpath("//div[@class='oxd-file-button']");
    By AttachementInputLocator = By.xpath("//div[@class='oxd-file-button']/following::div");
    By attachIcon = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/form/div[1]/div/div/div/div[2]/div/i");
    By attachementContainerLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/form/div[1]/div/div/div/div[2]/div");
    
    By attachementCommentFieldLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/form/div[2]/div/div/div/div[2]/textarea");
    By attachementCancelBtnLocator = By.xpath("(//div[@class='oxd-form-actions']/button)[2]");
    By AttachementSaveBtnLocator = By.xpath("(//div[@class='oxd-form-actions']/button)[3]");
    By lastAttachementInListName = By.xpath("(//div[@class='oxd-table-body']/div/div/div[2])[last()]");
    By getLastAttachementInListComment = By.xpath("(//div[@class='oxd-table-body']/div/div/div[3])[last()]");
    By getLastAttachementInListCheckBox = By.xpath("(//div[@class='oxd-table-body']/div/div/div[1])[last()]");
    By attachementErrorMsg = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/form/div[1]/div/div/div/span");


    // Page Elements
    
    public WebElement getfirstNamefield(){
        return driver.findElement(firstNameLocator);
    }
    public WebElement getfirstNameErrorMsg(){
        return driver.findElement(firstNameErrorMsgLocator);
    }

    public WebElement getmiddleNamefield(){
        return driver.findElement(middleNameLocator);
    }
    public WebElement getmiddleNameErrorMsg(){
        return driver.findElement(middleNameErrorMsgLocator);
    }

    public WebElement getlastNamefield(){
        return driver.findElement(lastNameLocator);
    }
    public WebElement getlastNameErrorMsg(){
        return driver.findElement(lastNameErrorMsgLocator);
    }


    public WebElement getOtherIDfield(){
        return driver.findElement(otherIdLocator);
    }
    public WebElement getlicenceExpiryDatefield(){
        return driver.findElement(licenceExpiryDateFieldLocator);
    }
    public WebElement getlicenceExpiryDatePicker(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(licenceDatePickerLocator));
    }
    
	public Boolean getlicenceExpiryDatePickerclosed() {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(licenceDatePickerLocator));
	}
	
    public WebElement getnationalityDropdown(){
        return driver.findElement(nationalityDropdownLocator);
    }
    public WebElement getMaritalStatusDropdown(){
        return driver.findElement(maritalStatusDropdownLocator);
    }
    public WebElement getMaleRadioBtn(){
        return driver.findElement(genderMaleRadioLocator);
    }
    public WebElement getFemaleRadioBtn() {
        return driver.findElement(genderFemaleRadioLocator);
    }
    public WebElement getEmpIdField(){
        return driver.findElement(empIdLocator);
    }
    public WebElement getDriverLicenceField(){
        return driver.findElement(driverLicenceNumberLocator);
    }
    public WebElement getDateOfBirthField(){
        return driver.findElement(dateOfBirthLocator);
    }
    public WebElement getSelectedNation(){
        return driver.findElement(selectedNation);
    }
    public WebElement getSelectedMaritalStatus(){
        return driver.findElement(selectedMaritalStatus);
    }

    public WebElement genderMaleBtn(){
        return driver.findElement(genderMaleRadioLocator);
    }
    public WebElement genderFeMaleBtn(){
        return driver.findElement(genderFemaleRadioLocator);
    }
	public WebElement genderMaleInputBtn() {
		return driver.findElement(genderMaleRadioInputLocator);
	}
	public WebElement genderFeMaleInputBtn() {
		return driver.findElement(genderFeMaleRadioInputLocator);
	}
    public WebElement getAttachementErrorMsg() {
		return driver.findElement(attachementErrorMsg);
	}
    public WebElement getSaveBtn() {
		return driver.findElement(saveBtnLocator);
	}



    //Page Actions

    public void waitFullToastContainer(){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(toastMsgFullContainer));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(toastMsgFullContainer));
        } catch (Exception e) {
            System.out.println("cannot wait foe toast container");
        }
    }

    public void clickDatePickerCloseBtn(){
        WebElement closeBtnLicenceDatePicker = driver.findElement(closeBtnDatePicker);
        closeBtnLicenceDatePicker.click();
    }

    public void clicklicenceExpiryDateField(){
        driver.findElement(licenceExpiryDateFieldLocator).click();
    }

    public void clickLicenceCalenderIcon(){
        driver.findElement(licenceDateIconLocator).click();
    }

    public void clickDatePickerTodayBtn(){
        driver.findElement(todayBtnDatePicker).click();
    }

    public void clickDatePickerClearBtn() {
        driver.findElement(clearBtnDatePicker).click();
    }

    public void waitFullSpinner(){
        try{
//            wait.until(ExpectedConditions.visibilityOfElementLocated(loadingSpinnerLocator));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinnerLocator));
        }catch(Exception e){
            System.out.println("Cannot wait for spinner");
        }
    }

    public void clearAndFillField(String whereToFill, String valueToFill){

        WebElement FieldToFill = null;

        switch (whereToFill){
            case "First name":
    	        FieldToFill = driver.findElement(firstNameLocator);
                break;
            case "Last name":
                FieldToFill = driver.findElement(lastNameLocator);
                break;
            case "Middle name":
                FieldToFill = driver.findElement(middleNameLocator);
                break;
            case "Other ID":
                FieldToFill = driver.findElement(otherIdLocator);
                break;
            case "License Expiry Date":
                FieldToFill = driver.findElement(licenceExpiryDateFieldLocator);
                break;
        }

        FieldToFill.click();
        js.executeScript("arguments[0].value = '';", FieldToFill);
        FieldToFill.sendKeys("a");
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.BACK_SPACE).perform();
        FieldToFill.sendKeys(valueToFill);
    }

    public String fieldVerify(String whereToVerify){
        WebElement fieldToVerify = null;
        switch (whereToVerify){
            case "First name":
                fieldToVerify = driver.findElement(firstNameLocator);
                break;
            case "Last name":
                fieldToVerify = driver.findElement(lastNameLocator);
                break;
            case "Middle name":
                fieldToVerify = driver.findElement(middleNameLocator);
                break;
            case "Other ID":
                fieldToVerify = driver.findElement(otherIdLocator);
                break;
            case "License Expiry Date":
                fieldToVerify = driver.findElement(licenceExpiryDateFieldLocator);
                break;
        }
        return fieldToVerify.getAttribute("value");
    }
        public String fieldErrorMsgVerify(String whereToVerify){
        WebElement fieldToVerify = null;
        switch (whereToVerify){
            case "First name":
                fieldToVerify = driver.findElement(firstNameErrorMsgLocator);
                break;
            case "Last name":
                fieldToVerify = driver.findElement(lastNameErrorMsgLocator);
                break;
            case "Middle name":
                fieldToVerify = driver.findElement(middleNameErrorMsgLocator);
                break;
            case "Other ID":
                fieldToVerify = driver.findElement(otherIdErrorMsgLocator);
                break;
            case "License Expiry Date":
                fieldToVerify = driver.findElement(licenceExpiryDateErrorMsgLocator);
                break;
        }

        return fieldToVerify.getText();
    }



    public void clickSaveBtn(){
        WebElement saveBtn = driver.findElement(saveBtnLocator);
        saveBtn.click();
    }

    public void pickDateinDatePicker(String dateAsString){
    	WebElement LicenceCalenderIcon = driver.findElement(licenceDateIconLocator);
    	js.executeScript("arguments[0].scrollIntoView(true);", getfirstNamefield());
    	LicenceCalenderIcon.click();
        Assert.assertTrue(getlicenceExpiryDatePicker().isDisplayed());

        WebElement yearBtn = driver.findElement(datePickerYearBtn);
        js.executeScript("arguments[0].scrollIntoView(true);", yearBtn);
        yearBtn.click();
        
        WebElement year = driver.findElement(By.xpath("//ul[@class='oxd-calendar-selector']/li[2]/ul/li[text()='"+dateAsString.split("-")[0]+"']"));
        js.executeScript("arguments[0].scrollIntoView(true);", year);
        year.click();

        WebElement monthBtn = driver.findElement(datePickerMonthBtn);
        monthBtn.click();
        WebElement month = driver.findElement(By.xpath("//ul[@class='oxd-calendar-selector']/li[1]/ul/li["+dateAsString.split("-")[1]+"]"));
        js.executeScript("arguments[0].scrollIntoView(true);", month);
        month.click();

        WebElement date = driver.findElement(By.xpath("//div[@class='oxd-calendar-dates-grid']/div["+dateAsString.split("-")[2]+"]"));
        date.click();

    }

    public void clickHelpBtn(){
        driver.findElement(helpBtn).click();
    }

    public void selectNation(String nation){
    	js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", getnationalityDropdown());
        getnationalityDropdown().click();
        WebElement nationSelector = driver.findElement(By.xpath("//div[@role='listbox']/div[./span[text()='" + nation + "']]"));
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", nationSelector);
        nationSelector.click();
        clickSaveBtn();
    }
    public void selectMaritalStatus(String maritalStatus){
    	js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", getMaritalStatusDropdown());
        getMaritalStatusDropdown().click();
        WebElement maritalStatusSelector = driver.findElement(By.xpath("//div[@role='listbox']/div[./span[text()='" + maritalStatus + "']]"));
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", maritalStatusSelector);
        maritalStatusSelector.click();
        clickSaveBtn();
    }

    public void attachementUploader(String fileName,String commentVerification,String comment){
        WebElement addBtn = driver.findElement(attachementAddBtnLocator);
//        WebElement cancelBtn = driver.findElement(attachementCancelBtnLocator);

        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", addBtn);
        addBtn.click();
        
        WebElement browserBtn = driver.findElement(attachementBrowserBtnLocator);
        WebElement attachIconn = driver.findElement(attachIcon);
        WebElement inputBtn = driver.findElement(AttachementInputLocator);
        WebElement inputInvisibleElelement = driver.findElement(inputInvisibleElelementLocator);
        WebElement commmentBox = driver.findElement(attachementCommentFieldLocator);
        WebElement saveBtn = driver.findElement(AttachementSaveBtnLocator);
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", inputBtn);
//        inputBtn.click();
        
//        try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//		}

        String relativePath = "./src/test/resources/test_files/"+fileName;
        File file = new File(relativePath);
        String absolutePath = file.getAbsolutePath();

//        StringSelection str = new StringSelection(absolutePath);
//        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
//
//        try {
//			robo = new Robot();
//		} catch (AWTException e) {
//		}
//
//        robo.keyPress(KeyEvent.VK_CONTROL);
//        robo.keyPress(KeyEvent.VK_V);
//        robo.keyRelease(KeyEvent.VK_V);
//        robo.keyRelease(KeyEvent.VK_CONTROL);
//        try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//
//		}
//        robo.keyPress(KeyEvent.VK_ENTER);
//        robo.keyRelease(KeyEvent.VK_ENTER);
//        try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//
//		}
        inputInvisibleElelement.sendKeys(absolutePath);

        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", commmentBox);
        commmentBox.sendKeys(comment);

        saveBtn.click();
    }

    public String[] attachementVerifier() {
        waitFullSpinner();
        WebElement lastAttachementName = driver.findElement(lastAttachementInListName);
        WebElement lastAttachementComment = driver.findElement(getLastAttachementInListComment);
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", lastAttachementName);
        String[] nameAndComment = {lastAttachementName.getText(),lastAttachementComment.getText()};
        return nameAndComment;
    }
}
