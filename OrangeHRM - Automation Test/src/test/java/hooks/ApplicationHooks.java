package hooks;


import java.util.Properties;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;
import utils.DriverFactory;

public class ApplicationHooks {
	Properties props;
	WebDriver driver;
	Logger LOGGER = LogManager.getLogger(getClass());
	
@Before(order = 0)
public void readConfig() {
	LOGGER.info("Execution Started");
	ConfigReader configs = new ConfigReader();
	props = configs.getProperties(); 
	LOGGER.info("Reading Configs");
}

@Before(order = 1)
public void LaunchBrowser() {
	DriverFactory driverFactory = new DriverFactory();
	driver = driverFactory.init_driver(props.getProperty("browser"));
	driver.get(props.getProperty("url"));
	LOGGER.info("Launching the Browser");
}

@AfterStep
public void saveScreenshot(Scenario scenario){
	if(scenario.isFailed()) {
		String screenshotName = scenario.getName().replaceAll(" ", "_");
		byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", screenshotName);
	}
}


@After(order = 1)
public void tearDown() {
	if(driver != null) {
	driver.quit();
	}
	LOGGER.info("Closing the Browser");
}

}
