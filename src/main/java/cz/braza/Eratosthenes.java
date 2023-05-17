package cz.braza;

import java.util.Scanner;

public class Eratosthenes {
    private static final int STROP = 2000000;
    private static final boolean[] SITO = new boolean[STROP + 1];

    private static void prosevej(int prvocislo) {
        int nasobek = 2;
        while (prvocislo * nasobek <= STROP)
            SITO[prvocislo * nasobek++] = true;
    }

    private static void prosevej() {
        SITO[0] = true;
        SITO[1] = true;
        // dvojka:
        prosevej(2);
        for (int i = 3; i <= Math.sqrt(STROP); i += 2) {
            if (!SITO[i])
                prosevej(i);
        }
    }

    private static void testuj() {
        int count = 0;
        for (int i = 0; i <= STROP; i++)
            if (!SITO[i]) count++;
        System.out.println("Existuje " + count + " prvocisel do " + STROP);
    }

    public static void main(String[] args) {
        System.out.println("Spoustim Eratosthenovo sito...");
        prosevej();
        System.out.println("Sito dokonceno, muzeme testovat.");
        testuj();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Zadej dolni mez intervalu: ");
            int dolni = sc.nextInt();
            System.out.println("Zadej horni mez intervalu: ");
            int horni = sc.nextInt();
            if ((horni >= dolni) && (dolni >= 0) && (horni <= STROP)) {
                System.out.println("Vypisuji prvocisla od " + dolni + " do " + horni + ":");
                for (int i = dolni; i <= horni; i++)
                    if (!SITO[i]) System.out.print("\t" + i);
                System.out.println("\nHotovo. Muzes znovu.");
            } else {
                System.out.println("Dolni mez musi byt nezaporna, horni mez nesmi prekrocit " +
                        STROP + " a horni mez nesmi byt mensi nez dolni mez. Zadani muzes opakovat.");
            }
        }
    }
}
