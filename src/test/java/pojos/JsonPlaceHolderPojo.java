package pojos;

//codehaus repodan geliyor pom icinde ekli
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true) //Default'u false
//Farkli key-value ikililerin uyusmazligini @JsonIgnoreProperties(ignoreUnknown = true) //(Default'u false)
// annotation'ini Pojo Class'imizin basina yazarak cozebiliriz
// bizim karsilastirmak datalarin key-valuesinin
// istediklerimiz disinda gelen body icerisinde farkli bir key-value varsa
// bu sayede gormezden gelecek.
public class JsonPlaceHolderPojo {
    /*
    1) Tum Keyler icin private variable 'lar olusturulur.
    2)  Tum parametreleri ve parametresiz constructor'larimizi olustururuz.
    3) Getters ve Setterlar olusturulur.
    4) toString() methodumuzu olustururuz.
     */

    //1) Tum Keyler icin private variable 'lar olusturulur.
        private Integer userId;
        private String title;
        private Boolean completed;

    //2)  Tum parametreleri ve parametresiz constructor'larimizi olustururuz.
    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }
    //Default Constructor
    public JsonPlaceHolderPojo() {
    }


    //3) Getters ve Setterlar olusturulur.


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    //4) toString() methodumuzu olustururuz.


    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }

    //Farkli key-value ikililerin uyusmazligini @JsonIgnoreProperties(ignoreUnknown = true) //(Default'u false)
    // annotation'ini Pojo Class'imizin basina yazarak cozebiliriz
    // bizim karsilastirmak datalarin key-valuesinin
    // istediklerimiz disinda gelen body icerisinde farkli bir key-value varsa
    // bu sayede gormezden gelecek.
}
