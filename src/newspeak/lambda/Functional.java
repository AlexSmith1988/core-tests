package newspeak.lambda;

/**
 * Created by AIKuznetsov on 20.12.2017.
 */
public class Functional {

    public static void main(String[] args) {
        System.out.println("Test");
    }
}


@FunctionalInterface
interface Foo {

    void doSome();

    // void breaksCompilation();

    default void oops() {
        System.out.println("All good my friend");
    }
}
