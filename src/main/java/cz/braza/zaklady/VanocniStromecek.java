package cz.braza.zaklady;

// kod od Pavly Sklenarove, upraveno do jedne tridy
// https://discord.com/channels/873147646917898271/956163210019487764/1321794523868827748
class Stromecek {
    private int koruna;
    private int kmen;

    /**
     * Konstruktor pro vánoční stromeček
     * @param koruna Počet řádků potřebných pro vytvoření koruny
     * @param kmen Počet řádků potřebných pro vytvoření kmene
     */
    public Stromecek(int koruna, int kmen) {
        this.koruna=koruna;
        this.kmen = kmen;
    }

    /**
     * počet řádků potřebných na vytvoření koruny
     */
    public int getKoruna() { return koruna; }

    /**
     * počet řádků potřebných na vytvoření kmene
     */
    public int getKmen() { return kmen; }

    public static void nastavPauzu(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }

    @Override
    public String toString() {
        int delkaRadku = 2 * koruna - 1;
        int indexStredu = delkaRadku / 2;
        StringBuilder radek = new StringBuilder();
        for (int j = 0; j < koruna + kmen; j++) {
            if(j < koruna)
                for (int i = 0; i < delkaRadku; i++) //vložíme mezeru nebo x
                    radek.append((i >= indexStredu - j && i <= indexStredu + j) ? "X" : " ");
            else
                for (int k = 0; k < delkaRadku; k++)
                    radek.append(k == indexStredu ? "X" : " ");
            radek.append("\n");
        }
        return radek.toString();
    }

    public void dramatickyVytiskni() {
        String[] lines = toString().split("\n");
        for (String line : lines) {
            System.out.println(line);
            nastavPauzu(500);
        }
    }
}

public class VanocniStromecek {
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static final int UROVNI = 3;          // kolik pater větví
    public static final int VELIKOST_UROVNE = 3; // kolik řad v jedné úrovni
    public static final int ROZDIL = 2;          // o kolik je v patře víc znaků
    public static final int TLOUSTKA = 2;        // tloušťka kmene
    public static final int PADLEFT = 10;        // odsazení zleva
    public static final int KMEN = 3;            // délka kmene

    public static void main(String[] args) {
        int trunkLeftPos = PADLEFT + (VELIKOST_UROVNE - 1) * ROZDIL;
        System.out.println(ANSI_GREEN);

        for (int level = 0; level < UROVNI; level++) {
            for (int line = 0; line < VELIKOST_UROVNE; line++)
                System.out.printf("%" + (trunkLeftPos + ROZDIL * line) + "s%n", "◢" + "█".repeat(TLOUSTKA + 2 * ROZDIL * line) + "◣");
        }
        System.out.print(ANSI_YELLOW);
        for (int kmen = 0; kmen < KMEN; kmen++)
            System.out.printf("%" + trunkLeftPos + "s%n", "█".repeat(TLOUSTKA));
        System.out.println(ANSI_RESET);

        // System.out.println(new Stromecek(9, 4));
        new Stromecek(9, 4).dramatickyVytiskni();
    }
}
