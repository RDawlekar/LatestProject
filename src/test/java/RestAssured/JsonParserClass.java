package RestAssured;

import io.restassured.path.json.JsonPath;

public class JsonParserClass extends RestAssuredPOC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js = new JsonPath(PayLoad.CoursePrice());

		System.out.println(js.getString("dashboard.purchaseAmount"));
		int sum=0;

		for(int i=0;i<js.getInt("courses.size()");i++)
		{
			int price=js.getInt("courses["+i+"].price");	
			int copies=js.getInt("courses["+i+"].copies");
			System.out.println(price);
			System.out.println(copies);

			sum=sum+price*copies;
		}
		System.out.println(sum);

	}

}
