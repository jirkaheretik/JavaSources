package cz.braza.leetcode;

public class L13Roman2Integer {
    public static final String LETTERS = "MDCLXVI";
    public static final int[] VALUES = {1000, 500, 100, 50, 10, 5, 1};

    private static int getCharValue(char c) {
        int pos = LETTERS.indexOf("" + c);
        return pos >= 0 ? VALUES[pos] : 0;
    }

    public static int romanToIntWithFunc(String s) {
        int result = 0;
        int value = getCharValue(s.charAt(0));
        if (s.length() == 1) return value; // so as not to get complex condition at the end for one-letter inputs
        int nextValue = 0;
        for (int index = 1; index < s.length(); index++) {
            nextValue = getCharValue(s.charAt(index));
            result += value * ((nextValue > value) ? -1 : 1);
            value = nextValue;
        }
        return result + nextValue;
    }

    public static int romanToInt(String s) {
        int result = 0;
        int value = VALUES[LETTERS.indexOf("" + s.charAt(0))];
        if (s.length() == 1) return value;
        int nextValue = 0;
        for (int index = 1; index < s.length(); index++) {
            nextValue = VALUES[LETTERS.indexOf("" + s.charAt(index))];
            result += value * (nextValue > value ? -1 : 1);
            value = nextValue;
        }
        return result + nextValue;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("LVIII"));
    }
}
