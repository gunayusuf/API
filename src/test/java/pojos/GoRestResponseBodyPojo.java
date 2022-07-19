package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoRestResponseBodyPojo {

    //Her class bir Datadır GoRestDataPojo dan aldik
    // Object yapmamizin sebebi null olmasi
    //1. Tum keyler privite variable'lar oluturuyoruz
    private Object meta;
    private GoRestDataPojo data;

    //2. Tüm parametrelerle ve paramatresiz constructor olusturuyoruz

    public GoRestResponseBodyPojo(Object meta, GoRestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    public GoRestResponseBodyPojo() {

    }


    //3. Getter ve Setters'larımızı olusturuyoruz

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public GoRestDataPojo getData() {
        return data;
    }

    public void setData(GoRestDataPojo data) {
        this.data = data;
    }


    //4. to String methodumuzu olusturuyoruz


    @Override
    public String toString() {
        return "GoRestResponseBodyPojo{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
