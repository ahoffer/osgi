package funservice;

// The implementation of a service interface. This is essentially a bean that implements
// an interface.
public class Fun implements FunService {

    private String someProperty;

    public String getSomeProperty() {
        return someProperty;
    }

    public void setSomeProperty(String someProperty) {
        this.someProperty = someProperty;
    }

    public void speak() {
        System.out.println("Speaking " + getSomeProperty());
    }
}
