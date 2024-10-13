package cz.braza.leetcode;

public class L9Palindrome {
    public static boolean isPalindrome(int x) {
        String val = String.valueOf(x);
        return val.equals(new StringBuilder(val).reverse().toString());
    }

    public static boolean isPalindromeNoStrings(int x) {
        if (x < 0) return false;
        if (x == 0) return true; // log10 solution needs x > 0
        int[] digits = new int[(int)(Math.log10(x)+1)];
        int digit = 0;
        while (x > 0) {
            digits[digit++] = x % 10;
            x /= 10;
        }
        // now check palindrome:
        for (int i = 0; i < digits.length / 2; i++)
            if (digits[i] != digits[digits.length - 1 - i])
                return false;
        return true;
    }
}
