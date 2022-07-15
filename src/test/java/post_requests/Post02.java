package post_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Post02 extends HerokuAppBaseUrl {

    /*
    Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "John",
                 "lastname": "Doe",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  }
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "bookingid": 5315,
                                                "booking": {
                                                    "firstname": "John",
                                                    "lastname": "Doe",
                                                    "totalprice": 11111,
                                                    "depositpaid": true,
                                                    "bookingdates": {
                                                        "checkin": "2021-09-09",
                                                        "checkout": "2021-09-21"
                                                    }
                                                }
                                             }
     */


    @Test
    public void post01() {
        //1. Set the Url
        spec.pathParam("first","booking");

        //2. Set the Expected Data
        //inner'i obje cagirarak aldik ve elimizde bulundurabilmek icin bir map'in icine attik boylece inner elimize gecti
        HerokuAppTestData herokuApp=new HerokuAppTestData();
        Map<String,String> bookingdatesMap=herokuApp.bookingdatesSetUp("2021-09-09","2021-09-21");

        //Outer Map'i doldurduk ve olusturdugumuz inner Map'i kullandik ve outer map'i elimizde tutabilmek icin yeni bir Map'e atadik
        Map<String,Object> expectedDataMap=herokuApp.expectedDataSetUp("Yusuf","Gunay",11111,true,bookingdatesMap);


        //3. Send the Post Request and get the Response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}");
        response.prettyPrint();

        //4. Do Assertion
        //***NOT expecteddataMap ile (actualData)response'u assert edebilmek icin response'i Map'e cevirmek zorundayiz ***

        Map<String,Object> actualDataMap=response.as(HashMap.class);
        assertEquals(200,response.getStatusCode());

        assertEquals(expectedDataMap.get("firstname"),((Map)actualDataMap.get("booking")).get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),((Map)actualDataMap.get("booking")).get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),((Map)actualDataMap.get("booking")).get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),((Map)actualDataMap.get("booking")).get("depositpaid"));

        assertEquals(bookingdatesMap.get("checkin"),((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"),((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkout"));




    }
}
