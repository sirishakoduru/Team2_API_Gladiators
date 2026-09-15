package pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestcaseWrapper {

    @JsonProperty("GetRequest")
    private List<JsonTestData> getRequest;
 
    @JsonProperty("PostRequest")
    private List<JsonTestData> postRequest;
    
    @JsonProperty("DeleteRequest")
    private List<JsonTestData> deleteRequest;

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
    
    public List<JsonTestData> getDeleteRequest() {
        return deleteRequest;
    }
    public void setDeleteRequest(List<JsonTestData> deleteRequest) {
        this.deleteRequest = deleteRequest;
    }
}
