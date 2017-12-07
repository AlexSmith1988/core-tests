package concurr.basic;

/**
 * Created by AIKuznetsov on 01.12.2017.
 */
public class LockStatusTest {

    static Object monitor = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Do I hold? " + Thread.holdsLock(monitor));
        Thread thread = new Thread() {
            @Override
            public void run() {
                synchronized (monitor) {
                    System.out.println("Do I hold? " + Thread.holdsLock(monitor));
                }
            }
        };
        thread.start();
        thread.join();

        assert 10 == 1;

        System.out.println("Test done");
    }
}
