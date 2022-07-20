package get_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

public class Get16 extends DummyRestApiBaseUrl {
    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */


    @Test
    public void get01() {
        //Set the Url
        spec.pathParam("pp1","employees");

        //Set the Expected Data



        //Send the GET request and GET the Response
      Response response = given().spec(spec).contentType(ContentType.JSON).when().get("/{pp1}");
      response.prettyPrint();

        //Do Assertion
        //i) Status code is 200

        response.then().
                assertThat().
                statusCode(200).//i) Status code is 200
                body("data.id",hasSize(24),//ii) There are 24 employees
                "data.employee_name",hasItems("Tiger Nixon","Garrett Winters")); //iii) "Tiger Nixon" and "Garrett Winters" are among the employees

        //iv) The greatest age is 66

        JsonPath jsonPath=response.jsonPath();
       List<Integer>  ageList = jsonPath.getList("data.findAll{it.id>0}.employee_age");
        System.out.println(ageList);

        Collections.sort(ageList);
        System.out.println(ageList);
    }
}
