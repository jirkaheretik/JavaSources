package cz.braza.euler;

/**
 * Pandigitals (all 0-9 digits)
 * d2d3d4 (2nd, 3rd and 4th digit) is divisible by 2 (X2)
 * d3d4d5 divisible by 3 (X3)
 * d4d5d6 divisible by 5 (X5)
 * d5d6d7 divisible by 7 (X7)
 * d6d7d8 divisible by 11 (X11)
 * d7d8d9 divisible by 13 (X13)
 * d8d9d10 divisible by 17 (X17)
 */
public class P43SubstringPandigitals {
    public static void main(String[] args) {
        long sum = 0;
        for (int d1 = 0; d1 < 10; d1++) {
            for (int d2 = 0; d2 < 10; d2++) {
                if (d2 == d1) continue;
                for (int d3 = 0; d3 < 10; d3++) {
                    if (d3 == d1 || d3 == d2) continue;
                    for (int d4 = 0; d4 < 10; d4 += 2) { // must be even (X2)
                        if (d4 == d3 || d4 == d2 || d4 == d1) continue;
                        for (int d5 = 0; d5 < 10; d5++) {
                            if (d5 == d4 || d5 == d3 || d5 == d2 || d5 == d1) continue;
                            if ((d5 + d4 + d3) % 3 != 0) continue; // divisible by three (X3)
                            for (int d6 = 0; d6 < 10; d6 += 5) { // divisible by 5
                                if (d6== d5 || d6 == d4 || d6 == d3 || d6 == d2 || d6 == d1) continue;
                                for (int d7 = 0; d7 < 10; d7++) {
                                    if (d7 == d6 || d7 == d5 || d7 == d4 || d7 == d3 || d7 == d2 || d7 == d1) continue;
                                    if ((d5 * 100 + d6 * 10 + d7) % 7 != 0) continue; // X7
                                    for (int d8 = 0; d8 < 10; d8++) {
                                        if (d8 == d7 || d8 == d6 || d8 == d5 || d8 == d4 || d8 == d3 || d8 == d2 || d8 == d1) continue;
                                        if ((d6 * 100 + d7 * 10 + d8) % 11 != 0) continue; // X11
                                        for (int d9 = 0; d9 < 10; d9++) {
                                            if (d9 == d8 || d9 == d7 || d9 == d6 || d9 == d5 || d9 == d4 || d9 == d3 || d9 == d2 || d9 == d1) continue;
                                            if ((d7 * 100 + d8 * 10 + d9) % 13 != 0) continue; // X13
                                            for (int dx = 0; dx < 10; dx++) {
                                                if (dx == d9 || dx == d8 || dx == d7 || dx == d6 || dx == d5 || dx == d4 || dx == d3 || dx == d2 || dx == d1) continue;
                                                if ((d8 * 100 + d9 * 10 + dx) % 17 != 0) continue; // X17
                                                long cislo = d1 * 1000000000L + d2 * 100000000L + d3 * 10000000L + d4 * 1000000 + d5 * 100000 + d6 * 10000 + d7 * 1000 + d8 * 100 + d9 * 10 + dx;
                                                System.out.println(cislo);
                                                sum += cislo;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Total sum of pandigital numbers with substring divisibility: " + sum);

    }
}
