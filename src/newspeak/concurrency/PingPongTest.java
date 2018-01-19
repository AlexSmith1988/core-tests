package newspeak.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

public class PingPongTest {

    public static void main(String[] args) throws InterruptedException {
        final PingPong pingPong = new PingPong();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            while (!executorService.isShutdown()) {
                try {
                    pingPong.ping();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.submit(() -> {
            while (!executorService.isShutdown()) {
                try {
                    pingPong.pong();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        sleep(500);
        executorService.shutdown();
    }

    static class PingPong {
        boolean timeToPong;

        synchronized void ping() throws InterruptedException {
            while (!timeToPong) {
                this.wait();
            }
            timeToPong = false;
            System.out.println("Ping");
            this.notify();
        }

        synchronized void pong() throws InterruptedException {
            while (timeToPong) {
                this.wait();
            }
            timeToPong = true;
            System.out.println("Pong");
            this.notify();
        }
    }

}
