package concurr.executors;

import java.util.concurrent.*;

/**
 * Created by AIKuznetsov on 24.10.2017.
 */
public class ExecutorsTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Hello, executors");

        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> result = service.submit(() -> {throw new RuntimeException("Oppa gangnam style");});

        service.execute(() -> {
            throw new RuntimeException("Runnable exception");
        });

        Thread.sleep(500);

        service.execute(() -> System.out.println(Thread.currentThread().getName()));
        service.execute(() -> System.out.println(Thread.currentThread().getName()));
        service.execute(() -> System.out.println(Thread.currentThread().getName()));
        service.execute(() -> System.out.println(Thread.currentThread().getName()));

        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);

//        System.out.println(result.get());
        System.out.println("All done");

    }
}
