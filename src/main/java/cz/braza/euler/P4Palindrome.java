package cz.braza.euler;

public class P4Palindrome {
    public static boolean isPalindrome(int value) {
        String val = String.valueOf(value);
        return val.equals(new StringBuilder(val).reverse().toString());
    }

    public static void main(String[] args) {
        int max = 0;
        for (int i = 999; i > 900; i--)
            for (int j = 999; j > 900; j--) {
                int val = i * j;
                if (val > max && isPalindrome(val))
                    max = val;
            }
        System.out.println("Found max at " + max);
    }
}
