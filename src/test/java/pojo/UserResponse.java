package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {
	
	 @JsonProperty("userId")
	    private String userId;

	    @JsonProperty("userFirstName")
	    private String userFirstName;

	    @JsonProperty("userLastName")
	    private String userLastName;

	    @JsonProperty("userMiddleName")
	    private String userMiddleName;

	    @JsonProperty("userPhoneNumber")
	    private String userPhoneNumber;

	    @JsonProperty("userLocation")
	    private String userLocation;

	    @JsonProperty("userTimeZone")
	    private String userTimeZone;

	    @JsonProperty("userLinkedinUrl")
	    private String userLinkedinUrl;

	    @JsonProperty("userEduUg")
	    private String userEduUg;

	    @JsonProperty("userEduPg")
	    private String userEduPg;

	    @JsonProperty("userComments")
	    private String userComments;

	    @JsonProperty("userVisaStatus")
	    private String userVisaStatus;

	    @JsonProperty("userLoginEmail")
	    private String userLoginEmail;

	    public String getUserId() {
	        return userId;
	    }

	    public String getUserFirstName() {
	        return userFirstName;
	    }

	    public String getUserLastName() {
	        return userLastName;
	    }

	    public String getUserMiddleName() {
	        return userMiddleName;
	    }

	    public String getUserPhoneNumber() {
	        return userPhoneNumber;
	    }

	    public String getUserLocation() {
	        return userLocation;
	    }

	    public String getUserTimeZone() {
	        return userTimeZone;
	    }

	    public String getUserLinkedinUrl() {
	        return userLinkedinUrl;
	    }

	    public String getUserEduUg() {
	        return userEduUg;
	    }

	    public String getUserEduPg() {
	        return userEduPg;
	    }

	    public String getUserComments() {
	        return userComments;
	    }

	    public String getUserVisaStatus() {
	        return userVisaStatus;
	    }

	    public String getUserLoginEmail() {
	        return userLoginEmail;
	    }

}
