package PageObjects;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

import Utilities.BuildJsonPayLoad.BaseClassAPI;
import Utilities.BuildJsonPayLoad.BuildJsonPayload;
import Utilities.BuildJsonPayLoad.Location;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestAssuredPageObjects extends BaseClassAPI{
    private RequestSpecification req;
    private ResponseSpecification res;
    private String resp;

    public RestAssuredPageObjects() {}

    public void addPlacePayload() {
        System.out.println("addPlacePayload() called");
        req=given().spec(returnRequestforAPI()).log().all();
    }

    public void postAPI() {
        System.out.println("postAPI() called");
        if (returnResponseforAPI() == null) {
            throw new IllegalStateException(
                "RequestSpecification is null. " +
                "Ensure addPlacePayload() step runs before postAPI()."
            );
        }
		
		String response = req.when().post("/maps/api/place/add/json").
						then().spec(returnResponseforAPI())
						.extract().response().asString();
		
		resp = response;
    }

    public void statusCodeValidation(String keyVal, String expectedStatus) {
        System.out.println("statusCodeValidation() called");

        if (resp == null) {
            throw new IllegalStateException(
                "Response string is null. " +
                "Ensure postAPI() step runs before statusCodeValidation()."
            );
        }
        System.out.println(resp);
		JsonPath js=new JsonPath(resp);
		String place_id=js.getString("place_id");
		System.out.println(place_id);
        assertEquals(js.getString(keyVal), expectedStatus);
    }
}
