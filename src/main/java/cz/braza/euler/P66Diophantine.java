package cz.braza.euler;

import java.util.Arrays;

public class P66Diophantine {
    public static final int MAXINT = 500000001;
    public static long[] SQUARES = new long[MAXINT];
    public static void init() {
        long tmp;
        for (int i = 0; i < MAXINT; i++) {
            tmp = i;
            SQUARES[i] = tmp * tmp;
            if (SQUARES[i] < 0)
                System.out.println(i + "^2 = " + SQUARES[i]);
        }
    }

    public static boolean isSquare(long i) {
        return Arrays.binarySearch(SQUARES, i) > -1;
    }
    public static void main(String[] args) {
        init();
        // test: 45444^2:
        System.out.println(SQUARES[MAXINT-10]);
        System.out.println(SQUARES[MAXINT / 2]);
        System.out.println(SQUARES[11]);
        System.out.println(isSquare(2065157136));
        System.out.println(isSquare(9));
        System.out.println(isSquare(121));

        int maxX = 0;
        int maxD = 0;

        for (int D = 2; D <= 1000; D++) {
            if (isSquare(D))
                continue; // no integer solution for squared D
            for (int x = 2; x < MAXINT; x++) {
                double y = (double)(SQUARES[x] - 1) / D;
                if (y % 1 == 0) // only for whole numbers:
                    if (isSquare((long) y)) {
                        if (x > maxX) {
                            maxX = x;
                            maxD = D;
                            System.out.println("Found new max D=" + maxD + " (x = " + maxX + ")");
                        }
                        System.out.println("Found solution for D=" + D + ", x=" + x + ", y=" + y);
                        break; // found a diophantine solution, break the for loop
                    }
                if (x == MAXINT - 1)
                    System.out.println("Did not find a solution for a D=" + D);
            }
            //System.out.println("End of D=" + D);
        }

    }
}
