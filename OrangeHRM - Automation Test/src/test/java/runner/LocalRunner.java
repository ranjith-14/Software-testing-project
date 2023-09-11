package runner;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features={"src/test/resources/Features/"},
		glue={"stepdefinitions","hooks"},
		dryRun=false,
		monochrome = true,
		plugin = { "pretty", "json:target/cucumber-report/results.json", "html:target/cucumber-report/cucumber.html",
		"junit:target/cucumber-report/cucumber.xml",
		"rerun:target/failed_scenarios.txt","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })
public class LocalRunner extends AbstractTestNGCucumberTests{
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios(){
        return super.scenarios();
    }
}
