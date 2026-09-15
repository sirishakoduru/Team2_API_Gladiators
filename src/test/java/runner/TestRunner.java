package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(
	    features = "src/test/resources/features",
	    glue = {"stepDefinitions", "hooks"},
	    tags = "@validcase1 or @validcase2 or @noauthuser or @emptyfields or @duplicatefields or @invalidfields or @edgecases or @deleteUser",
	    //		+ or @PostProgram_02",
	    //tags="@noauthuser",
	    //tags="@emptyfields",
	    //tags="@duplicatefields",
	   // tags = "@invalidfields",
	    //tags="@edgecases",
	    //tags="@deleteUser",
	    plugin = {"pretty", "html:target/cucumber-report.html",
	              "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
	              "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
	             },
	    dryRun = false
	)
public class TestRunner extends AbstractTestNGCucumberTests{

}

