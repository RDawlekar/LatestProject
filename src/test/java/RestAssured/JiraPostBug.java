package RestAssured;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class JiraPostBug {
public static void jiraPostDefectwithattachment() {
	RestAssured.baseURI="https://reshmadawlekar41.atlassian.net/";
	
	String res= given().log().all().auth().preemptive().
			basic("user id","API Token")
			.header("Content-Type","application/json")
	    	.body(PayLoad.jiraPayloadBug())
	.post("rest/api/3/issue")
	.then().log().all().assertThat().statusCode(201).contentType("application/json").extract().response().asString();  
	System.out.println(res);
	String issueId=new JsonPath(res).getString("id");
	
	given().log().all().pathParam("key",issueId)
	.auth().preemptive().
	basic("user id","API Token")
	.header("X-Atlassian-Token", "no-check")
	.header("Content-Type","multipart/form-data")
	.multiPart("file",new java.io.File("C:\\Users\\rkuma\\Downloads\\reshma.txt"))
	.post("rest/api/3/issue/{key}/attachments")
	.then().log().all().assertThat().statusCode(200);
    	
    }

}
