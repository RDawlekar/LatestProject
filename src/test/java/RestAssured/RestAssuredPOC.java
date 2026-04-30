package RestAssured;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import RestAssured.PayLoad;

public class RestAssuredPOC {
	
    @Test
	    public void getPlaceTest() {
	        RestAssured.baseURI = "https://rahulshettyacademy.com";
String response=given().log().all()
	            .queryParam("key", "qaclick123")
	            .header("Content-Type", "application/json")
	            .body(PayLoad.getPayLoad())
	        .when()
	            .post("/maps/api/place/add/json")
	        .then().log().all()
	            .assertThat()
	            .statusCode(200).body("scope", equalTo("APP")).header("Content-Type", "application/json;charset=UTF-8").extract().response().asString();	     
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
	     
	     given().log().all()
         .queryParam("key", "qaclick123")
         .queryParam("place_id", place_id)
         .header("Content-Type", "application/json")
     .when()
         .get("/maps/api/place/get/json")
     .then().log().all()
         .assertThat()
         .statusCode(200)
         .body("address", equalTo("101 Mountain View, USA"));
	     
	}
}
