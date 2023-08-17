package cz.braza.euler;

public class P36DoubleBasePalindromes {
    public static boolean isPalindrome(int value) {
        String val = String.valueOf(value);
        return val.equals(new StringBuilder(val).reverse().toString());
    }

    public static boolean isBinaryPalindrome(int value) {
        String val = Integer.toBinaryString(value);
        return val.equals(new StringBuilder(val).reverse().toString());
    }
    public static void main(String[] args) {
        int LIMIT = 1000000;
        long dblPalindromesSum = 0;
        for (int i = 1; i < LIMIT; i++) {
            if (isPalindrome(i) && isBinaryPalindrome(i)) {
                System.out.println(i + " --- " + Integer.toBinaryString(i));
                dblPalindromesSum += i;
            }
        }
        System.out.println("Sum of double based palindromes: " + dblPalindromesSum);
    }
}
