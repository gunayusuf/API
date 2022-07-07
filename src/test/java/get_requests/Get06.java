package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerokuAppBaseUrl {
    /*
       Given
            https://restful-booker.herokuapp.com/booking/101
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
          {
        "firstname": "GGS",
        "lastname": "FINCH",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
          }

     */

    @Test
    public void get01() {

    //1 Step: Set the Url
        spec.pathParams("first","booking", "second",101);

    //2 Step: Set the expected data

    //3 Step: Send the request and get response
   Response response = given().spec(spec).when().get("/{first}/{second}");
  // response.prettyPrint();

    //4 Step: Do Assertion

        //1.YOL
        response.
                then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname",equalTo("GGS"),
                        "lastname",equalTo("FINCH"),
                        "totalprice",equalTo(111),
                        "depositpaid",equalTo(true),
                        "bookingdates.checkin",equalTo("2018-01-01"),
                        "bookingdates.checkout",equalTo("2019-01-01"));


                //NOT NESTEDLARDA . (NOKTA) koyarak ic ice olan verilere ulasabiliriz

        //2.YOL ==> JsonPath Class kullanacagiz

        JsonPath jsonPath = response.jsonPath();  //json = new JsonPath(response.asInputStream()); bu sekilde de olusturulabilir
        assertEquals("GGS",jsonPath.getString("firstname"));
        assertEquals("FINCH",jsonPath.getString("lastname"));
        assertEquals(111,jsonPath.getInt("totalprice"));
        assertEquals(true,jsonPath.getBoolean("depositpaid"));
        assertEquals("2018-01-01",jsonPath.getString("bookingdates.checkin"));
        assertEquals("2019-01-01",jsonPath.getString("bookingdates.checkout"));
        //Bu sekilde hard Assertion oldu


        //3.YOL: Soft Assertion
        //Soft Assertion icin 3 adim izlenir;

        //1) SoftAssert Objesi olusturulur
        SoftAssert softAssert=new SoftAssert();

        //2) Olusturdugumuz Obje araciligi ile Assertion yapacagiz
        softAssert.assertEquals(jsonPath.getString("firstname"),"GGS","firstname eslesmedi");

        softAssert.assertEquals(jsonPath.getString("lastname"),"FINCH");
        softAssert.assertEquals(jsonPath.getString("totalprice"),111);

        //3) assertAll() methodu kullanilir. Aksi takdirde assert islemi gerceklesmez.
        softAssert.assertAll();











    }
}
