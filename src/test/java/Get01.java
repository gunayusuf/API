import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {

    /*
    1-Postman manuel API testi icin kullanilir.
    2-API otomasyon testi icin Rest-Assured Library kullaniyoruz.
    3-Otomasyon kodlarinin yazimi icin su adimlari izliyoruz:
        a) Gereksinimleri anlama
        b) Test case yazimi
            - Test case yazimi icin 'Gherkin Language' kullaniyoruz
                'Gherkin' bazi keywordlere sahip, bunlar ;
                x) Given: On kosullar
                y) When: Aksiyonlar --> Get,Put...
                z) Then: Ciktilar-Donutler /Daha sonra anlami/Output>>Dogrulama,Response
                t) And: Coklu islemler icin kullanilir.

        c) Testing kodunun Yazimi

            i) Set the URL / URL kurulumu
            ii) Set the expected data(POST-PUT-PATCH) /Beklenen data kurulumu
            iii) Type code to send request / talep gonderme icin kod yazimi
            IV) Do Assertion / Beklenen data ile elimizdeki datalar karsilastirilir
     */



    /*
    Given
            https://restful-booker.herokuapp.com/booking/55
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */

    @Test
    public void get01(){

        //i) Set the URL / URL kurulumu
        String url = "https://restful-booker.herokuapp.com/booking/555";

        //ii) Set the expected data(POST-PUT-PATCH) /Beklenen data kurulumu
        //Get yapacagimiz icin atladik bu adimi


        //iii) Type code to send request / talep gonderme icin kod yazimi
        Response response=given().when().get(url);
  //      response.prettyPrint(); // sout ile olmuyor yazdirmak icin bunu kullaniyoruz



        //IV) Do Assertion / Beklenen data ile elimizdeki datalar karsilastirilir
        response.then().assertThat().statusCode(200).contentType("application/JSON").statusLine("HTTP/1.1 200 OK");

        //NOT;
        //response.then().assertThat().statusCode(400).
        // contentType("application/json").statusLine("HTTP/1.1 200 OK");
        // status code u 400 yapınca
        // VEYA application/xml yapinca da hata veriyor
        // java.lang.AssertionError: 1 expectation failed.
        //Expected status code <400> but was <200>.  hatasını veriyor

        // 'Status Code' nasil yazdirilir ?
        System.out.println("Status Code ==> "+response.statusCode());

        // 'Content Type' nasil yazdirilir ?
        System.out.println("Content Type ==> "+response.contentType());

        // 'Status Line' nasil yazdirilir ?
        System.out.println("Status Line ==> " + response.statusLine());

        // 'Header' nasil yazdirilir ?

        System.out.println(response.header("Connection"));

        // 'Headers' nasil yazdirilir ?
        System.out.println("Headers ==> \n"+response.headers());
      //  System.out.println("Headers asList ==>\n"+response.headers().asList());


        // 'Time' nasil yazdirilir ? // test suresi
        System.out.println("Time : "+response.getTime());


    }


}
