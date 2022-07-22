package put_request;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.DummyApiResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Put02 extends DummyRestApiBaseUrl {
    /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */

    /*
    Typing Gherkin Language
    Given
        https://dummy.restapiexample.com/api/v1/update/21
    And
        {
          "employee_name": "Tom Hanks",
          "employee_salary": 111111,
          "employee_age": 23,
          "profile_image": "Perfect image"
         }
    When
        User sends the PUT Request
    Then
           Status code is 200
    And
           {
             "status": "success",
             "data": {
                 "employee_name": "Tom Hanks",
                 "employee_salary": 111111,
                 "employee_age": 23,
                 "profile_image": "Perfect image"
             },
             "message": "Successfully! Record has been updated."
           }
     */

    @Test
    public void put02() {

        //Set the Url
       spec.pathParams("pp1","update","pp2",21);

       //Set the Expected Data
        DummyApiDataPojo dummyApiDataPojo=new DummyApiDataPojo("Ali Can",111111,23,"Perfect image");
        DummyApiResponseBodyPojo expectedData=new DummyApiResponseBodyPojo("success",dummyApiDataPojo,"Successfully! Record has been updated.");

        //Send the PUT Request
        Response response = given().spec(spec).contentType(ContentType.JSON).body(dummyApiDataPojo).when().put("/{pp1}/{pp2}");

        DummyApiResponseBodyPojo actualData = JsonUtil.convertJsonToJavaObject(response.asString(),DummyApiResponseBodyPojo.class);

        //Do Assertion
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getMessage(),actualData.getMessage());
        assertEquals(expectedData.getData().getEmployee_name(),actualData.getData().getEmployee_name());
        assertEquals(expectedData.getData().getEmployee_salary(),actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getData().getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(expectedData.getData().getProfile_image(),actualData.getData().getProfile_image());





    }
}
