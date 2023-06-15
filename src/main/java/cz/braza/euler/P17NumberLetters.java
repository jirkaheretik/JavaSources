package cz.braza.euler;

public class P17NumberLetters {
    // "", "one", "two", "three", ... "nineteen":
    public static final int[] SUB20 = { 0, 3, 3, 5, 4, 4, 3, 5, 5, 4, 3, 6, 6, 8, 8, 7, 7, 9, 8, 8 };

    // "", "", "twenty", "thirty", "forty", ... "ninety":
    public static final int[] TENTHS = {0, 0, 6, 6, 5, 5, 5, 7, 6, 6};

    public static int belowHundred(int n) {
        if (n < 20) return SUB20[n];
        return TENTHS[n / 10] + SUB20[n % 10];
    }

    public static int numberLength(int n) {
        if (n < 100) return belowHundred(n);
        int result = 0;
        int hundreds = (n / 100) % 10;
        int thousands = n / 1000;
        int subHundred = n % 100;
        if (n > 999) result += belowHundred(thousands) + 8; // length of "thousand"
        if (hundreds > 0)
            result += SUB20[hundreds] + 7; // length of "hundred"
        if (subHundred > 0)
            result += 3 + belowHundred(subHundred); // 3 == length of "and"
        return result;
    }
    public static void main(String[] args) {
        int MAX = 1000;
        int sum = 0;
        for (int i = 1; i <= MAX; i++)
            sum += numberLength(i);
        System.out.println("Total character length of numbers up to " + MAX + " is " + sum);
    }
}
