package GoogleMap.WeatherApi;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipInputStream;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
















import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.response.ValidatableResponseOptions;
import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.RestAssured.*;

public class XMLServerRequest {
	
	private RequestSpecification reqSpec;
	private RequestSpecBuilder builder;
	private RequestSpecification reqSpec2;
	private RequestSpecBuilder builder2;

	
	//@BeforeTest
	public void setBaseURI(){
		RestAssured.baseURI="https://samples.openweathermap.org";
	  
	}
	@BeforeTest
	public void requestSpec(){
		builder=new RequestSpecBuilder();
		builder.setBaseUri("https://samples.openweathermap.org");
		builder.setBasePath("/data/2.5/weather");
		builder.addParam("q", "London");
		builder.addParam("mode", "xml");
		builder.addParam("appid", "b6907d289e10d714a6e88b30761fae22");
		reqSpec=builder.build();
		
		
		
		builder2=new RequestSpecBuilder();
		builder2.setBaseUri("https://samples.openweathermap.org");
		builder2.setBasePath("/data/2.5/weather");
		builder2.addParam("q", "London");
		//builder.addParam("mode", "xml");
		builder2.addParam("appid", "b6907d289e10d714a6e88b30761fae22");
		reqSpec2=builder2.build();


	}
	
	//Get Requst 
	
	//@Test
	public void test_01(){
		
		 Response res  =given ().relaxedHTTPSValidation()
				 	.param ("q", "London")
				 	.param ("mode", "xml")
				 	.param ("appid", "b6907d289e10d714a6e88b30761fae22")
				    .when()
				    .get ("https://samples.openweathermap.org/data/2.5/weather").
				    then()
				    .contentType(ContentType.XML)
				    .extract().response();


				    System.out.println (res.asString ());

		
		
		
	}
	//@Test
	public void test_02(){
		
		
		 String  str  =given ().relaxedHTTPSValidation()
				 	.param ("q", "London")
				 	.param ("mode", "xml")
				 	.param ("appid", "b6907d289e10d714a6e88b30761fae22")
				    .when()
				    .get ("/data/2.5/weather").
				    then()
				    .contentType(ContentType.XML)
				    .extract().
				   // path("current.city.country");
		 			path("current.temperature.@value");


				    System.out.println (str);

		
	}

	 @Test
	public void test_03(){
		
		
		  Long  time  =given ().relaxedHTTPSValidation().spec(reqSpec)				 	
					.when()
				    .get ("").
				    then()
				    .contentType(ContentType.XML)
				    .extract().
				   // path("current.city.country");
				  //  path("current.temperature.@value");
				   // .statusCode (200);
				    time();
				    
				  System.out.println ("Time taken XML   "+time);

				  Long  time2  =given ().relaxedHTTPSValidation().spec(reqSpec2)				 	
							.when()
						    .get ("").
						    then()
						    .contentType(ContentType.JSON)
						    .extract().
						   // path("current.city.country");
						  //  path("current.temperature.@value");
						   // .statusCode (200);
						    time();
						    
						  System.out.println ("Time taken JSON   "+time2);
	}
	
	
	
	//@Test
	public void fileDownload(){
		 int fileSize;
		   String fileName="D:\\eclipse\\workspace\\WeatherApi\\resources\\rest-assured-3.0.5-dist.zip";
		  // FileInputStream fis = new FileInputStream(fileName);
         //  BufferedInputStream bis = new BufferedInputStream(fis);
          // ZipInputStream stream = new ZipInputStream(bis))
           
           
           
		//   fileSize = (int)bis.length();
		   
		   //System.out.println("The actual value is "+fileSize);

		   byte [] expectedValue =
		     given ()
		     .get("http://dl.bintray.com/johanhaleby/generic/rest-assured-3.0.5-dist.zip")
		     .asByteArray();
		   

		          System.out.println("The expected value is "+expectedValue.length);

		 //  Assert.assertEquals(fileSize, expectedValue.length); 

	}
	
}
