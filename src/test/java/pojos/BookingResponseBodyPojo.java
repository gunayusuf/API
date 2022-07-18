package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

//codehaus repodan geliyor pom icinde ekli
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponseBodyPojo {

    /*
    1) Tum Keyler icin private variable 'lar olusturulur.
    2)  Tum parametreleri ve parametresiz constructor'larimizi olustururuz.
    3) Getters ve Setterlar olusturulur.
    4) toString() methodumuzu olustururuz.
     */

    //1) Tum Keyler icin private variable 'lar olusturulur.
    private Integer bookingid;
    private BookingPojo booking;

    //2)  Tum parametreleri ve parametresiz constructor'larimizi olustururuz.


    public BookingResponseBodyPojo(Integer bookingid, BookingPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public BookingResponseBodyPojo() {
    }

    //3) Getters ve Setterlar olusturulur.

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }


    //4) toString() methodumuzu olustururuz.

    @Override
    public String toString() {
        return "BookingResponseBodyPojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
