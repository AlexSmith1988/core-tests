package newspeak.concurrency;

import java.util.stream.IntStream;

/**
 * Created by AIKuznetsov on 27.12.2017.
 */
public class FJPtest {

    // Lambdas use
    public static void main(String[] args) {

    }

    static void lambdaForkJoinPoolTest() {
        // Lambdas use ForkJoin.commonPool under the hood
        long start = System.currentTimeMillis();
        IntStream.range(0, 4000).parallel()
                .mapToObj(n -> IntStream.range(n, 1000000 + n))
                .map(intStream -> intStream.filter(n -> n * 5 % 2 == 0).reduce(1, (left, right) -> left * (left + right)))
                .map(integer -> integer >> 30)
                .forEach(System.out::print);
        System.out.println();
        System.out.println("Elapsed: " + (System.currentTimeMillis() - start));
    }

    static void forkJoinPoolTest() {

    }
}
