package algos;

import java.util.stream.Stream;

/**
 * Created by AIKuznetsov on 13.11.2017.
 */
public class DoublyLinkedListReverse {

    static class Node {
        int val;
        Node next;
        Node prev;


        static Node of(int... vals) {
            Node start = null, prev = null;
            for (int val : vals) {
                Node curr = new Node(val, null, prev);
                if (start == null)
                    start = curr;
                else
                    prev.next = curr;
                prev = curr;
            }
            return start;
        }

        Node print() {
            System.out.print(val + " ");
            if (next != null)
                next.print();
            else
                System.out.println();
            return this;
        }

        Node reverse() {
            Node next = this.next;
            this.next = this.prev;
            this.prev = next;
            if (next != null)
                return next.reverse();
            return this;
        }

        Node reverseLinear() {
            Node curr = this;
            while (curr.next != null) {
                curr = curr.swap();
            }


            curr.swap();
            return curr;
        }

        Node swap() {
            Node next = this.next;
            this.next = this.prev;
            this.prev = next;
            return next;
        }

        public Node(int val, Node next, Node prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    public static void main(String[] args) {
        Node.of(1, 2, 3, 4, 5).print().reverse().print().reverseLinear().print();
    }
}
