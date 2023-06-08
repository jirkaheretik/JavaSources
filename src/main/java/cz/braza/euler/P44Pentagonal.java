package cz.braza.euler;

import java.util.ArrayList;
import java.util.Arrays;

public class P44Pentagonal {
    public static void main(String[] args) {
        int N = 30000; // soft limit
        int K = 3000; // soft limit, how many numbers apart they can be
        long[] pentas = new long[N];
        for (long i = 1; i < N; i++) {
            pentas[(int)i] = i * (3 * i - 1) / 2;
        }
        System.out.println("Pentagonal numbers done.");
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < pentas.length; i++)
            for (int j = i + 1; j < Math.min(i + K, pentas.length); j++) {
                if (i > minDiff)
                    break;
                if (Arrays.binarySearch(pentas, pentas[i] + pentas[j]) < 0) // if sum is not pentagonal
                    continue;
                if (Arrays.binarySearch(pentas, pentas[i] + 2 * pentas[j]) > 0) {
                    System.out.println("Found " + j + " and " + (i+j));
                    minDiff = i;
                }
            }
        System.out.println("Minimal difference of Pk-Pj is " + minDiff);
    }
}
