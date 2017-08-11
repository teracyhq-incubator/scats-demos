package runner;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
features={"src/test/resources/features"},
glue={"scenariosteps","org.teracy.scats.core.steps"},
//tags = {"@test"},
plugin={"junit:target/testrunner/cucumber.xml"
		,"html:target/testrunner"
		,"json:target/testrunner/cucumber.json"}
)
public class TestRunner {
}
