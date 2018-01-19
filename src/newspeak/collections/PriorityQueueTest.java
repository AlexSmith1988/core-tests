package newspeak.collections;

import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * Created by AIKuznetsov on 12.10.2017.
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b > a ? -1 : b.equals(a) ? 0 : 1);

        IntStream.range(0, 1000).forEach(val -> {
            queue.add(val);
            if (queue.size() > 10)
                queue.poll();
        });

        for (Integer val;
             (val = queue.poll()) != null; ) {
            System.out.println(val);
        }
    }

}
