package hashtree.firstOccurenceOfUniqueCharacter;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by ilya on 15.11.2016.
 */
public class FirstUniqueEntry {

    public static void main(String[] args) {
        final String[] inputs = {
                "abcdehabcedkajhsladjlkadjlaksjjsjhlkjslkjkgjhlkjhakjhflkjadlkjhklda",
                "aaabbc",
                "aaawad",
                "aabbccdd"};

        Stream.of(inputs).forEach(FirstUniqueEntry::occurrence);
        Stream.of(inputs).forEach(FirstUniqueEntry::firstUniqueEntry);
    }

    static void occurrence(String input) {
        new FirstUniqueEntry(input).occurrence();
    }

    static void firstUniqueEntry(String input) {
        new FirstUniqueEntry(input).firstUniqueEntry();
    }

    private final String input;

    private final Map<Character, NavigableSet<Integer>> charsPositionsInInput = new HashMap<>();

    FirstUniqueEntry(String input) {
        this.input = input;
    }

    private void occurrence() {
        for (int i = 0; i < input.length(); i++) {
            addCharPosition(i);
        }

        final TreeSet<Map.Entry<Character, NavigableSet<Integer>>> sortedChars = new TreeSet<>(
                (left, right) -> {
                    final NavigableSet<Integer> oneCharPositions = left.getValue();
                    final int oneCharPositionsCount = oneCharPositions.size();

                    final NavigableSet<Integer> anotherCharPositions = right.getValue();
                    final int anotherCharPositionsCount = anotherCharPositions.size();

                    if (oneCharPositionsCount != anotherCharPositionsCount) {
                        return oneCharPositionsCount - anotherCharPositionsCount;
                    }

                    return oneCharPositions.first() - anotherCharPositions.first();
                });

        sortedChars.addAll(charsPositionsInInput.entrySet());

        Map.Entry<Character, NavigableSet<Integer>> charWithMaxCharacteristics = sortedChars.first();
        System.out.printf("1st nr for %s:" + charWithMaxCharacteristics.getKey() + ", index:" + charWithMaxCharacteristics.getValue().first() + "\n", input);
    }

    private void addCharPosition(int charPosition) {
        final Character currentChar = input.charAt(charPosition);
        if (!charsPositionsInInput.containsKey(currentChar)) {
            charsPositionsInInput.put(currentChar, new TreeSet<>());
        }
        charsPositionsInInput.get(currentChar).add(charPosition);
    }

    final Map<Character, Integer> uniqueCharsPositions = new HashMap<>();
    final Set<Character> nonUnique = new HashSet<>();

    private void firstUniqueEntry() {
        pickUniqueCharsWithPositions();

        if (!uniqueCharsPositions.isEmpty()) {
            Map.Entry<Character, Integer> firstUniqueCharEntry = pickFirstUnique();

            System.out.println("For line " + input +
                    " first unique character is " + firstUniqueCharEntry.getKey() +
                    " at position " + firstUniqueCharEntry.getValue());
        } else {
            System.out.println("Line " + input + " has no unique characters");
        }
    }

    private void pickUniqueCharsWithPositions() {
        for (int position = 0; position < input.length(); ++position) {
            tryPickUniqueCharFromPosition(position);
        }
    }

    private void tryPickUniqueCharFromPosition(int position) {
        final char curr = input.charAt(position);
        if (potentiallyUnique(curr)) {
            tryPickAsUnique(position, curr);
        }
    }

    private boolean potentiallyUnique(char currentChar) {
        return !nonUnique.contains(currentChar);
    }

    private void tryPickAsUnique(int position, char currentChar) {
        if (markedUnique(currentChar)) {
            markNonUnique(currentChar);
        } else {
            markUnique(position, currentChar);
        }
    }

    private boolean markedUnique(char currentChar) {
        return uniqueCharsPositions.containsKey(currentChar);
    }

    private void markNonUnique(char currentChar) {
        uniqueCharsPositions.remove(currentChar);
        nonUnique.add(currentChar);
    }

    private Integer markUnique(int position, char currentChar) {
        return uniqueCharsPositions.put(currentChar, position);
    }

    private Map.Entry<Character, Integer> pickFirstUnique() {
        return Collections.min(uniqueCharsPositions.entrySet(),
                (charPosition1, charPosition2) -> {
                    Integer leftCharPosition = charPosition1.getValue();
                    Integer rightCharPosition = charPosition2.getValue();

                    if (leftCharPosition == rightCharPosition)
                        return 0;

                    if (leftCharPosition == null)
                        return -1;

                    if (rightCharPosition == null)
                        return 1;

                    return leftCharPosition.compareTo(rightCharPosition);
                }
        );
    }

}
