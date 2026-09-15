package pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class JsonTestData {

	@JsonProperty("testcaseName")
	public String testcaseName;

	@JsonProperty("endpoint")
	public String endpoint;

	@JsonProperty("statusmessage")
	public String statusmessage;

	@JsonProperty("contentType")
	public String contentType;

	@JsonProperty("loginRequest")
	public LoginRequest loginRequest;

	@JsonProperty("batchRequest")
	public BatchRequest batchRequest;

	@JsonProperty("forgotPasswordRequest")
	public ForgotPasswordRequest forgotPasswordRequest;

	@JsonProperty("method")
	public String method;

	@JsonProperty("resetPasswordRequest")
	public ResetPasswordRequest resetPasswordRequest;

	@JsonProperty("programRequest")
	private ProgramRequest programRequest;

    @JsonProperty("authType")
    public String authType;

    @JsonProperty("expectedType")
    private String expectedType;

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

    @JsonProperty("programDescription")
    private String programDescription;

    @JsonProperty("programName")
    private String programName;

    @JsonProperty("programStatus")
    private String programStatus;

    @JsonProperty("expectedMessage")
    private String expectedMessage;

    @JsonProperty("BooleanMessage")
    private boolean BooleanMessage;

    @JsonProperty("userRequest")
    private UserRequest userRequest;

    @JsonProperty("expectedMessages")
    private java.util.List<String> expectedMessages;
    @JsonProperty("userId")
    private String userId;

    @JsonProperty("userRoleList")
    private List<UserRoleRequest> userRoleList;

    @JsonProperty("loginStatus")
    private String loginStatus;

    @JsonProperty("status")
    private String status;

    @JsonProperty("roleId")
    private String roleId;

    @JsonProperty("batchId")
    private String batchId;

    @JsonProperty("programBatchRequest")
    private UserRoleProgramBatchStatusRequest programBatchRequest;


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

    public String getExpectedType() {
        return expectedType;
    }

	public String getExpectedEmail() {
		return expectedEmail;
	}

	public String getExpectedStatus() {
		return expectedStatus;
	}

	public ProgramRequest getProgramRequest() {
		return programRequest;
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

	public LoginRequest getLoginRequest() {
        return loginRequest;
	}
	public void setLoginRequest(LoginRequest loginRequest) {
		this.loginRequest = loginRequest;
	}

	public BatchRequest getBatchRequest() {
		return batchRequest;
	}

	public void setbodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public Integer getProgramId() {
		return programId;
	}

	public void setProgramId(Integer programId) {
		this.programId = programId;
	}

	public String getProgramDescription() {
		return programDescription;
	}

	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramStatus() {
		return programStatus;
	}

	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}

	public String getExpectedMessage() {
		return expectedMessage;
	}

	public boolean getBooleanMessage() {
		return BooleanMessage;
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

	public UserRequest getUserRequest() {
		return userRequest;
	}

	public void setUserRequest(UserRequest userRequest) {
		this.userRequest = userRequest;
	}

	public ForgotPasswordRequest getForgotPasswordRequest() {
		return forgotPasswordRequest;
	}

	public void setForgotPasswordRequest(ForgotPasswordRequest forgotPasswordRequest) {
		this.forgotPasswordRequest = forgotPasswordRequest;
	}

	public ResetPasswordRequest getResetPasswordRequest() {
		return resetPasswordRequest;
	}

	public void setResetPasswordRequest(ResetPasswordRequest resetPasswordRequest) {
		this.resetPasswordRequest = resetPasswordRequest;
	}

    public java.util.List<String> getExpectedMessages() {
        return expectedMessages;
    }
    public void setExpectedMessages(java.util.List<String> expectedMessages) {
        this.expectedMessages = expectedMessages;
    }
    public String getuserId() {
        return userId;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }
    public List<UserRoleRequest> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRoleRequest> userRoleList) {
        this.userRoleList = userRoleList;
    }
    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getBatchId() {
        return batchId;
    }

    public void setbatchId(String batchId) {
        this.batchId = batchId;
    }
    public UserRoleProgramBatchStatusRequest getProgramBatchRequest() {
        return programBatchRequest;
    }

    public void setProgramBatchRequest(UserRoleProgramBatchStatusRequest programBatchRequest) {
        this.programBatchRequest = programBatchRequest;
    }
  
    //========For Batch=======
    
    @JsonProperty("batchRequest")
    private BatchRequest batchRequest;

    public BatchRequest getBatchRequest() {
        return batchRequest;
    }

    public void setBatchRequest(BatchRequest batchRequest) {
        this.batchRequest = batchRequest;
    public java.util.List<String> getExpectedMessages() {
        return expectedMessages;
    }
    public void setExpectedMessages(java.util.List<String> expectedMessages) {
        this.expectedMessages = expectedMessages;
    public String getuserId() {
        return userId;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }
    public List<UserRoleRequest> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRoleRequest> userRoleList) {
        this.userRoleList = userRoleList;
    }
    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getBatchId() {
        return batchId;
    }

    public void setbatchId(String batchId) {
        this.batchId = batchId;
    }
    public UserRoleProgramBatchStatusRequest getProgramBatchRequest() {
        return programBatchRequest;
    }

    public void setProgramBatchRequest(UserRoleProgramBatchStatusRequest programBatchRequest) {
        this.programBatchRequest = programBatchRequest;
    }
}

