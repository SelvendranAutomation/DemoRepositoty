package GoogleMap.WeatherApi;

import org.testng.annotations.Test;

import APITesting.com.org.classes.Info;
import APITesting.com.org.classes.Posts;
import APITesting.com.org.classes._Posts;
import APITesting.com.org.classes.Advanced._Info;
import APITesting.com.org.classes.Advanced.__Posts;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static org.hamcrest.Matchers.lessThan;

import static com.jayway.restassured.RestAssured.*;

public class JSONServerRequest {
	
	//Get Requst 
	
	//@Test
	public void test_01(){
		
		Response resp=given().
						when().
						get("http://localhost:3000/posts");
		
		String result=resp.asString();
		System.out.println("Get response is : " + result);
	}
	
	//@Test
	public void test_02(){
		
	Response resp=given().
		 			body("{ \"id\": \"2\","
		 					+ "\"title\": \"dummy title\", "
		 					+ "\"author\" : \"selva\" }").
		 			when().
		 			contentType(ContentType.JSON).
		 			post("http://localhost:3000/posts");
	String opResult=resp.asString();
	
	System.out.println("Post Response is : " +opResult);
	
	}
	//Post  Post by Object 
	//@Test
	public void test_03(){
		Posts posts=new Posts();
		posts.setId("3");
		posts.setTitle("Post Request by Object");
		posts.setAuthor("Selva");
		
		Response resp=given().
				body(posts).
				when().
				contentType(ContentType.JSON).
				post("http://localhost:3000/posts");
		
		String result=resp.asString();
		
		System.out.println("Post response by Object"+result);
				
					
	}
	
	//Get by id
	
	//@Test
	public void test_04()
	{
		Response resp=given().
				when().
				get("http://localhost:3000/posts/2");
		
		String result=resp.asString();
		
		System.out.println("Get by id:  " +result);
	}

	
	//Put     //PUT by Object
	
	//@Test
	public void test_05(){
		
		Posts posts=new Posts();
		posts.setId("2");
		posts.setTitle("Updated Title");
		posts.setAuthor("Updated Author");
		
		Response response=given().
						when().
						contentType(ContentType.JSON).
						body(posts).
						put("http://localhost:3000/posts/3");
		
		System.out.println("Put Response object : "+response.asString());
		
	}
	//PATCH  /posts/1
	
	//@Test 
	public void test_06(){
		
		    Response resp=      given().
		          body("{ \"title\":\"Updated by batch request \"  }").
		          when().
		          contentType(ContentType.JSON).
		          patch("http://localhost:3000/posts/3");
		System.out.println("Batch reqeust Response :" +resp.asString());
	}
	
	
	//DELETE /posts/1
	//@Test
	public void test_07(){
		
		Response resp=given().
				when().
				delete("http://localhost:3000/posts/3");
		
		System.out.println("Delete request Response :"+resp.asString());
		
	}
	
	//Complex Post Object 
	//POST   /posts
	 //@Test
	public void test_08(){
		
		Info info= new Info();
		info.setEmail("selenium2-api@testing.com");
		info.setPhone("11111111112");
		info.setAddress("Chennai tamil nadu india 2" );
		
		_Posts posts=new _Posts();
		posts.setId("04");
		posts.setTitle("Updated Title by comples Posts 2");
		posts.setAuthor("Author Updated by complex Posts 2");
		posts.setInfo(info);
		
		Response resp=given().
				when().
				body(posts).
				contentType(ContentType.JSON).
				post("http://localhost:3000/posts");
		
		System.out.println("Complex object by post" + resp.asString());
		
		
		
		
		
		
	}
	
	
	//More Complex Post Object
	
	//POST   /posts
	
	//@Test
	public void test_09(){
		
		_Info info1=new _Info();
		info1.setEmail("testfirst@test.com");
		info1.setPhone("111111111");
		info1.setAddress("Address lane one");
		_Info info2=new _Info();
		info2.setEmail("testsecond@test.com");
		info2.setPhone("22222222");
		info2.setAddress("Address lane two");
		
		__Posts posts=new __Posts();
		posts.setId("10");
		posts.setTitle("Title is updated by Posts");
		posts.setAuthor("Updated Author");
		posts.setInfo(new _Info[]{info1,info2});
		
		
		Response resp=given().
						when().
						body(posts).
						contentType(ContentType.JSON).
						post("http://localhost:3000/posts");
		
		System.out.println("More complex Response: "+resp.asString());
	}
	
	//Get request to calculate Response time
	
	//GET for all Posts
	
	@Test
	public void test_10(){
		
		Response resp=given().
		when().
		get("http://localhost:3000/posts");
		
		Long timeTaken=resp.
		then().
		extract().
		time();
			
		System.out.println("Time Taken for the response :" +timeTaken);
		
		try{given().
		when().
		get("http://localhost:3000/posts").
		then().
		and().
		time(lessThan(800L));
		}catch(Throwable t){
			System.out.println("Not as expected time ");
		}
		
	}
	
	
}
