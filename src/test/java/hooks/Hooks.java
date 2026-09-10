package hooks;

import java.io.IOException;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.JsonTestData;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.TestcaseWrapper;
import base.BaseClass;
import utilities.ConfigReader;
import utilities.JsonReader;
import utilities.TokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Hooks extends BaseClass {

    RequestSpecification request;
    Response response;

    private static final Logger log = LoggerFactory.getLogger(Hooks.class);
    
    public static Scenario scenario;

    @Before(order = 0)
    public void captureScenario(Scenario scenario) {
        Hooks.scenario = scenario;
    }
    
    @Before(value = "@authToken", order = 1)
    public void generateTokenBeforeScenario() throws IOException {

    	BaseClass.init(); 
        if (TokenManager.getToken() == null || TokenManager.getToken().isEmpty()) {

        	 LoginRequest login = new LoginRequest();
        	 login.setUserLoginEmailId(ConfigReader.getProperty("userLoginEmailId"));
     		 login.setPassword(ConfigReader.getProperty("password"));

     		 request = requestWithoutAuth();

             response = request
                     .body(login)
                     .when()
                     .post(ConfigReader.getProperty("LoginEndpoint"));
             LoginResponse loginResponse = response.as(LoginResponse.class);

             TokenManager.setToken(loginResponse.getToken());

             log.info("Token generated successfully: {}", TokenManager.getToken());
        }
    }
}