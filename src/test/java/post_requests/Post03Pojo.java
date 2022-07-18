package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Post03Pojo extends JsonPlaceHolderBaseUrl {
    /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void postPojo01() {
        //1.Step: Set the Url
        spec.pathParam("pp1","todos");

        //2.Step: Set the expectedData
        JsonPlaceHolderPojo requestBody= new JsonPlaceHolderPojo(55,"Tidy your room",false);

        //3.Step: Send POST Request, and get the Response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{pp1}");
        response.prettyPrint();


        //4.Step: Do Assertion

       JsonPlaceHolderPojo actualBody = response.as(JsonPlaceHolderPojo.class);

        assertEquals(requestBody.getUserId(),actualBody.getUserId());
        assertEquals(requestBody.getTitle(),actualBody.getTitle());
        assertEquals(requestBody.getCompleted(),actualBody.getCompleted());

        //Bu sekilde de assertion yapabiliriz
        //assertEquals(requestBody.toString(),actualBody.toString());













    }
}
