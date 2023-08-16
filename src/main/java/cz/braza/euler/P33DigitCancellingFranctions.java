package cz.braza.euler;

import cz.braza.educanet.Frac;

public class P33DigitCancellingFranctions {
    public static void main(String[] args) {
        Frac product = Frac.fromInt(1);
        for (int citatel = 10; citatel < 99; citatel++) {
            for (int jmenovatel = citatel + 1; jmenovatel < 100; jmenovatel++) {
                int c1 = citatel / 10;
                int c2 = citatel % 10;
                int j1 = jmenovatel / 10;
                int j2 = jmenovatel % 10;
                Frac f1 = new Frac(citatel, jmenovatel);
                Frac f2 = Frac.fromInt(2);
                if (c1 == j1 && j2 != 0)
                    f2 = new Frac(c2, j2);
                else if (c1 == j2)
                    f2 = new Frac(c2, j1);
                else if (c2 == j1 && j2 != 0)
                    f2 = new Frac(c1, j2);
                else if (c2 == j2 && j2 != 0)
                    f2 = new Frac(c1, j1);
                if (f1.equals(f2)) {
                    System.out.println(citatel + "/" + jmenovatel + " ===" + f2);
                    product = product.times(f2);
                }
            }
        }
        System.out.println("Total product of curious fractions is " + product);
    }
}
