package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLogin {
	
	    @JsonProperty("userLoginEmail")
	    private String userLoginEmail;
	    
	    @JsonProperty("loginStatus")
	    private String loginStatus;
	    
	    @JsonProperty("status")
	    private String status;
	    
	    public UserLogin() {}
	    
	    public UserLogin(String userLoginEmail, String loginStatus) 
	    {
	        this.userLoginEmail = userLoginEmail;
	        this.loginStatus = loginStatus;
	    }
	    
	    public String getUserLoginEmail() 
	    {
	        return userLoginEmail;
	    }
	    
	    public void setUserLoginEmail(String userLoginEmail)
	    {
	        this.userLoginEmail = userLoginEmail;
	    }
	    
	    public String getLoginStatus() 
	    {
	        return loginStatus;
	    }
	    
	    public void setLoginStatus(String loginStatus) 
	    {
	        this.loginStatus = loginStatus;
	    }
	    
	    public String getStatus() {
	        return status;
	    }
	    
	    public void setStatus(String status) {
	        this.status = status;
	    }
	}
