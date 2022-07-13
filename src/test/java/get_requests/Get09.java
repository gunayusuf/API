package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerokuAppBaseUrl {

    /*
    Given
            https://restful-booker.herokuapp.com/booking/91
        When
            I send GET Request to the url
        Then
            Response body should be like that;
         {
        "firstname": "James",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
      }

     */

    @Test
    public void get01() {
        //1.Step: Set the Url
        spec.pathParams("first","booking","second",91);

        //2.Step: Set the ExpectedData
            //Once inner map'i yapiyoruz (checkin-checkout kismi)

        Map<String,String> bookingDatesMap=new HashMap<>();
        bookingDatesMap.put("checkin","2018-01-01");
        bookingDatesMap.put("checkout","2019-01-01");

        //Outer Map
        //====================================================
        Map<String,Object> expectedDataMap=new HashMap<>();
        expectedDataMap.put("firstname","James");
        expectedDataMap.put("lastname","Brown");
        expectedDataMap.put("totalprice",111);
        expectedDataMap.put("depositpaid",true);
        expectedDataMap.put("bookingdates",bookingDatesMap);
        expectedDataMap.put("additionalneeds","Breakfast");

        System.out.println(expectedDataMap);

        //================================================

        //3.Step: Send the req and Get the response

       Response response = given().spec(spec).when().get("/{first}/{second}");

       Map<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);


        //4.Step: Do Assertion
        assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));

        //burada inner map'e ulasmak icin once inner icine girdik daha sonrasinda actual data obje oldugu icin
        // direkt ulasamadik. Bu yuzden actual olan map'i (Map) seklinde belirttik ki bu sayede get()
        // methodunu kullanabildik ve assert yaptik.
        assertEquals(bookingDatesMap.get("checkin"),((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"),((Map)actualDataMap.get("bookingdates")).get("checkout"));

        assertEquals(expectedDataMap.get("additionalneeds"),actualDataMap.get("additionalneeds"));


    }
}
