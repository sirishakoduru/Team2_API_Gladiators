package pojo;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class UserRequest {
	
    @JsonProperty("userComments")
    private String userComments;
    
    @JsonProperty("userEduPg")
    private String userEduPg;
    
    @JsonProperty("userEduUg")
    private String userEduUg;
    
    @JsonProperty("userFirstName")
    private String userFirstName;
    
    @JsonProperty("userLastName")
    private String userLastName;
    
    @JsonProperty("userLinkedinUrl")
    private String userLinkedinUrl;
    
    @JsonProperty("userLocation")
    private String userLocation;
    
    @JsonProperty("userMiddleName")
    private String userMiddleName;
    
    @JsonProperty("userPhoneNumber")
    private String userPhoneNumber;
    
    @JsonProperty("userRoleMaps")
    private List<UserRole> userRoleMaps;
    
    @JsonProperty("userTimeZone")
    private String userTimeZone;
    
    @JsonProperty("userVisaStatus")
    private String userVisaStatus;
    
    @JsonProperty("userLogin")
    private UserLogin userLogin;
    
    @JsonProperty("userId")
    private String userId;
    
    @JsonProperty("userLoginEmail")
    private String userLoginEmail;
    
    @JsonProperty("loginStatus")
    private String loginStatus;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("programId")
    private int programId;
    
    @JsonProperty("roleId")
    private String roleId;
    
    @JsonProperty("batchId")
    private int batchId;
    
    @JsonProperty("userRoleProgramBatchStatus")
    private String userRoleProgramBatchStatus;
    
    // Getters and Setters
    public String getUserComments()
    { 
    	return userComments; 
    }
    public void setUserComments (String userComments)
    { 
    	this.userComments = userComments;
    }
    public String getUserEduPg() 
    { 
    	return userEduPg;
    }
    public void setUserEduPg(String userEduPg)
    {
    	this.userEduPg = userEduPg;
    }
    public String getUserEduUg() 
    { 
    	return userEduUg; 
    }
    public void setUserEduUg(String userEduUg)
    { 
    	this.userEduUg = userEduUg; 
    }
    public String getUserFirstName() 
    { 
    	return userFirstName; 
    }
    public void setUserFirstName(String userFirstName)
    { 
    	this.userFirstName = userFirstName; 
    }
    public String getUserLastName()
    {
    	return userLastName;
    }
    public void setUserLastName(String userLastName) 
    { 
    	this.userLastName = userLastName;
    }
    public String getUserLinkedinUrl() 
    {
    	return userLinkedinUrl;
    }
    public void setUserLinkedinUrl(String userLinkedinUrl) 
    { 
    	this.userLinkedinUrl = userLinkedinUrl;
    }
    public String getUserLocation()
    { 
    return userLocation; 
    }
    public void setUserLocation(String userLocation)
    { 
    	this.userLocation = userLocation; 
    }
    public String getUserMiddleName()
    { 
    	return userMiddleName;
    }
    public void setUserMiddleName(String userMiddleName)
    {
    this.userMiddleName = userMiddleName;
    }
    public String getUserPhoneNumber() 
    { 
    	return userPhoneNumber; 
    }
    public void setUserPhoneNumber(String userPhoneNumber) 
    { 
    	this.userPhoneNumber = userPhoneNumber;
    }
 
    public List<UserRole> getUserRoleMaps() {
        return userRoleMaps;
    }
    
    public void setUserRoleMaps(List<UserRole> userRoleMaps) {
        this.userRoleMaps = userRoleMaps;
    }
    
    public String getUserTimeZone() 
    {
    	return userTimeZone; 
    }
    public void setUserTimeZone(String userTimeZone) 
    { 
    	this.userTimeZone = userTimeZone;
    }
    public String getUserVisaStatus() 
    { 
    	return userVisaStatus;
    }
    public void setUserVisaStatus(String userVisaStatus) 
    { 
    	this.userVisaStatus = userVisaStatus;
    }
    
	public UserLogin getUserLogin() 
	{ 
		return userLogin; 
	}
    
    public void setUserLogin(UserLogin userLogin)
    { 
    	this.userLogin = userLogin; 
    }
    public String getuserId() {
        return userId;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }
    public String getuserLoginEmail() {
        return userLoginEmail;
    }

    public void setuserLoginEmail(String userLoginEmail) {
        this.userLoginEmail = userLoginEmail;
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
    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }
    public String getUserRoleProgramBatchStatus() {
        return userRoleProgramBatchStatus;
    }

    public void setUserRoleProgramBatchStatus(String userRoleProgramBatchStatus) {
        this.userRoleProgramBatchStatus = userRoleProgramBatchStatus;
    }
}