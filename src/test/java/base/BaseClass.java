package base;

import java.io.IOException;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import pojo.TestcaseWrapper;
import utilities.ConfigReader;
import utilities.JsonReader;
import utilities.TokenManager;

public class BaseClass {
	
	public static void init() throws IOException {
		RestAssured.baseURI = ConfigReader.getProperty("BaseURL");
	}
	
	public static RequestSpecification requestWithoutAuth() {
        return given()
                .log().all()
                .header("Content-Type", "application/json")
                .baseUri(RestAssured.baseURI);
    }
	
	public static RequestSpecification createRequest() {
        return given()
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .baseUri(RestAssured.baseURI);
    }
	public static TestcaseWrapper getTestData() {
		
		return  JsonReader.readAllModules("src/test/resources/TestDataforLMS.json");
		 
		}
	

}
