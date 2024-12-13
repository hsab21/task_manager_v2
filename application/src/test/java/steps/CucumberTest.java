package steps;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/steps/features",
        glue = "steps",
        plugin = {"pretty", "json:target/cucumber.json"}
)
public class CucumberTest {

}
