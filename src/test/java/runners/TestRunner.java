package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * This is the TestRunner class for running Cucumber tests.
 * It uses JUnit to execute the Cucumber tests.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty"}, dryRun = true,
        glue = "definitions"
)
public class TestRunner {
}