package GoogleMap.WeatherApi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;





























import GRMAPITesting.com.org.classes.UpdateMasterFile;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.config.XmlConfig;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.RestAssured.*;

public class FlightSchedule {
	
	private RequestSpecification reqSpec;
	private RequestSpecBuilder builder;
	private UpdateMasterFile update;

	


	@BeforeTest
	public void requestSpec(){
		builder=new RequestSpecBuilder();
		builder.setBaseUri("http://54.152.212.208");
		builder.setBasePath("/sabre/flightWebServices/services/serviceWrapper.php");
		reqSpec=builder.build();
		
		
		



	}
	
	//Get Requst 
	
	//@Test
	public void test_01(){
		String myRequest = null;
		try {
			myRequest = GenerateStringFromResource("D:\\API testing Json DB\\Request.xml");
		//System.out.println(myRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Response res  =given ().
			     config(RestAssuredConfig.newConfig().
		                    xmlConfig(XmlConfig.xmlConfig().
		                            namespaceAware(true).
		                            declareNamespace("ns9921", "http://tempuri.org"))).
		            spec(reqSpec).
					header("User-Agent", "NuSOAP/0.9.5 (1.123)").
					header("Content-Type", "text/xml").
					header("Cache-Control", "no-cache").
					contentType("application/soap+xml; charset=ISO-8859-1;").
				  	body(myRequest).
				
				    when()
				    		.post ("").
					then().
						contentType("text/xml").       	
						extract().response();
		
		System.out.println(res.asString());
				    
		if(res.getStatusCode()==200){
			System.out.println("API is working fine");
		} else {
			System.out.println("API is not working");
		}
		
		System.out.println("Status Code  "+res.getStatusCode());



				 

		
		
		
	}
	
	//@Test
	public void test_02(){
		String myRequest = null;
		try {
			myRequest = GenerateStringFromResource("D:\\API testing Json DB\\Request.xml");
		//System.out.println(myRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  Map<String, String> authhdrs = new HashMap<String, String>();
		    authhdrs.put("User-Agent", "NuSOAP/0.9.5 (1.123)");
		    authhdrs.put("Content-Type","text/xml" );	
		    authhdrs.put("Cache-Control","no-cache" );	
		    
		    
		   Response resp = given().request().headers(authhdrs)
		      .contentType("text/xml").body(myRequest)
		      .when()
		      .post( "http://54.152.212.208/sabre/flightWebServices/services/serviceWrapper.php" )
		      .andReturn();
		      


		   	   
		System.out.println( resp.asString() );
		
		
		/*
		 
		String myRequest = null;
		try {
			myRequest = GenerateStringFromResource("D:\\API testing Json DB\\Request.xml");
		//System.out.println(myRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  Map<String, String> authhdrs = new HashMap<String, String>();
		    authhdrs.put("User-Agent", "NuSOAP/0.9.5 (1.123)");
		    authhdrs.put("Content-Type","text/xml" );	
		    authhdrs.put("Cache-Control","no-cache" );	
		    
		    
		   Response resp = given().request().headers(authhdrs)
		      .contentType("text/xml").body(myRequest)
		      .when()
		      .post( "http://54.152.212.208/sabre/flightWebServices/services/serviceWrapper.php" )
		      .then()
		      .extract()
		     	
		      .path("SOAP-ENV.SOAP-ENV.ns1");
		      
		   //  String s=resp.path("SOAP-ENV:Envelope.SOAP-ENV:Body.ns1:serviceWrapperResponse.return.response.item[0]").toString() ;   
		  
		     
		     System.out.println(resp.asString());
		//System.out.println( resp.asString() );
	
		 */
	}
	
	@Test(priority=1)
	public void test_04(){
		update=new UpdateMasterFile() ;
		update.updatePom("D:\\API testing Json DB\\Request.xml", "MAA");	
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test(priority=2)
	public void test_03(){
		String myRequest = null;
		try {
			myRequest = GenerateStringFromResource("D:\\API testing Json DB\\Request.xml");
		//System.out.println(myRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  Map<String, String> authhdrs = new HashMap<String, String>();
		    authhdrs.put("User-Agent", "NuSOAP/0.9.5 (1.123)");
		    authhdrs.put("Content-Type","text/xml" );	
		    authhdrs.put("Cache-Control","no-cache" );	
		    
		    
		   Response resp = given().request().headers(authhdrs)
		      .contentType("text/xml").body(myRequest)
		      .when()
		      .post( "http://54.152.212.208/sabre/flightWebServices/services/serviceWrapper.php" )
		      .thenReturn();
		   
		   Long timeTaken= resp.then().
				      extract().time();
		   
		     System.out.println("Time Taken for  "  +timeTaken +"  MilliSeconds");
		  
		     System.out.println(resp.asString());
		     
		   /*  XmlPath xml=new XmlPath(resp.asString());
		   
		   
		   System.out.println(xml.getString("Envelope.Body.serviceWrapperResponse.return.response.item.item[0].airlinesCode"));
		      
		    
		   
		   
		   
		   
		   List<String> list =xml.getList("Envelope.Body.serviceWrapperResponse.return.response.item.item.airlinesCode");
		     

		     System.out.println("size  "+list.size());
		     
		     for(String str:list){
		      System.out.println(str);
		      }*/
		   	   
	//System..println( resp.asString() );
		
	}
	
	
	
	public static String GenerateStringFromResource(String path) throws IOException {
			
	    return new String(Files.readAllBytes(Paths.get(path)));

	}





	
}
