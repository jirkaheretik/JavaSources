package cz.braza.euler;

/**
 * p - perimeter of a right angle triangle with integer sides a, b, c
 * for p==120... three solutions {20, 48, 52}, {24, 45, 51} and {30, 40, 50}
 * For which p <= 1000 there is a maximum number of solutions?
 */
public class P39PythagoreanTriangles {
    public static int countSolutions(int p) {
        int solutions = 0;
        for (int a = 1; a < p/3; a++)
            for (int b = a; b < p/2; b++) {
                int c = p - a - b;
                if (c >= a + b)
                    continue;
                if (a*a + b*b == c*c) {
                    solutions++;
                    //System.out.println("{" + a + ", " + b  + ", " + c + "}");
                }
            }
        return solutions;
    }
    public static void main(String[] args) {
        int max = 0;
        int maxN = 0;
        for (int n = 10; n <= 1000; n++) {
            int pocet = countSolutions(n);
            if (pocet > max) {
                max = pocet;
                maxN = n;
            }
        }
        System.out.println("Maximum number of solutions: " + max  + " for number p = " + maxN);
    }
}
