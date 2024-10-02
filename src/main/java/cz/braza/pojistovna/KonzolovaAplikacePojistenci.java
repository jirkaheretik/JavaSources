package cz.braza.pojistovna;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class KonzolovaAplikacePojistenci {
    // private static final SeznamPojistencu evidence = new EvidenceCSV("/home/jirka/src/java0/pojistenci.dat");
    // private static final SeznamPojistencu evidence = new Evidence();
    private static final SeznamPojistencu evidence = new EvidenceMySQL();
    private static final Scanner sc = new Scanner(System.in);
    private static final int[] VOLBY_MENU = {1, 2, 3, 4, 9};

    private static void zobrazNabidku() {
        System.out.println(
                "Programátorská pojišťovna\n" +
                        "=========================\n" +
                        "V evidenci je: " + evidence.vratPocetZaznamu() + " záznamů\n" +
                        "Vyberte si jednu z následujících akcí:\n" +
                        "1) Vypsat všechny pojištěnce\n" +
                        "2) Vkládání nových záznamů\n" +
                        "3) Vyhledat pojištěnce dle řetězce\n" +
                        "4) Vyhledat pojištěnce dle věku\n" +
                        "9) Ukončit aplikaci\n\n"
        );
    }

    public static void main(String[] args) {
        int volba = 0;
        while (volba != 9) {
            zobrazNabidku();
            do {
                volba = nactiCisloNeboDefault("Vaše volba?", "Neplatná volba", 0);
            } while (Arrays.binarySearch(VOLBY_MENU, volba) < 0);
            switch (volba) {
                case 1 -> zobrazZaznamy();
                case 2 -> vlozPojistence();
                case 3 -> vyhledejTextove();
                case 4 -> vyhledejDleVeku();
                case 9 -> evidence.ukonci();
            }
        }
        System.out.println("Děkujeme za použití aplikace a přejeme příjemný den!");
    }

    private static void zobrazZaznamy() {
        vypisPojistence(evidence.vyberVsechnyPojistence());
    }

    private static String ozavorkuj(String txt) {
        return txt.isEmpty() ? "" : " [" + txt + "]";
    }

    private static String getOrDefault(String vyzva, String defVal) {
        System.out.print(vyzva + ozavorkuj(defVal) + ": ");
        String result = sc.nextLine().trim();
        return result.isEmpty() ? defVal : result;
    }

    private static void vlozPojistence() {
        System.out.println("Vkládání nových záznamů. Zadejte platné hodnoty jednotlivých údajů.");
        String jmeno = "";
        String jmenoPuvodni = "";
        String prijmeni = "";
        String prijmeniPuvodni = "";
        String vek = "";
        String vekPuvodni = "";
        String telefon = "";
        String telefonPuvodni = "";
        String znovu = "ano";
        while ("ano".equals(znovu)) {
            boolean vkladat = true;
            while (vkladat) {
                jmeno = getOrDefault("Zadejte jméno", jmenoPuvodni);
                prijmeni = getOrDefault("Zadejte příjmení", prijmeniPuvodni);
                vek = getOrDefault("Zadejte věk", vekPuvodni);
                telefon = getOrDefault("Zadejte telefon", telefonPuvodni);
                try {
                    evidence.pridejPojistence(jmeno, prijmeni, Integer.parseInt(vek), telefon);
                    System.out.println("Záznam byl úspěšně uložen v evidenci.");
                    vkladat = false;
                } catch (Exception e) {
                    System.out.println("Chyba při pokusu o vytvoření záznamu, pravděpodobně kvůli chybně zadaným datům. Chybová zpráva: " + e.getMessage());
                    jmenoPuvodni = jmeno;
                    prijmeniPuvodni = prijmeni;
                    vekPuvodni = vek;
                    telefonPuvodni = telefon;
                }
            }
            System.out.print("Přejete si vložit další záznam? (ano/ne) ");
            znovu = sc.nextLine().trim().toLowerCase();
        }
    }

    private static void vyhledejTextove() {
        System.out.print("Vyhledávání dle textu. Zadejte jméno, příjmení nebo obojí (oddělené mezerou),\n lze zadat i jen začátek jména či příjmení, a systém zároveň podporuje vyhledávání dle telefonního čísla.\nVyhledávaný řetězec: ");
        String textKVyhledani = sc.nextLine().trim();
        System.out.println("Vyhledávám text: " + textKVyhledani);
        vypisPojistence(evidence.vyhledejPojistence(textKVyhledani));
    }

    private static void vyhledejDleVeku() {
        int min = nactiCisloNeboDefault("Vyhledávání pojištěnců dle věku (od-do).\nZadejte minimální věk (0): ", "Minimální věk nebyl určen.", 0);
        int max = nactiCisloNeboDefault("Zadejte maximální věk (MAX): ", "Maximální věk nebyl určen.", Integer.MAX_VALUE);
        vypisPojistence(evidence.vyhledejPojistenceDleVeku(min, max));
    }

    private static int nactiCisloNeboDefault(String vyzva, String chybovaZprava, int defaultVal) {
        System.out.println(vyzva);
        String volba = sc.nextLine().trim();
        int vysledek = defaultVal;
        try {
            vysledek = Integer.parseInt(volba);
        } catch (NumberFormatException nfe) {
            if (chybovaZprava != null && !chybovaZprava.isEmpty())
                System.out.println(chybovaZprava);
        }
        return vysledek;
    }

    private static void vypisPojistence(Collection<Pojistenec> seznam) {
        if (seznam == null || seznam.isEmpty()) {
            System.out.println("Není k dispozici žádný záznam");
        } else {
            for (Pojistenec p : seznam)
                System.out.println(p);
        }
    }
}
