package cz.braza.euler;

/**
 * Find pythagorean triplet: a2 + b2 = c2, a < b < c
 * such that a+b+c = 1000
 * Then return a*b*c
 */
public class P9PythagoreanTriplet {

    public static void findSolution(final int obvod) {
        for (int a = 1; a < obvod; a++)
            for (int b = a + 1; b < obvod; b++) {
                int c = obvod - a - b;
                if (a + b <= c) continue;
                if (a*a + b*b == c*c)
                    System.out.println("Found triplet (" + a + ", " + b + ", " + c + ") with a product of " + (a*b*c));
            }
    }
    public static void main(String[] args) {
        for (int a = 1; a < 998; a++)
            for (int b = a + 1; b < 999; b++)
                for (int c = b + 1; c < 999; c++) {
                    if (a + b + c > 1000)
                        break; // no more with the same [a, b]
                    if (a*a+b*b < c*c)
                        break; // no more again...
                    if (a*a+b*b==c*c)
                        if (a+b+c == 1000)
                            System.out.println("Found triplet (" + a + ", " + b + ", " + c + ") with a product of " + (a*b*c));
                }
        findSolution(1000);
        findSolution(840);
    }
}
