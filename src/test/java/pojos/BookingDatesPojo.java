package pojos;

//codehaus repodan geliyor pom icinde ekli
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDatesPojo {

    /*
    1) Tum Keyler icin private variable 'lar olusturulur.
    2)  Tum parametreleri ve parametresiz constructor'larimizi olustururuz.
    3) Getters ve Setterlar olusturulur.
    4) toString() methodumuzu olustururuz.
     */

    //1) Tum Keyler icin private variable 'lar olusturulur.
    private String checkin;
    private String checkout;

    // 2)  Tum parametreleri ve parametresiz constructor'larimizi olustururuz.


    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingDatesPojo() {

    }

    //3) Getters ve Setterlar olusturulur.


    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    //4) toString() methodumuzu olustururuz.


    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
