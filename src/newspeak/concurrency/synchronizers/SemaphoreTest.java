package newspeak.concurrency.synchronizers;

import org.openjdk.jmh.generators.core.SourceThrowableError;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SemaphoreTest {

    private static Semaphore semaphore = new Semaphore(3, true);

    public static void main(String[] args) {
        printOut();
        List<Thread> threads = IntStream.range(0, 10).mapToObj(num -> new Thread(new Worker())).collect(Collectors.toList());
        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static void printOut() {
        System.out.println("Available semaphores: " + semaphore.availablePermits());
    }

    static class Worker implements Runnable {
        static final AtomicInteger count = new AtomicInteger();

        @Override
        public void run() {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                System.out.println("We were interrupted");
            }
            printOut();
            System.out.println("Acquired " + count.incrementAndGet());
            try {
                Thread.currentThread().sleep(300);
            } catch (InterruptedException e) {
                System.out.println("Exception during sleep: " + e.getMessage());
            }
            semaphore.release();
        }
    }
}
