package cz.braza.euler;

public class P41PandigitalPrime {
    public static boolean isNdigitPandigital(String val) {
        int n = val.length();
        if (n > 9) return false; // cannot be pandigital at all
        boolean[] result = new boolean[10];
        result[0] = true; // we want to escape as soon as we can, and zero cannot be there...
        for (int i = n; i < 9; i++)
            result[i + 1] = true; // for N digit pandigitals where N < 9, mark all the higher numbers
        for (int idx = 0; idx < n; idx++) { // we already know the string is exactly 9 characters long
            int digit = Integer.parseInt("" + val.charAt(idx));
            if (result[digit])
                return false;
            else
                result[digit] = true;
        }
        return true;
    }

    public static void main(String[] args) {
        /**
         * Test longs, that is 9digit pandigitals, but it does not find anything, so we can continue with just ints...
         */
        /*
        for (long i = 987654321L; i > 123456789; i--) {
            if (P32PandigitalProducts.isPandigital(String.valueOf(i)) && isPrime(i)) {
                System.out.println(i);
                break;
            }

        }
        */
        for (int i = 87654321; i > 0; i--) {
            if (isNdigitPandigital(String.valueOf(i)) && isPrime(i)) {
                System.out.println(i);
                break;
            }
        }
    }

    private static boolean isPrime(long i) {
        if (i % 2 == 0)
            return false;
        for (int p = 3; p <= Math.sqrt(i); p += 2)
            if (i % p == 0)
                return false;
        return true;
    }
}
