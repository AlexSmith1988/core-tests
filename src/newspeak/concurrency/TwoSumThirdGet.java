package newspeak.concurrency;

import java.util.concurrent.*;

import static java.lang.Thread.*;

/**
 * Created by AIKuznetsov on 13.11.2017.
 */
public class TwoSumThirdGet {

    public static void main(String[] args) throws InterruptedException {

        class SubTask extends Thread {

            private long result;

            @Override
            public void run() {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("SubTask threw: " + e.getMessage());
                }
                result = 42;
            }

            public long getResult() {
                return result;
            }
        }

        class Task extends Thread {

            private final SubTask subtask1, subtask2;

            Task(SubTask subTask1, SubTask subTask2) {
                this.subtask1 = subTask1;
                this.subtask2 = subTask2;
                subTask1.run();
                subTask2.run();
            }

            @Override
            public void run() {
                try {
                    subtask1.join();
                    subtask2.join();
                } catch (InterruptedException e) {
                    System.out.println("Main task threw " + e.getMessage());
                }

                System.out.println(subtask1.getResult() + subtask2.getResult());
            }
        }

        Task task = new Task(new SubTask(), new SubTask());
        task.start();
        task.join();

        // Executors Service style
        final ExecutorService service = Executors.newFixedThreadPool(2);
        Callable<Integer> subTask = () -> {
            try {
                sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Sleep threw interruption exception " + e.getMessage());
            }
            return 42;
        };
        final Future<Integer> future1 = service.submit(subTask);
        final Future<Integer> future2 = service.submit(subTask);

        service.submit(() -> {
            try {
                System.out.println("Sum result executor style: " + (future1.get() + future2.get()));
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Exception waiting on Futures: " + e.getMessage());
            }
        });

        service.shutdown();
        System.out.println("Shutdown request is sent");
        service.awaitTermination(1, TimeUnit.MINUTES);
        System.out.printf("We are finished now");
    }
}

