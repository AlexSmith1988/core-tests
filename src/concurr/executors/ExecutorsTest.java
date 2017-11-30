package concurr.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by AIKuznetsov on 24.10.2017.
 */
public class ExecutorsTest {

    public static void main(String[] args) {
        System.out.println("Hello, executors");

        ExecutorService service = Executors.newFixedThreadPool(2);


    }
}
