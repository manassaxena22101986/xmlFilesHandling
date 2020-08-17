package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "features",
		glue = {"stepDefinition"},
		plugin = {"html:target/htmlReport/","json:target/jsonReports/cucumber-report.json"},
		dryRun = false
		)

public class TestRunner {

}
