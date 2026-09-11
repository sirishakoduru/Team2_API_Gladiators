package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRole {		@JsonProperty("roleId")	private String roleId;		@JsonProperty("userRoleStatus")	private String userRoleStatus;
	
	  public UserRole() {}
	    public UserRole(String roleId, String userRoleStatus) {
	        this.roleId = roleId;
	        this.userRoleStatus = userRoleStatus;
	    }	public String getRoleId() {		return roleId ;	}	public void setRoleId(String roleId) {		this.roleId = roleId;	}	public String getUserRoleStatus() {		return userRoleStatus;	}	public void setUserRoleStatus(String userRoleStatus) {		this.userRoleStatus = userRoleStatus;	}		}