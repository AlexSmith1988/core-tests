package old;

/**
 * Created by AIKuznetsov on 30.10.2017.
 */
public class HappensBefore {

    public static void main(String[] args) {


        Foo test = new Foo(42, "Test");

        System.out.println(test.normal);

    }
}

class Foo {
    int normal;
    final String finalField;

    public Foo(int normal, String finalField) {
        this.normal = normal;
        this.finalField = finalField;
    }
}
