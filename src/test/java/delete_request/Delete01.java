package delete_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Delete01 extends JsonPlaceHolderBaseUrl {

   /*
   Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
    */


    @Test
    public void delete01() {

        //Set the Url
        spec.pathParams("pp1","todos","pp2",198);

        // Set the expected data (istenen data { } seklinde bos bir json yada list)
        Map<String,Object> expectedDataMap = new HashMap<>();
        System.out.println(expectedDataMap);

        // Send the DELETE request and get the Response
        Response response=given().spec(spec).when().delete("/{pp1}/{pp2}");
        response.prettyPrint();

        // Do Assertion
        // expectedData Map formatinda Response ise Json formatinda oldugu icin
        // De-Serialization yapiyoruz
        // Response'i Map'e ceviriyoruz

        //1.YOL
        Map<String,Object> actualMap=response.as(HashMap.class);
        response.then().assertThat().statusCode(200);
        assertEquals(expectedDataMap ,actualMap);



        //2.YOL
        assertTrue(actualMap.size()==0);
        //Veya
        assertTrue(actualMap.isEmpty());//Tavsiye edilen

        //NOT:Delete request yapmadan once "Post Request" yapip o datayi silmeliyiz.(Baskasinin datasini silmemek icin)




    }
}
