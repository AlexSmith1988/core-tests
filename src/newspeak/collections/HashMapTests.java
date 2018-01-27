package newspeak.collections;

import java.util.HashMap;
import java.util.Objects;

public class HashMapTests {

    public static void main(String[] args) {
        keyChangeTest();
    }

    private static class Entry {
        private String field1;
        private String field2;
        private String field3;

        public Entry(String field1, String field2, String field3) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return Objects.equals(field1, entry.field1) &&
                    Objects.equals(field2, entry.field2) &&
                    Objects.equals(field3, entry.field3);
        }

        @Override
        public int hashCode() {
            return Objects.hash(field1, field2, field3);
        }
    }

    private static void keyChangeTest() {
        HashMap<Entry, String> map = new HashMap<>();
        Entry key = new Entry("1", "2", "3");
        map.put(key, "Hello");
        System.out.println(map.get(new Entry("1", "2", "3")));
        key.field1 = "2";
        System.out.println(map.get(new Entry("1", "2", "3")));
    }
}
