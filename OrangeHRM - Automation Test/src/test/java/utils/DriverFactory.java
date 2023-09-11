package utils;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DriverFactory {
	
static ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();
Logger LOGGER = LogManager.getLogger(getClass());

public WebDriver init_driver(String browser) {
	
	if(browser.equalsIgnoreCase("chrome")) {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setAcceptInsecureCerts(true);
		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--window-size=1920,1080");
		
//		tldriver.set(new ChromeDriver());
		tldriver.set(new ChromeDriver(chromeOptions));
		
		LOGGER.info("Launching Chrome Browser");
		
	}else if(browser.equalsIgnoreCase("edge")) {
		EdgeOptions edgeOptions = new EdgeOptions();
		edgeOptions.setAcceptInsecureCerts(true);
		
//		tldriver.set(new EdgeDriver());
		tldriver.set(new EdgeDriver());
		LOGGER.info("Launching Edge Browser");
		
	}else if(browser.equalsIgnoreCase("firefox")) {
		
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setAcceptInsecureCerts(true);
		
//		tldriver.set(new FirefoxDriver());
		tldriver.set(new FirefoxDriver(firefoxOptions));
		
		LOGGER.info("Launching Firefox Browser");
		
	}else {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setAcceptInsecureCerts(true);
		
//		tldriver.set(new ChromeDriver());
		tldriver.set(new ChromeDriver(chromeOptions));
		
		LOGGER.info("Launching Chrome Browser as default");
		
	}
	
	getDriver().manage().window().maximize();
	getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	return getDriver();
	}

public static synchronized WebDriver getDriver() {
	return tldriver.get();
}

}
