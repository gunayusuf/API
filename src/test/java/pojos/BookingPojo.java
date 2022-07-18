package pojos;

//codehaus repodan geliyor pom icinde ekli
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingPojo {
    /*
    1) Tum Keyler icin private variable 'lar olusturulur.
    2)  Tum parametreleri ve parametresiz constructor'larimizi olustururuz.
    3) Getters ve Setterlar olusturulur.
    4) toString() methodumuzu olustururuz.
     */

    //"booking" :{
    //       "firstname": "Ali",
    //        "lastname": "Can",
    //        "totalprice": 999,
    //        "depositpaid": true,
    //        "bookingdates": {
    //            "checkin": "2021-09-21",
    //            "checkout": "2021-12-21"
    //                  }




    //1) Tum Keyler icin private variable 'lar olusturulur.
    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private BookingDatesPojo bookingdates;
    private String additionalneeds;

    //2)  Tum parametreleri ve parametresiz constructor'larimizi olustururuz.


    public BookingPojo(String firstname, String lastname, Integer totalprice, Boolean depositpaid, BookingDatesPojo bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }

    public BookingPojo() {
    }

    //3) Getters ve Setterlar olusturulur.

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    public Boolean getDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDatesPojo getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDatesPojo bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    //4) toString() methodumuzu olustururuz.


    @Override
    public String toString() {
        return "BookingPojo{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingdates=" + bookingdates +
                ", additionalneeds='" + additionalneeds + '\'' +
                '}';
    }
}
