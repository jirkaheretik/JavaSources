package cz.braza.advent.a2015;

public class Day20Presents {
    public static final int PRESENTS = 34000000;
    public static void main(String[] args) {
        fasterPart1();
        part2();
    }

    public static void fasterPart1() {
        final int STROP = 1000000;
        int[] cisla = new int[STROP];
        for (int i = 1; i < STROP; i++) {
            int cislo = i;
            do {
                cisla[cislo] += i;
                cislo += i;
            } while (cislo < STROP);
        }
        for (int i = 1; i < STROP; i++)
            if (cisla[i] * 10 >= PRESENTS) {
                System.out.println("Part 1: house " + i + " gets " + (cisla[i] * 10) + " presents.");
                break;
            }
    }

    public static void part2() {
        final int STROP = 1000000;
        int[] cisla = new int[STROP];
        for (int i = 1; i < STROP; i++) {
            for (int k = 1; k <= 50; k++)
                if (k * i < STROP)
                    cisla[k * i] += i;
                else break;
        }
        for (int i = 1; i < STROP; i++)
            if (cisla[i] * 11 >= PRESENTS) {
                System.out.println("Part 2: house " + i + " gets " + (cisla[i] * 11) + " presents.");
                break;
            }

    }
    public static void stupidPart1() {
        int house = 0;
        int presents = 0;
        while (presents < PRESENTS) {
            house++;
            presents = sumOfDividers(house) * 10;
        }
        System.out.println("Part 1: House number " + house + " gets " + presents + " presents.");
        // Part 1: House number 786240 gets 34137600 presents.
    }

    public static int sumOfDividers(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++)
            if (n % i == 0) sum += i;
        return sum;
    }
}
