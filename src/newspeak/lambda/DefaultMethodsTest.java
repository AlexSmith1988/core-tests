package newspeak.lambda;

/**
 * Created by AIKuznetsov on 22.01.2018.
 */
public class DefaultMethodsTest {

    public static void main(String[] args) {
        System.out.println(new Some(){}.hashCode());
    }
}

interface Some {
//    Not possible
//    @Override
//    default int hashCode() {
//        return 42;
//    }
}
