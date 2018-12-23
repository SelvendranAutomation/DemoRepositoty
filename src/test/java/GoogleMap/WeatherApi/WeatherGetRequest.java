package GoogleMap.WeatherApi;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
 import static com.jayway.restassured.RestAssured.*;
 

public class WeatherGetRequest {
	
	//Simple get request for getting weather request by city name 
	
	@Test
	public void test_01(){
		Response resp=when().get("http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
	System.out.println("Status Code"+resp.getStatusCode());
	}


	@Test
	public void test_02(){
		Response resp=given().param("id","2172797").param("appid","sdfdsffjsjfsdjfljsljdlljfgdffgdfg").when().get("http://samples.openweathermap.org/data/2.5/weather");
	
		if(resp.getStatusCode()==200){
			System.out.println("API is working fine");
		} else {
			System.out.println("API is not working");
		}
		
		System.out.println("Status Code"+resp.getStatusCode());
	} 
	
	@Test
	public void test_03(){
		Response resp=given().param("lat","35").param("lon","138").param("appid","b6907d289e10d714a6e88b30761fae22").when().get("http://samples.openweathermap.org/dat/2.5/weather");
	
		if(resp.getStatusCode()==200){
			System.out.println("API is working fine");
		} else {
			System.out.println("API is not working");
		}
		
		System.out.println("Status Code"+resp.getStatusCode());
	} 
}
