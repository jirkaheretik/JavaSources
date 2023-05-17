package cz.braza.advent;

import java.util.Random;

/**
 * Chobotnice/nervy:
 * Na vstupu pole 10x10 čísel 0-9 (energie).
 * Krok:
 * a) všem zvednout energii o 1
 * b) kdo má 10+, bouchne/zazáří, čímž zvedne energii o 1 všem (až osmi) přímým sousedům.
 * Ti tak mohou zase bouchnout/zářit. Na konci kroku všem, co zářili, nastavit 0
 * Otázka 1: Kolik vzruchů/záření za 100 kroků?
 * Otázka 2: Za kolik kroků zazáří všichni najednou?
 */
public class Chobotnice {
    public static final int X = 10;
    public static final int Y = 10;
    public static final int[][] POLE = new int[Y + 2][X + 2];

    private static void inicializace() {
        Random gen = new Random();
        for (int i = 1; i <= Y; i++)
            for (int j = 1; j <= X; j++)
                POLE[i][j] = gen.nextInt(9);
    }

    /**
     * provede krok nad polem POLE, vraci pocet "blysknuti"
     * @return
     */
    private static int krok() {
        int pocet = 0;
        // zvetsit vse o 1:
        for (int i = 1; i <= Y; i++)
            for (int j = 1; j <= X; j++)
                POLE[i][j] += 1;
        // vyresit vsechna blysknuti
        boolean blysknuto;
        do {
            blysknuto = false;
            for (int i = 1; i <= Y; i++)
                for (int j = 1; j <= X; j++)
                    if (POLE[i][j] > 9) {
                        pocet++;
                        blysknuto = true;
                        for (int x = -1; x < 2; x++)
                            for (int y = -1; y < 2; y++)
                                if (POLE[i + y][j + x] >= 0)
                                    POLE[i + y][j + x] += 1;
                        POLE[i][j] = -1; // nebude v tomto kroku zvysovano
                    }
        } while (blysknuto);
        // nastavit blysknute bunky na 0:
        for (int i = 1; i <= Y; i++)
            for (int j = 1; j <= X; j++)
                if (POLE[i][j] < 0)
                    POLE[i][j] = 0;
        return pocet;
    }

    public static void main(String[] args) {
        inicializace();
        int kroku = 0;
        int blysknuti = 0;
        boolean konec = false;
        while (!konec) {
            kroku++;
            if (kroku % 1000000 == 0)
                System.out.println("Krok " + kroku);
            int krok = krok();
            if (krok > X * Y) {
                System.out.println("Problem, krok " + kroku + ", blysknuti: " + krok);
            }
            if (kroku <= 100)
                blysknuti += krok;
            else if (kroku == 101)
                System.out.println("Za 100 kroku se blyskne " + blysknuti + "krat.");
            if (krok == X * Y) {
                System.out.println("Synchronizovane blysknuti vsech za " + kroku + " kroku.");
                konec = true;
            }
        }
    }
}
