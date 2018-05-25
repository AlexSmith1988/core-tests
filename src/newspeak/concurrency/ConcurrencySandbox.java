package newspeak.concurrency;

public class ConcurrencySandbox {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread.sleep(-1);
        System.out.println("Done " + (System.currentTimeMillis() - startTime));

    }
}
