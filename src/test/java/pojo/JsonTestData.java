package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.restassured.RestAssured;

//@JsonIgnoreProperties(ignoreUnknown = true)

public class JsonTestData {

    @JsonProperty("testcaseName")
    public String testcaseName;

    @JsonProperty("endpoint")
    public String endpoint;

    @JsonProperty("Statusmessage") 
    public String statusmessage;

    @JsonProperty("contentType")
    public String contentType;
    
    @JsonProperty("loginRequest")
    public LoginRequest loginRequest;
//    
//    @JsonProperty("forgotPasswordRequest")
//    public ForgotPasswordRequest forgotPasswordRequest;
    
    @JsonProperty("method")
    public String  method;
    
//    @JsonProperty("resetPasswordRequest")
//    public ResetPasswordRequest resetPasswordRequest;
    
    @JsonProperty("authType")
    public String authType;
    
    
    
    @JsonProperty("expectedEmail")
    private String expectedEmail;
    
    @JsonProperty("expectedStatus")
    private String expectedStatus;
    
    @JsonProperty("rawBody")
    private String rawBody;
    
    @JsonProperty("bodyType")
    private String bodyType;
    
    @JsonProperty("expectedStatusCode")
    private Integer expectedStatusCode;
    
    @JsonProperty("programId")
    private Integer programId;

    @JsonProperty("expectedMessage")
    private String expectedMessage;
    
    @JsonProperty("BooleanMessage")
    private boolean BooleanMessage;

  
    public String getTestcaseName() {
        return testcaseName;
    }

    public void setTestcaseName(String testcaseName) {
        this.testcaseName = testcaseName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
    

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatusmessage() {
        return statusmessage;
    }

    public void setStatusmessage(String statusmessage) {
        this.statusmessage = statusmessage;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getExpectedEmail() {
        return expectedEmail;
    }

    public String getExpectedStatus() {
        return expectedStatus;
    }
    
    public String getRawBody() {
        return rawBody;
    }

    public void setRawBody(String rawBody) {
        this.rawBody = rawBody;
    }
    
    public String getbodyType() {
        return bodyType;
    }

    public void setbodyType(String bodyType) {
        this.bodyType = bodyType;
    }
    public Integer getProgramId() {
        return programId;
    }

    public String getExpectedMessage() {
        return expectedMessage;
    }
    public boolean getBooleanMessage() {
        return BooleanMessage;
    }

	public RestAssured getRequestBody() {
		// TODO Auto-generated method stub
		return null;
	}
    
    public Integer getexpectedStatusCode() {
        return expectedStatusCode;
    }
    
    public void setexpectedStatusCode(int expectedStatusCode) {
        this.expectedStatusCode = expectedStatusCode;
    }
    

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }
}
