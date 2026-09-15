package pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRoleUpdate {
	
	 @JsonProperty("userRoleList")
	    private List<UserRoleRequest> userRoleList;

	    public UserRoleUpdate() {}

	    public UserRoleUpdate(List<UserRoleRequest> userRoleList) {
	        this.userRoleList = userRoleList;
	    }

	    public List<UserRoleRequest> getUserRoleList() {
	        return userRoleList;
	    }

	    public void setUserRoleList(List<UserRoleRequest> userRoleList) {
	        this.userRoleList = userRoleList;
	    }

}
