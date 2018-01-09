package newspeak.collections;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * Created by AIKuznetsov on 12.10.2017.
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.add("1");
        queue.add("3");
        queue.add("2");

        while(!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

        System.out.println("---------");

        TreeSet<Integer> set = new TreeSet<>();
        set.add(4);
        set.add(3);
        set.add(2);
        set.add(1);

        set.stream().forEach(System.out::println);
//        System.out.println(set);

        System.out.println("---------");

        HashMap<String, String> test = new HashMap<>();
        test.put(null, "test");
        System.out.println(test.get(null));
    }

}
