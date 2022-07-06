import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get04 extends BaseUrls{

    /*
        Given
            https://jsonplaceholder.typicode.com/todos
        When
	 	    I send a GET request to the Url
	    And
	        Accept type is “application/json”
	    Then
	        HTTP Status Code should be 200
	    And
	        Response format should be "application/json"
	    And
	        There should be 200 todos
	    And
	        "quis eius est sint explicabo" should be one of the todos title
	    And
	        2, 7, and 9 should be among the userIds
     */

    @Test
    public void get01() {

        //1 Step: Set the Url
        spec.pathParam("first","todos");

        //2 Step: Set the expected data (post-put-patch)

        //3 Step: Set the request  and get response
        // Taskte gonderecegimiz datanin json formatinda oldugunu istedigi icin
        // specten sonra accept() methodunu kullandik ve icerisine formatin turunu yazdik
        //Accept methodunu gonderirken kullaniyoruz
        Response response=given().spec(spec).when().accept(ContentType.JSON).get("/{first}");


        //	  And      There should be 200 todos
        // kac tane todos oldugunu gormek icin yazdiralim
        response.prettyPrint();


        //4 Step: Do Assertion
        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON).
                body("id", hasSize(200),
                        "title",hasItem("quis eius est sint explicabo")
                ,"userId",hasItems(2,7,9));






        //hasSize() methodu hamcrest'den cagirdik otomatik olarak gelmiyor sayisini,uzunlugunu buluyor
        // hasItem() methodu contains ile ayni islevi goruyor hamcrest'den cagirdik
        // hasItems() methodu containsAll ile ayni islevi goruyor hamcrest'den cagirdik Bu method ise cogullar icin gecerli

    }
}
