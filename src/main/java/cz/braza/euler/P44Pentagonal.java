package cz.braza.euler;

import java.util.ArrayList;
import java.util.Arrays;

public class P44Pentagonal {
    public static void main(String[] args) {
        int N = 1000000; // soft limit
        int K = 3000; // soft limit, how many numbers apart they can be
        long[] pentas = new long[N];
        for (long i = 1; i < N; i++) {
            pentas[(int)i] = i * (3 * i - 1) / 2;
        }
        System.out.println("Pentagonal numbers done.");
        System.out.println("Largest: " + pentas[N - 1]);
        System.out.println("2nd Largest: " + pentas[N - 2]);
        long minDiff = Long.MAX_VALUE;
        for (int step = 1; step < K; step++)
            for (int i = 2; i < pentas.length - step; i++) {
                long p1 = pentas[i];
                long p2 = pentas[i + step];
                if (Arrays.binarySearch(pentas, p1 + p2) < 0) // if sum is not pentagonal
                    continue;
                System.out.print(".");
                //System.out.println("Found pentagonal numbers " + p1 + " and " + p2 + " with pentagonal sum");
                if (Arrays.binarySearch(pentas, p2 - p1) < 0) // if sum is not pentagonal
                    continue;
                System.out.println("Found pentagonal numbers " + p1 + " and " + p2 + " with pentagonal sum and diff too.");
                if (p2 - p1 < minDiff) {
                    minDiff = p2 - p1;
                    System.out.println("New minimal difference is " + minDiff);
                }
            }
        /*
        for (int i = 1; i < pentas.length; i++)
            for (int j = i + 1; j < Math.min(i + K, pentas.length); j++) {
                /*
                if (i > minDiff)
                    break;

                 /
                if (Arrays.binarySearch(pentas, pentas[i] + pentas[j]) < 0) // if sum is not pentagonal
                    continue;
                if (Arrays.binarySearch(pentas, pentas[i] + 2 * pentas[j]) > 0) {
                    System.out.println("Found " + j + " and " + (i+j));
                    if (pentas[j] - pentas[i] < minDiff)
                        minDiff = pentas[j] - pentas[i];
                }
            }

         */
        System.out.println("\nMinimal difference of Pk-Pj is " + minDiff);
    }
}
