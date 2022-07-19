package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;

public class Get12Pojo extends HerokuAppBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/18
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like
 		    {
                                "firstname": "omto",
                                "lastname": "nena",
                                "totalprice": 112,
                                "depositpaid": true,
                                "bookingdates": {
                                    "checkin": "2018-01-01",
                                    "checkout": "2019-01-01"
                                },
                                "additionalneeds": "Breakfast"
                            }
     */


    @Test
    public void getPojo01() {
        //Set the Url
        spec.pathParams("pp1","booking","pp2",18);

        //Set the Expected Data
        BookingDatesPojo bookingDates=new BookingDatesPojo("2018-01-01","2019-01-01");

        BookingPojo bookingPojo=new BookingPojo("omto","nena",112,true,bookingDates,"Breakfast");

        //Send the GET request and get the Response
       Response response= given().spec(spec).when().get("/{pp1}/{pp2}");


        //Do Assertion
        BookingPojo actualPojo = response.as(BookingPojo.class);
        Assert.assertEquals(200,response.getStatusCode());

        Assert.assertEquals(bookingPojo.getFirstname(),actualPojo.getFirstname());
        Assert.assertEquals(bookingPojo.getLastname(),actualPojo.getLastname());
        Assert.assertEquals(bookingPojo.getTotalprice(),actualPojo.getTotalprice());
        Assert.assertEquals(bookingPojo.getDepositpaid(),actualPojo.getDepositpaid());
        //1.Yol
        Assert.assertEquals(bookingPojo.getBookingdates().getCheckin(),actualPojo.getBookingdates().getCheckin());
        Assert.assertEquals(bookingPojo.getBookingdates().getCheckout(),actualPojo.getBookingdates().getCheckout());
        Assert.assertEquals(bookingPojo.getAdditionalneeds(),actualPojo.getAdditionalneeds());


        //2.Yol
        Assert.assertEquals(bookingDates.getCheckin(),actualPojo.getBookingdates().getCheckin());
        Assert.assertEquals(bookingDates.getCheckout(),actualPojo.getBookingdates().getCheckout());








    }
}
