package cz.braza;

import java.util.Map;
/**
 * Class representing Roman Numerals including the rules to compose them.
 * Terminology:
 * Valid - follows the rules (including XVIIIIII)
 * Efficient - valid & shortest
 * Invalid - breaks at least one rule
 */
public class Roman {
    public static final int M = 1000;
    public static final int D = 500;
    public static final int C = 100;
    public static final int L = 50;
    public static final int X = 10;
    public static final int V = 5;
    public static final int I = 1;
    public static final String ALLOWED = "MDCLXVI";
    public static final String ALLOWED_SUBTRACT = "CXI";
    public static final int[] VALS = {1000, 500, 100, 50, 10, 5, 1};

    public static final Map<String, Integer> VALUES = Map.of("M", 1000, "D", 500, "C", 100, "L", 50, "X", 10, "V", 5, "I", 1);
    /**
     * Factory method creating a Roman numeral instance from a String representation
     * @param input
     * @return
     */

    private int value;
    private String initialString;
    private String normalizedString;

    public Roman(int value, String initialString) {
        this.value = value;
        this.initialString = initialString;
        normalizedString = createRomanNumeral(value);
    }
    public Roman(int value) {
        this(value, createRomanNumeral(value));
    }
    public static Roman of(String input) {
        int value = 0;
        for (char c : input.trim().toUpperCase().toCharArray()) {
            String key = c + "";
            if (VALUES.containsKey(key))
                value += VALUES.get(key);
        }
        return new Roman(value, input);
    }

    public static Roman of(int value) {
        return new Roman(value);
    }

    public static String createRomanNumeral(int value) {
        String result = "";
        for (char c : ALLOWED.toCharArray()) {
            int val = VALUES.get(c + "");
            while (value >= val) {
                result = result + c;
                value -= val;
            }
        }
        return result;
    }

    public int getIntValue() {
        return value;
    }

    /**
     * Checks whether a given string is valid roman numeral
     * @param number
     * @return
     */
    public static boolean isValid(String number) {
        return true; // TODO
    }

    /**
     * Checks whether a given string is the most efficient way of writing a given numeral
     * @param number
     * @return
     */
    public static boolean isEfficient(String number) {
        return isValid(number) && (of(number).toString()==number);
    }

    public String toString() {
        return normalizedString;
    }

    public static int romanToInt(String s) {
        int result = 0;
        int value = VALS[ALLOWED.indexOf("" + s.charAt(0))];
        if (s.length() == 1) return value;
        int nextValue = 0;
        for (int index = 1; index < s.length(); index++) {
            nextValue = VALS[ALLOWED.indexOf("" + s.charAt(index))];
            result += value * (nextValue > value ? -1 : 1);
            value = nextValue;
        }
        return result + nextValue;
    }

    public static String intToRoman(int val) {
        StringBuilder result = new StringBuilder();
        // thousands:
        for (int i = 0; i < val / 1000; i++)
            result.append('M');
        val %= 1000;
        // hundreds:
        if (val > 500) {
            result.append('D');
            val -= 500;
        }
        for (int i = 0; i < val / 100; i++)
            result.append('C');
        val %= 100;
        // tens:


        return result.toString();
    }

    public static void main(String[] args) {
        // tests:
        int[] numbers = {24, 11, 19, 8, 5555, 256};
        for (int i : numbers)
            System.out.println(i + " --- " + Roman.of(i));
    }
}
