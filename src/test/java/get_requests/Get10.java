package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get10 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/2986
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
      {

        "meta": null,
        "data": {
        "id": 2965,
        "name": "Mr. Gita Menon",
        "email": "gita_menon_mr@bayer.com",
        "gender": "female",
        "status": "inactive"
                }
    }
     */

    @Test
    public void get01() {
        //1. Step: Set the Url
        spec.pathParams("first","users","second",2965);

        //2. Step: Set the Expected Data
        //Once inner Map'i yapiyoruz (data kismi)
        Map<String,String> dataKeyMap=new HashMap<>();
        dataKeyMap.put("name","Mr. Gita Menon");
        dataKeyMap.put("email","gita_menon_mr@bayer.com");
        dataKeyMap.put("gender","female");
        dataKeyMap.put("status","inactive");

        // Outer Map inner'i da kapsiyor
        Map<String, Object> expectedData=new HashMap<>();
        expectedData.put("meta",null);
        expectedData.put("data",dataKeyMap);


        //3 Step: Send the request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");

       //***===*** //De Serialization yapiyoruz Response'i Map'e ceviriyoruz olarak ceviriyoruz, Actual Datamizi aliyoruz
        Map<String,Object> actualDataMap= response.as(HashMap.class);

        //4.Step: Do Assertion

        assertEquals(expectedData.get("meta"),actualDataMap.get("meta"));
        assertEquals(dataKeyMap.get("name"),((Map)actualDataMap.get("data")).get("name"));
        assertEquals(dataKeyMap.get("email"),((Map)actualDataMap.get("data")).get("email"));
        assertEquals(dataKeyMap.get("gender"),((Map)actualDataMap.get("data")).get("gender"));
        assertEquals(dataKeyMap.get("status"),((Map)actualDataMap.get("data")).get("status"));

    }




    //2.YOL TestData package'den cagirarak yapmak
    // Bu yol Tercih ediliyor genelde


    @Test
    public void get02() {

        //1. Step: Set the Url
        spec.pathParams("first","users","second",2965);

        //2. Step: Set the Expected Data
        GoRestTestData dataKey=new GoRestTestData(); //Gerekli methodun cagrilmasi icin obje olusturuldu
       Map<String,String> dataKeyMap= dataKey.dataKeyMap("Mr. Gita Menon","gita_menon_mr@bayer.com","female","inactive");// ic mapi olusturdum

       Map<String,Object>expectedData=dataKey.expectedDataMap(null,dataKeyMap); // Outer Map, Dıs taraftaki Map olusturdum



         //3 Step: Send the request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //***===*** //De Serialization yapiyoruz ,Actual Datamizi aliyoruz
        Map<String,Object> actualDataMap= response.as(HashMap.class);// De-Serialization==> Json formattan Java objesine cevirdik

        //4.Step: Do Assertion

        assertEquals(expectedData.get("meta"),actualDataMap.get("meta"));
        assertEquals(dataKeyMap.get("name"),((Map)actualDataMap.get("data")).get("name")); //Once "data" elementine ulasip buradan aldigim objeyi Map formatina cast ediyorum
        assertEquals(dataKeyMap.get("email"),((Map)actualDataMap.get("data")).get("email"));
        assertEquals(dataKeyMap.get("gender"),((Map)actualDataMap.get("data")).get("gender"));
        assertEquals(dataKeyMap.get("status"),((Map)actualDataMap.get("data")).get("status"));



    }
}
