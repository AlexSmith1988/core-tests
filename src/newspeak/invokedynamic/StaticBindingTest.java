package newspeak.invokedynamic;

/**
 * Created by AIKuznetsov on 27.11.2017.
 */
public class StaticBindingTest {

    static void execute() {
        System.out.println("Hello World");
    }

    public static void main(String[] args) {
        execute();
        new StaticBindingTest().call();
    }

    public void call() {
        System.out.println("Hello virtual");
    }
}
