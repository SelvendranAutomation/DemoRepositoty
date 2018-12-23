package GoogleMap.WeatherApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
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

public class AgencyAutoFlightScheduleRequest {
	
	private RequestSpecification reqSpec;
	private RequestSpecBuilder builder;


	
	//@BeforeTest
	public void setBaseURI(){
		RestAssured.baseURI="https://samples.openweathermap.org";
	  
	}
	//@BeforeTest
	public void requestSpec(){
		builder=new RequestSpecBuilder();
		builder.setBaseUri("http://preprodaws.atyourprice.net/");
		builder.setBasePath("/AllWebServices2.0/webSearch2.0/webServiceSearch.php");
		reqSpec=builder.build();
	
		
		




	}
	
	//Get Requst 
	
	@Test
	public void test_01(){
		String myRequest = null;
		try {
			myRequest = GenerateStringFromResource("D:\\API testing Json DB\\testFlight.json");
		//System.out.println(myRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		  Map<String, String> authhdrs = new HashMap<String, String>();
		    authhdrs.put("Cache-control", "no-cache");
		    authhdrs.put("Content-type","application/x-www-form-urlencoded" );	
		    authhdrs.put("Requestauthorization","ttApXaM3ky0g7VXFUSj7RccmRlWxmwfFOFTBDvsORjUO4FkMlrH1rJd8VXv5izBW4ilyDCajJSXBn5dxkIoBhpA0dTUlil5btgxUIx3hfPIqKJNVh1mnfKbgsVpA4kzlqW4XjX2oABFb8gUe64rbDqhVcwj4EJgSvaFDVyRmXklTp3XlXckotr9tLjnfQrXbprCbNilyswhFn5Qa70pofmHOtTR9aF9oUBpxJyV0S2v5Va3RYg52tDEKkmKkPHwz" );	
		    Map<String, String> bodyContent = new HashMap<String, String>();
		    bodyContent.put("mode", "air");
		    bodyContent.put("devMode", "true");
		    bodyContent.put("agencyId", "102");
		    
		   Response resp = given().request().headers(authhdrs)
					      .contentType(ContentType.JSON).body(myRequest)
					      .body("{\"date\":{\"onward\":\"2018-09-26\",\"return\":\"\"},\"passengers\":{\"adult\":\"1\",\"child\":0,\"infant\":0},\"sector\":{\"origin\":\"MAA\",\"destination\":\"BOM\"},\"types\":{\"requestedAirline\":[\"9W\"],\"blockedAirlines\":[\"9W\",\"AI\",\"UK\",\"G8\",\"SG\",\"6E\",\"2T\"],\"airlinecode\":\"G8\",\"faretype\":\"CF\",\"tripType\":\"O\",\"travel_type\":\"D\"},\"cabinclass\":\"E\",\"refundableFare\":\"N\",\"agentId\":101,\"currency_type\":\"INR\",\"promoCode\":{\"airline\":[{\"airlineCode\":\"G8\",\"bookingClass\":\"CF\",\"fareType\":\"CF\",\"corporateid\":\"105\",\"fareBasisCode\":\"S4ICDO/ICDU\",\"promoCode\":\"VIVRITA\",\"promo_code_id\":\"1037\"}]}}&mode=air&devMode=true&agencyId=102")
					 
		      .when()
		      .post( "http://preprodaws.atyourprice.net/AllWebServices2.0/webSearch2.0/webServiceSearch.php" );
		     
		      

		   System.out.println("Status code  "  +resp.getStatusCode());
		   	   
		System.out.println( resp.asString() );
		
		


	}



	
	public static String GenerateStringFromResource(String path) throws IOException {
		
	    return new String(Files.readAllBytes(Paths.get(path)));

	}

	


}
