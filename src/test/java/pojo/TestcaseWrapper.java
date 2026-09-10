package pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestcaseWrapper {
	
	@JsonProperty("GetRequest")
    private List<JsonTestData> getRequest;
	
	public List<JsonTestData> getGetRequest() {
        return getRequest;
    }

    public void setGetRequest(List<JsonTestData> getRequest) {
        this.getRequest = getRequest;
    }

}
