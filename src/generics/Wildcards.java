package generics;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by AIKuznetsov on 30.10.2017.
 */
public class Wildcards {

    static class Key {
        int val;

        public Key(int val) {
            this.val = val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            return val == key.val;

        }

        @Override
        public int hashCode() {
            return 10;
        }
    }

    public static void main(String[] args) throws Throwable {
        HashMap<Key, String> testMap= new HashMap<>();
        for (int i = 0; i< 16; ++i)
            testMap.put(new Key(i), "Test" + i);

        System.out.println(testMap.get(new Key(9)));
        testMap.put(new Key(9), "Fuck yes");
        System.out.println(testMap.get(new Key(9)));


        try {
            Wildcards.class.getDeclaredMethod("some").invoke(new Wildcards());
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }


        int MAXIMUM_CAPACITY = 1 << 30;

        int cap = 129;

        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println("Calculated threshold: " + ((n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1));

        List<Integer> vals = new ArrayList<>();
        List<Number> valsNum = new ArrayList<>();
        List<Object> valsObj = new ArrayList<>();


        Foo foo = new Foo();
        Object fooObj = foo;
        Serializable fooObj1 = (Wildcards & Serializable) fooObj;

        System.out.println(fooObj1);


        create(vals);
        create(valsNum);
        create(valsObj);

        sum(valsNum);
        sum(vals);

        System.out.println("---------");

        List<Double> valsD = new ArrayList<>();
        valsD.add(1.0);
        valsD.add(42.5);

        sum(valsD);

    }

    static void sum(List<? extends Number> vals) {
        double result = 0.0d;

        for (Number n : vals) {
            result += n.doubleValue();
        }

        System.out.println("Sum: " + result);
    }

    static void create(List<? super Integer> dest) {
        dest.add(10);
        dest.add(5);
    }
}

class Foo implements Serializable, Runnable {

    @Override
    public void run() {

    }
}


