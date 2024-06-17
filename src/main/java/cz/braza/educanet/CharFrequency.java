package cz.braza.educanet;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;

public class CharFrequency {
    private Map<Character, Integer> map = new HashMap<>();

    public CharFrequency(String input) {
        map = getFrequencies(input);
    }

    private Map<Character, Integer> getFrequencies(String input) {
        Map<Character, Integer> result = new HashMap<>();
        for (char c: input.toCharArray())
            result.put(c, result.getOrDefault(c, 0) + 1);
        return result;
    }

    public boolean contains(char c) {
        return map.containsKey(c); // pokud nemám nuly v mapě, viz getFrequencies(), toto stačí
    }

    public int charFrequency(char c) {
        return map.getOrDefault(c, 0);
    }

    public char mostFrequentChar(String input) {
        char result = input.charAt(0);
        int max = 0;
        for (char c: input.toCharArray())
            if (map.containsKey(c)) {
                if (map.get(c) > max) {
                    max = map.get(c);
                    result = c;
                }
            }
        return result;
    }

    public int highestFrequency() {
        return map.values().stream().mapToInt(x->x).max().orElse(0);
    }

    public int lowestFrequency() {
        return map.values().stream().mapToInt(x->x).min().orElse(0);
    }


}