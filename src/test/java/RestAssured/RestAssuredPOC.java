package RestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import RestAssured.PayLoad;
import Utilities.BuildJsonPayLoad.BuildJsonPayload;
import Utilities.BuildJsonPayLoad.Location;

public class RestAssuredPOC {

	@Test
	public void getPlaceTest() {
		
		BuildJsonPayload p=new BuildJsonPayload();
		
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setName("Rahul Shetty Academy");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://rahulshettyacademy.com");
		List<String> typesval=new ArrayList<String>();
		typesval.add("shoe park");
		typesval.add("shop");
		p.setTypes(typesval);		
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key","qaclick123")
				.addHeader("Content-Type","application/json")
				.setBody(p).build();
		ResponseSpecification res= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response=given().spec(req).log().all()
				.when() .post("/maps/api/place/add/json")
				.then().spec(res).extract().response().asString();	     
		System.out.println(response);
		JsonPath js=new JsonPath(response);
		String place_id=js.getString("place_id");
		System.out.println(place_id);

		given().log().all()
		.queryParam("key", "qaclick123")
		.header("Content-Type", "application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+place_id+"\",\r\n" + 
				"\"address\":\"101 Mountain View, USA\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}")
		.when()
		.put("/maps/api/place/update/json")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.body("msg", equalTo("Address successfully updated"));



		//get place request 
		given().spec(req)
		.queryParam("place_id", place_id)
		.header("Content-Type", "application/json")
		.when()
		.get("/maps/api/place/get/json")
		.then().spec(res)
		.body("address", equalTo("101 Mountain View, USA"));

	}
}

