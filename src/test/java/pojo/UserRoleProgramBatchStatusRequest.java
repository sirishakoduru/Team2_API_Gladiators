package pojo;

import java.util.List;

public class UserRoleProgramBatchStatusRequest {
	
	private int programId;
    private String roleId;
    private List<UserRoleProgramBatch> userRoleProgramBatches;

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
	public List<UserRoleProgramBatch> getUserRoleProgramBatches() {
        return userRoleProgramBatches;
    }

    public void setUserRoleProgramBatches(List<UserRoleProgramBatch> userRoleProgramBatches) {
        this.userRoleProgramBatches = userRoleProgramBatches;
    }
}
