package concurr.completabeFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Created by AIKuznetsov on 01.12.2017.
 */
public class CompletableFuturesTest {

    public static void main(String[] args) {
        CompletableFuture
                .supplyAsync(CompletableFuturesTest::supply)
                .thenApply(CompletableFuturesTest::applyCat)
                .thenAccept(CompletableFuturesTest::acceptAnimals)
                .join();
    }

    private static void acceptAnimals(List<String> strings) {
        System.out.println("acceptAnimals : " + strings);
    }

    private static List<String> applyCat(Integer number) {
        sleep(200);
        System.out.println("apply Cat " + number);
        return IntStream
                .range(0, number)
                .mapToObj(x -> "Cat " + ++x)
                .collect(toList());
    }

    private static Integer supply() {
        sleep(400);
        System.out.println("supply awoke");

        return 2;
    }

    private static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
