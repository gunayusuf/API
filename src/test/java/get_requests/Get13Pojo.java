package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestResponseBodyPojo;

import static io.restassured.RestAssured.*;


public class Get13Pojo extends GoRestBaseUrl {
    /*
    Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
            {
            "meta": null,
             "data": {
                 "id": 2508,
                 "name": "Akshita Nehru",
                 "email": "nehru_akshita@jast.info",
                 "gender": "female",
                 "status": "active"
                }
            }
     */

    @Test
    public void getPojo01() {
        //Set the Url
        spec.pathParams("pp1","users","pp2",2508);


        //Set the Expected Data
        GoRestDataPojo goRestDataPojo=new GoRestDataPojo(2508,"Akshita Nehru","nehru_akshita@jast.info","female","active");

        GoRestResponseBodyPojo goRestResponseBodyPojo=new GoRestResponseBodyPojo(null,goRestDataPojo);

        //Send the Get request and get the Response

        Response response = given().spec(spec).when().get("/{pp1}/{pp2}");
        response.prettyPrint();


        //Do Assertion

        GoRestResponseBodyPojo actualPojo= response.as(GoRestResponseBodyPojo.class);

        Assert.assertEquals(200,response.getStatusCode());

        Assert.assertEquals(goRestResponseBodyPojo.getMeta(),actualPojo.getMeta());
        Assert.assertEquals(goRestResponseBodyPojo.getData().getId(),actualPojo.getData().getId());
        Assert.assertEquals(goRestResponseBodyPojo.getData().getName(),actualPojo.getData().getName());
        Assert.assertEquals(goRestResponseBodyPojo.getData().getEmail(),actualPojo.getData().getEmail());
        Assert.assertEquals(goRestResponseBodyPojo.getData().getGender(),actualPojo.getData().getGender());
        Assert.assertEquals(goRestResponseBodyPojo.getData().getStatus(),actualPojo.getData().getStatus());




    }
}
