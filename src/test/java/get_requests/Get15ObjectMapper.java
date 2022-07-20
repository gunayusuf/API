package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15ObjectMapper extends HerokuAppBaseUrl {
    /*
    Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
                {
                    "firstname": "Oliver",
                    "lastname": "Smith",
                    "totalprice": 100,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2022-07-18",
                        "checkout": "2022-07-19"
                    },
                    "additionalneeds": "Breakfast"
                }
     */

    @Test
    public void get01() {
        //Set the Url
        spec.pathParams("pp1","booking","pp2",22);

        //Set the expected Data
        String expectedData="{\n" +
                "                    \"firstname\": \"Oliver\",\n" +
                "                    \"lastname\": \"Smith\",\n" +
                "                    \"totalprice\": 100,\n" +
                "                    \"depositpaid\": true,\n" +
                "                    \"bookingdates\": {\n" +
                "                        \"checkin\": \"2022-07-18\",\n" +
                "                        \"checkout\": \"2022-07-19\"\n" +
                "                    },\n" +
                "                    \"additionalneeds\": \"Breakfast\"\n" +
                "                }";

        BookingPojo exptectedDataPojo = JsonUtil.convertJsonToJavaObject(expectedData, BookingPojo.class);

        //Send the GET Request and get the RESPONSE
        Response response = given().spec(spec).when().get("/{pp1}/{pp2}");

        //Do Assertion
       BookingPojo actualDataPojo = JsonUtil.convertJsonToJavaObject(response.asString(),BookingPojo.class);

       //Hard Assertion
       assertEquals(200,response.getStatusCode());
       assertEquals(exptectedDataPojo.getFirstname(),actualDataPojo.getFirstname());
       assertEquals(exptectedDataPojo.getLastname(),actualDataPojo.getLastname());
       assertEquals(exptectedDataPojo.getTotalprice(),actualDataPojo.getTotalprice());
       assertEquals(exptectedDataPojo.getDepositpaid(),actualDataPojo.getDepositpaid());
       assertEquals(exptectedDataPojo.getBookingdates().getCheckin(),actualDataPojo.getBookingdates().getCheckin());
       assertEquals(exptectedDataPojo.getBookingdates().getCheckout(),actualDataPojo.getBookingdates().getCheckout());
       assertEquals(exptectedDataPojo.getAdditionalneeds(),actualDataPojo.getAdditionalneeds());


        //Soft Assertion
        //SoftAssert objesi olustur
        SoftAssert softAssert = new SoftAssert();

        //Assertion yap
        softAssert.assertEquals(response.getStatusCode(),200,"status code hata");
        softAssert.assertEquals(actualDataPojo.getFirstname(),exptectedDataPojo.getFirstname(),"firstname uyusmadi");
        softAssert.assertEquals(actualDataPojo.getLastname(),exptectedDataPojo.getLastname(),"Lastname uyusmadi");
        softAssert.assertEquals(actualDataPojo.getTotalprice(),exptectedDataPojo.getTotalprice(),"Totalprice uyusmadi");
        softAssert.assertEquals(actualDataPojo.getDepositpaid(),exptectedDataPojo.getDepositpaid(),"Depositpaid uyusmadi");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckin(),exptectedDataPojo.getBookingdates().getCheckin(),"Checkin uyusmadi");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckout(),exptectedDataPojo.getBookingdates().getCheckout(),"Checkout uyusmadi");
        softAssert.assertEquals(actualDataPojo.getAdditionalneeds(),exptectedDataPojo.getAdditionalneeds(),"Additionalneeds uyusmadi");

     //assertAll() methodunu calistir
        softAssert.assertAll();


    }


}
