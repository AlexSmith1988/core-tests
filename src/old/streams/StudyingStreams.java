package old.streams;

import java.util.stream.Stream;

/**
 * Created by AIKuznetsov on 21.12.2017.
 */
public class StudyingStreams {

    public static void main(String[] args) {
        Stream<String> sorted = Stream.of("42", "13", "7").sorted();
        System.out.println(sorted.reduce("", (s, s2) -> s + " " + s2));
    }
}
