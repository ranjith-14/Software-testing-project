package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class ConfigReader {
	Logger LOGGER = LogManager.getLogger(getClass());
	private Properties props = null;
	
	
private Properties init_prop() {
	
	Properties props = new Properties();
	try {
		FileInputStream fileIn = new FileInputStream("./src/test/resources/config.properties");
		props.load(fileIn);
	} catch (FileNotFoundException e) {
		LOGGER.error("Config Reader File Input error");
	} catch (IOException e) {
		LOGGER.error("Config Reader Properties read error");
	}
	LOGGER.info("Loading Properties File");
	return props;
	
}

public Properties getProperties() {
	if(props == null) {
		props = init_prop();
	}
	
	return props;
}

}
