package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get08 extends JsonPlaceHolderBaseUrl {


    //NOT:
    //API'DAN APP'A==> De-Serialization: JSON formatindan Java objesine cevirme.(Converting)
    //APP'DAN API'A ==> Serialization: Java objesini JSON formatina cevirme.
                //De-Serialization VE Serialization iki turlu yapilir:
                //Gson: Google tarafindan uretilmistir
                // Object Mapper: Daha populer bunu kullanacagiz ***

    /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */

    //NOT:
    //API'DAN APP'A==> De-Serialization: JSON formatindan Java objesine cevirme.(Converting)
    //APP'DAN API'A ==> Serialization: Java objesini JSON formatina cevirme.
            //De-Serialization VE Serialization iki turlu yapilir:
            //Gson: Google tarafindan uretilmistir
            // Object Mapper: Daha populer bunu kullanacagiz ***


    @Test
    public void get01() {

        //1. Step: Set the Url
        spec.pathParams("first","todos","second",2);

        //2. Step: Set the expected Data

        Map<String,Object> expectedData= new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        expectedData.put("StatusCode",200);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");

        //3. Step: Send the request and Get the Response
        Response response = given().spec(spec).when().get("{first}/{second}");
        // Datayi DeSerializaton yapacagiz. // responseyi HashMap'e cevirecegiz

        Map<String,Object>actualData=response.as(HashMap.class);

        //4. Step:DO Assertion
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));

        //expectedData(Map), ve response ile assert ediyoruz
        assertEquals(expectedData.get("StatusCode"),response.getStatusCode());
        assertEquals(expectedData.get("Via"),response.getHeader("Via"));
        assertEquals(expectedData.get("Server"),response.getHeader("Server"));
    }


    @Test
    public void get02(){

        //1. Step: Set the Url
        spec.pathParams("first","todos","second",2);

        //2. Step: Set the expected Data
        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData();
       Map<String,Object>expectedDataMap =expectedData.expectedDataWithAllKeys(1,"quis ut nam facilis et officia qui", false);
        expectedDataMap.put("StatusCode",200);
        expectedDataMap.put("Via","1.1 vegur");
        expectedDataMap.put("Server","cloudflare");


        //3. Step: Send the request and Get the Response
        Response response = given().spec(spec).when().get("{first}/{second}");
        // Datayi DeSerializaton yapacagiz. // responseyi HashMap'e cevirecegiz

        Map<String,Object>actualData=response.as(HashMap.class);
        System.out.println(actualData);

        //4. Step:DO Assertion
        assertEquals(expectedDataMap.get("userId"),actualData.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualData.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualData.get("completed"));

        //expectedData(Map), ve response ile assert ediyoruz
        assertEquals(expectedDataMap.get("StatusCode"),response.getStatusCode());
        assertEquals(expectedDataMap.get("Via"),response.getHeader("Via"));
        assertEquals(expectedDataMap.get("Server"),response.getHeader("Server"));



    }

}
