package newspeak.lambda;

import java.util.stream.IntStream;

public class AllOpeartions {

    public static void main(String[] args) {
        System.out.println(
                IntStream.range(0, 1000).sorted().sum());
    }
}
