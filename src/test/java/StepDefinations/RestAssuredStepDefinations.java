package StepDefinations;

import PageObjects.RestAssuredPageObjects;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RestAssuredStepDefinations {
	RestAssuredPageObjects restAssuredPageObjects;

	public RestAssuredStepDefinations(RestAssuredPageObjects restAssuredPageObjects) {
		this.restAssuredPageObjects = restAssuredPageObjects;
	}
	@Given("User calls {string} payload")
	public void callPayload(String payloadAction) {
		System.out.println("payload is called");
		
		switch(payloadAction)
		{
		case "Add Place":
			restAssuredPageObjects.addPlacePayload();
			break;
		case "Delete Place":
			break;
			
		}
	}
	@When("user calls the {string}")
	public void callAPI(String APIName) {
		switch(APIName)
		{
		case "PostAPI":
			restAssuredPageObjects.postAPI();
			break;
		case "GetAPI":
			break;
			
		}
	}
    @Then("{string} must be added with response code for {string}")
	public void validateResponseCode(String keyVal,String statusCode) {
    	
    	switch(statusCode)
		{
		case "200":
			restAssuredPageObjects.statusCodeValidation(keyVal,statusCode);
			break;
		case "300":
			break;
		case "400":
			break;
		case "500":
			break;
			
		}

	}
}
