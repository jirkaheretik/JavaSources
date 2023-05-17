package cz.braza.educanet;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Platidla {
    public static final int[] HODNOTY_DIST = { 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1};
    public static final int MAX_POCET_DIST = 10; // kolikrat lze dat stejnou hodnotu
    public static final int PLATIDEL_DIST = HODNOTY_DIST.length;

    public static int[] hodnoty = HODNOTY_DIST;
    public static int maxPocet = MAX_POCET_DIST;
    public static int platidel = hodnoty.length;
    public static int pocitadlo = 0;

    public static void vypisKombinace(int castka, int minIndex, String cesta) {
        vypisKombinace(castka, minIndex, cesta, true);
    }
    public static void vypisKombinace(int castka, int minIndex, String cesta, boolean vypisovat) {
        // 1: Jsem na konci seznamu?
        if (minIndex == platidel - 1) {
            if (castka <= maxPocet) {
                pocitadlo++;
                if (vypisovat) System.out.println(cesta + (castka == 0 ? "" : castka + "x1"));
            }
            return;
        }
        for(int i = maxPocet; i >=0; i--) {
            // i je počet, kolikrát beru aktuální částku (HODNOTY[minIndex]), odečtu od zbývající částky a to musím pokrýt menšími hodnotami
            int dalsiCastka = castka - i * hodnoty[minIndex];

            // HEURISTIKY/ořezávání na obě strany
            if (dalsiCastka < 0) {// dávám moc
                continue;
            }

            // nedávám málo??
            int max = 0;
            for (int j = minIndex + 1; j < platidel; j++)
                max += maxPocet * hodnoty[j];
            if (max < dalsiCastka) {
                // ani s maximem od všeho částku nedosáhnu, nemá cenu tam lézt:
                break; //
            }
            // konec heuristik
            vypisKombinace(dalsiCastka, minIndex + 1, cesta + (i == 0 ? "" : i + "x" + hodnoty[minIndex] + " "), vypisovat);
        }
    }

    public static void nactiVstup(String data) {
        try {
            String[] cislaRetezec = data.split(";");
            int[] result = new int[cislaRetezec.length];
            for (int i = 0; i < cislaRetezec.length; i++)
                result[i] = Integer.parseInt(cislaRetezec[i]);
            hodnoty = result;
            platidel = hodnoty.length;
        } catch (Exception e) {
            hodnoty = HODNOTY_DIST;
            platidel = PLATIDEL_DIST;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
        while(true) {
            System.out.println("Nový běh, inicializace\n======================\n");
            System.out.println("Zadejte částky k dispozici, od největší, oddělené středníkem [konec pro ukončení]:");
            String vstup = sc.nextLine().trim();
            if ("konec".equals(vstup))
                break;
            nactiVstup(vstup);
            System.out.println("Zadejte maximální počet od jedné hodnoty [10]:");
            try {
                maxPocet = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                maxPocet = MAX_POCET_DIST;
            }
            System.out.println("Zadejte částku v Kč:");
            int castka = Integer.parseInt(sc.nextLine());
            pocitadlo = 0;
            vypisKombinace(castka, 0, "", false);
            System.out.println("Konec výpisu možností, nalezeno " + pocitadlo + " kombinací.");
        }
    }
}
