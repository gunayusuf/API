package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerokuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Aaron" and last name is "Chen"
     */


    @Test
    public void get01() {

        //1. Step: Set the Url
        spec.pathParam("first","booking").
                queryParams("firstname","Aaron",
                        "lastname","Chen");

        //https://restful-booker.herokuapp.com/booking?firstname=Aaron&lastname=Chen son hali bu olacak
        // Soru ? isaretinden sonraki kisima queryParam(s)
        // Soru ? isaretinden onceki kisima / ile olan yerlere ise pathParam(s) olarak geciyor



        //2. Step: Set the expected data



        //3. Step: Send the request and Get the response
        // once pathParam'i cagirdik /{first}
      Response response = given().spec(spec).when().get("/{first}");
      response.prettyPrint(); // "bookingid": 1649

        //4. Step: Do Assertion

        response.then().assertThat().statusCode(200);
        assertTrue(response.asString().contains("bookingid"));




    }
}

