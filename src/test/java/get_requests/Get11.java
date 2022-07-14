package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {
    /*
    Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Aalok Acharya DDS","Chidambaram Gill","Atmananda Gowda" are among the users
        And
            The female users are more than or equal to male users
     */

    @Test
    public void get01() {
        //1.Step: Set the Url
        spec.pathParam("first","users");

        //2. Step: Set the Expected Data (Karmasik yapida oldugu icin atladik)

        //3. Step: Send the req and Get the Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();


        //4. Step: Do Assertion

        response.
                then().
                assertThat().
                statusCode(200).
                body("meta.pagination.limit",equalTo(10),
                        "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data.id",hasSize(10),
                        "data.status",hasItem("active"),"data.name",hasItems("Aalok Acharya DDS","Chidambaram Gill","Atmananda Gowda"));


        //Bayan ve Erkek sayisini karsilastiralim
        //1.YOL: Tum cinsiyetleri cekip bayan sayisi ile karsilastirdik
        JsonPath jsonPath=response.jsonPath();
      List<String> genders = jsonPath.getList("data.gender");
        System.out.println(genders);

        int numberOfFamales=0;
        for (String w:genders
             ) {
            if (w.equalsIgnoreCase("female")){
                numberOfFamales++;
            }
        }
        System.out.println(numberOfFamales); // 6
        assertTrue(numberOfFamales > genders.size()-numberOfFamales);

        //2.YOL: Tum Bayan ve Erkekleri ayri ayri Groovy ile cekelim
        List<String> femaleList = jsonPath.getList("data.findAll{it.gender=='female'}.gender");
        System.out.println(femaleList);

        List<String> maleList = jsonPath.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println(maleList);

        assertTrue(femaleList.size()>maleList.size());


    }
}
