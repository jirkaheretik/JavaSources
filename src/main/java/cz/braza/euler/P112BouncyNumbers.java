package cz.braza.euler;

enum TypCisla {
    Increasing, Decreasing, Steady, Bouncy;

    public String toString() {
        return this.name();
    }
    public static TypCisla getType(int cislo) {
        boolean increasing = true;
        boolean decreasing = true;
        int last = -1;
        while (cislo > 0) {
            int cislice = cislo % 10;
            cislo /= 10;
            if (cislice > last && last > -1)
                increasing = false;
            if (cislice < last)
                decreasing = false;
            last = cislice;
        }
        if (increasing)
            return decreasing ? TypCisla.Steady : TypCisla.Increasing;
        else
            return decreasing ? TypCisla.Decreasing : TypCisla.Bouncy;
    }
}
public class P112BouncyNumbers {
    public static void main(String[] args) {
        // test fce coJeZac():
        System.out.println(TypCisla.getType(112567));
        System.out.println(TypCisla.getType(222222222));
        System.out.println(TypCisla.getType(1587454587));
        System.out.println(TypCisla.getType(94310));

        // main fce
        int bouncy = 0;
        int other = 0;
        int[] marks = { 538, 1000, 21780};

        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (TypCisla.getType(i) == TypCisla.Bouncy)
                bouncy++;
            else
                other++;
            if (bouncy == other * 99) {
                System.out.println("Target number: " + i + ", bouncy: " + bouncy);
                break;
            }
            if (bouncy > other * 99)
                System.out.println("Over 99 % for number " + i + ", bouncy: " + bouncy);
            for (int x = 0; x < marks.length; x++)
                if (i == marks[x])
                    System.out.println(i + ", bouncy: " + bouncy + ", other: " + other + ", ratio: " + ((double) bouncy / (bouncy + other)));

        }

    }

}
