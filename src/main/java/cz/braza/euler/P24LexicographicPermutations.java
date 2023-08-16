package cz.braza.euler;

public class P24LexicographicPermutations {
    public static void main(String[] args) {
        //int totalCount = 362880; // should be all the 9digits permutations of 1-9 (thus with a leading zero)
        int totalCount = 0;
        for (long i = 1; i < 9876543210L; i++) {
        //for (long i = 1023456789; i < 9876543210L; i++) {
        // for (int i = 1023456789; i < Integer.MAX_VALUE; i++) { // first attempt, but there are not enough numbers/permutations
            if (isAPermutation(i)) {
                totalCount++;
                if (totalCount == 1000000) {
                    System.out.println("Milionth permutation is a number " + i);
                    return;
                }
            }
        }
        //System.out.println("There are only " + totalCount + " permutations up to MAX_INT");
    }

    private static boolean isAPermutation(long value) {
        boolean[] digits = new boolean[10];
        while (value > 0) {
            int res = (int) (value % 10);
            if (digits[res])
                return false; // second digit of the same value
            digits[res] = true;
            value /= 10;
        }
        // return true; // only if all the numbers are same length
        return digits[1] && digits[2] && digits[3] && digits[4] && digits[5] && digits[6] && digits[7] && digits[8] && digits[9]; // dont care about zero, it may be at the beginning (or not)
    }
}
