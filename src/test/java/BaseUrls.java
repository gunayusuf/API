import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseUrls {

    protected RequestSpecification spec;

    //@Before annotation'i kullandigimiz methodlar her Test metodundan once calistirilir
    @Before
    public void setUp(){

        spec = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();

    }




}
