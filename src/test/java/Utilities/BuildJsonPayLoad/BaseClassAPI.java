package Utilities.BuildJsonPayLoad;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import WebDriverManager.GetConfigData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseClassAPI {

	BuildJsonPayload returnPayLoadData=returnPayLoadData();

	public RequestSpecification returnRequestforAPI() {
		RequestSpecification req=null;
		try {
		PrintStream file=new PrintStream(new FileOutputStream("logging.txt"));
				req=new RequestSpecBuilder().
				setBaseUri(GetConfigData.getBaseURI()).
				addQueryParam("key","qaclick123").
				addHeader("Content-Type","application/json").
				setBody(returnPayLoadData).
				addFilter(RequestLoggingFilter.logRequestTo(file)).
				addFilter(ResponseLoggingFilter.logResponseTo(file)).
				setContentType(ContentType.JSON).
				build();
		}
		catch (Exception e) {
			System.out.println("Exception while creating request specification " + e.getMessage());
		}

		return  req;	
	}

	public ResponseSpecification returnResponseforAPI() {
		ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return res;
	}

	public BuildJsonPayload returnPayLoadData()
	{
		BuildJsonPayload p = new BuildJsonPayload();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setName("Rahul Shetty Academy");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://rahulshettyacademy.com");

		List<String> typesVal = new ArrayList<>();
		typesVal.add("shoe park");
		typesVal.add("shop");
		p.setTypes(typesVal);

		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
	}
}

