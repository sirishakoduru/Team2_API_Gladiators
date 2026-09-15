package pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestcaseWrapper {

    @JsonProperty("GetRequest")
    private List<JsonTestData> getRequest;
 
    @JsonProperty("PostRequest")
    private List<JsonTestData> postRequest;
    
    @JsonProperty("PutRequest")
    private List<JsonTestData> putRequest;
    
    @JsonProperty("UpdateRoleIDRequest")
    private List<JsonTestData> updateRoleIDRequest;
    
    @JsonProperty("programBatchRequest")
    private List<JsonTestData> programBatchRequest;

    public List<JsonTestData> getGetRequest() {
        return getRequest;
    }

    public void setGetRequest(List<JsonTestData> getRequest) {
        this.getRequest = getRequest;
    }
    
    public List<JsonTestData> getPostRequest() {
        return postRequest;
    }
    public void setPostRequest(List<JsonTestData> postRequest) {
        this.postRequest = postRequest;
    }
    public List<JsonTestData> getPutRequest() {
        return putRequest;
    }
    public void setPutRequest(List<JsonTestData> putRequest) {
        this.putRequest = putRequest;
    }
    public List<JsonTestData> getUpdateRoleIDRequest() {
        return updateRoleIDRequest;
    }
    public void setUpdateRoleIDRequest(List<JsonTestData> UpdateRoleIDRequest) {
        this.updateRoleIDRequest = UpdateRoleIDRequest;
    }

	public List<JsonTestData> getProgramBatchRequest() {
		return programBatchRequest;
	}
	public void setProgramBatchRequest(List<JsonTestData> programBatchRequest) {
        this.programBatchRequest = programBatchRequest;
    }
    
}
