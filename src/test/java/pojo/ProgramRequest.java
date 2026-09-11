package pojo;

public class ProgramRequest {
	
	
	private String programName;
	private String programDesciption;
	private String programStatus;
	
	
	public String getprogramName() {
		return programName;
	}
	
	public String getprogramDesciption() {
		return programDesciption;
	}
	public String getprogramStatus() {
		return programStatus;
	}
	
	public void setProgramDescription(String programDescription) {
		this.programDesciption = programDescription;
	}
	
	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}
	
}
