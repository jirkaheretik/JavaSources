package cz.braza.zaklady;

/*
(A/B)^C * (D)^(E/F)
A-F ~ 1-6, each letter different digit.
Find max value and min value
 */
public class Algebrogram {
    public static void main(String[] args) {
        double min = 1;
        double max = 1;
        double oneDiff = 1;
        String VAL = "(%d / %d)^%d * %d^(%d / %d)";
        String minStr = "";
        String maxStr = "";
        String oneStr = "";
        for (int a = 1; a <= 6; a++)
            for (int b = 1; b <= 6; b++) {
                if (b == a) continue;
                for (int c = 1; c <= 6; c++) {
                    if (c == a || c == b) continue;
                    for (int d = 1; d <= 6; d++) {
                        if (d == a || d == b || d == c) continue;
                        for (int e = 1; e <= 6; e++) {
                            if (e == a || e == b || e == c || e == d) continue;
                            for (int f = 1; f <= 6; f++) {
                                if (f == a || f == b || f == c || f == d || f == e) continue;
                                double ab = Math.pow((double)a / b, c);
                                double de = Math.pow(d, (double)e / f);
                                double result = ab * de;
                                if (result > max) {
                                    max = result;
                                    maxStr = String.format(VAL, a, b, c, d, e, f);
                                }
                                if (result < min) {
                                    min = result;
                                    minStr = String.format(VAL, a, b, c, d, e, f);
                                }
                                if (Math.abs(result - 1) < oneDiff) {
                                    oneDiff = Math.abs(result - 1);
                                    oneStr = String.format(VAL, a, b, c, d, e, f);
                                }
                            }
                        }
                    }
                }
            }
        System.out.println("Max = " + max + " with: " + maxStr);
        System.out.println("Min = " + min + " with: " + minStr);
        System.out.println("Closest to 1 = " + oneDiff + " with: " + oneStr);
    }
}
