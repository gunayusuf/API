package post_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;


public class Post04Pojo extends HerokuAppBaseUrl {
    /*
         Given
       1)     https://restful-booker.herokuapp.com/booking
       2)    {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast with white tea, Dragon juice"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast with white tea, Dragon juice"
                                     }
                                  }
     */

//Not once inner pojoyu olusturduk BookingDatesPojo Class'i


    @Test
    public void postPojo01() {
        //1. Set the Url
        spec.pathParam("pp1","booking");

        //2. Set the Expected Data
        //En KUCUKTEN(icten) en BUYUGE dogru obj olusturup deger giriyoruz

        BookingDatesPojo bookingDates=new BookingDatesPojo("2021-09-21","2021-12-21");

        BookingPojo bookingPojo=new BookingPojo("Ali","Can",999,true,bookingDates,"Breakfast with white tea, Dragon juice");

        //3. Send POST Request and Get the Response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(bookingPojo).when().post("/{pp1}");

        //4. D0 Assertion
//De-Serialization: JSON formatından Java objesine çevirme.
//Serialization: Java objesini JSON formatına çevirme.
// De-Serialization ve Serialization iki türlü yapılır:
//Gson: Goole tarafından üretiliştir.
//Object Mapper: Daha popüler ***


        //GSON KULLANARAK DE-SERİALİZATİON YAPİYORUZ
        BookingResponseBodyPojo actualPojo = response.as(BookingResponseBodyPojo.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(bookingPojo.getFirstname(),   actualPojo.getBooking().getFirstname());
        assertEquals(bookingPojo.getLastname(),    actualPojo.getBooking().getLastname());
        assertEquals(bookingPojo.getTotalprice(),  actualPojo.getBooking().getTotalprice());
        assertEquals(bookingPojo.getDepositpaid(), actualPojo.getBooking().getDepositpaid());
        assertEquals(bookingPojo.getAdditionalneeds(),actualPojo.getBooking().getAdditionalneeds());

        //1.YOL
        assertEquals(bookingPojo.getBookingdates().getCheckin(),    actualPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingPojo.getBookingdates().getCheckout(),   actualPojo.getBooking().getBookingdates().getCheckout());

        //2.YOL
        assertEquals(bookingDates.getCheckin(),    actualPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),   actualPojo.getBooking().getBookingdates().getCheckout());


    }
}
