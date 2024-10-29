package HouseIt;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty",
        "html:build/reports/tests/cucumber/cucumber-report.html"},
    features = {"classpath:features"},
    glue = {"HouseIt"}
)
public class RunCucumberTests {
    
}
