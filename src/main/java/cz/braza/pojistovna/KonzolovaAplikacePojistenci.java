package cz.braza.pojistovna;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

/**
 * Hlavní program celé aplikace, obsahuje metodu main (vstupní bod programu)
 * a reprezentuje konzolovou aplikaci pro interakci s evidencí.
 * Evidence je aktuálně napevno daná ve vlastnosti <b>evidence</b>, ale
 * pouhou náhradou za jinou třídu lze dosáhnout jiného chování/ukládání dat.
 *
 * Projekt vznikal jako ukázkový a jako proof-of-concept vyučovaných pravidel
 * v rámci školení <i>best practices</i>. Zároveň jsem byl zvědavý, kolik času
 * něco takového může zabrat.
 * <ul>
 *     <li>Cca 20 minut třída s pojištěncem (základ)</li>
 *     <li>Dalších 20 minut třída s evidencí</li>
 *     <li>Pak asi hodinu, hodinu a čtvrt na samotnou konzolovou aplikaci, včetně toho, že jsem udělal rozhraní pro evidenci, aby ji bylo možné vyměnit.</li>
 *     <li>Potom už šlo jen o rozšiřování dejme tomu hodinu na práci se soubory CSV nad stávajícím rámcem.</li>
 *     <li>Databáze (MySQL) zabrala asi o trochu víc, když jsem potřeboval prošťouchnout, jak připojit knihovny, které potřebuju, atd.</li>
 *     <li>Později už jen drobnosti, kompletování dokumentačních komentářů, přidání barviček apod.</li>
 * </ul>
 * Celkově se dá říci, že i v této podobě to je možné za odpoledne vytvořit.
 */
public class KonzolovaAplikacePojistenci {
    /**
     * Základní implementace s uložením záznamů pouze v paměti
     */
    //private static final EvidenceService evidence = new EvidenceVPameti();
    /**
     * Navazující implementace, která ukládá data do CSV souboru (zadávaného v konstruktoru)
     */
    private static final EvidenceService evidence = new EvidenceCSV("/home/jirka/src/java0/pojistenci.dat");
    /**
     * Implementace evidence s využitím relační databáze
     */
    // private static final EvidenceService evidence = new EvidenceMySQL();
    /**
     * Scanner využívaný v naší aplikaci (vstupy od uživatele)
     */
    private static final Scanner sc = new Scanner(System.in);
    /**
     * Povolené volby v nabídce (pro kontrolu uživatelských vstupů)
     * POZOR, pro správnou funkčnost je třeba pole udržovat setříděné
     * (v aplikaci není měněno)
     */
    public static final int[] VOLBY_MENU = {1, 2, 3, 4, 9};

    /**
     * Barevné konstanty pro barvení textu, experiment
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * Barevné konstanty pro barvení pozadí textu
     */
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    /**
     * Metoda pro zobrazení nabídky. V případě přidávání položek
     * je třeba též upravit {@link #VOLBY_MENU} a {@link #main}
     */
    private static void zobrazNabidku() {
        System.out.println(
                ANSI_PURPLE + "\nProgramátorská pojišťovna\n" +
                        "=========================\n" + ANSI_RESET +
                        "V evidenci je: " + ANSI_RED_BACKGROUND + ANSI_WHITE + evidence.vratPocetZaznamu() + " záznamů" + ANSI_RESET + "\n" +
                        "Vyberte si jednu z následujících akcí:\n" +
                        "1) Vypsat všechny pojištěnce\n" +
                        "2) Vkládání nových záznamů\n" +
                        "3) Vyhledat pojištěnce dle řetězce\n" +
                        "4) Vyhledat pojištěnce dle věku\n" +
                        "9) Ukončit aplikaci\n\n"
        );
    }

    /**
     * Hlavní program - smyčka obsluhující nabídku.
     * Ke správné funkčnosti vyžaduje korektní obsah {@link #VOLBY_MENU}
     * @param args
     */
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

    /**
     * Zobrazení záznamů z evidence
     */
    private static void zobrazZaznamy() {
        vypisPojistence(evidence.vyberVsechnyPojistence());
    }

    /**
     * Pomocná metoda používaná pro zobrazení promptu uživateli v případě,
     * že již data zadával a máme je k dispozici, aby nemusel zadávat znovu
     * @param txt Text, který bude opatřen závorkami, je-li neprázdný
     * @return Výsledný řetězec (buď prázdný nebo s hranatými závorkami okolo)
     */
    private static String ozavorkuj(String txt) {
        return txt.isEmpty() ? "" : " [" + txt + "]";
    }

    /**
     * Pomocná metoda pro zadávání vstupu
     * @param vyzva Výzva pro uživatele (co se očekává k zadání)
     * @param defVal Defaultní/počáteční hodnota, která se použije v případě, že uživatel zadá pouze prázdný řetězec
     * @return Výsledná hodnota od uživatele (v případě neprázdného zadaného textu to bude ten, jinak defaultní hodnota)
     */
    private static String getOrDefault(String vyzva, String defVal) {
        System.out.print(vyzva + ozavorkuj(defVal) + ": ");
        String result = sc.nextLine().trim();
        return result.isEmpty() ? defVal : result;
    }

    /**
     * Metoda pro zadávání údajů pro nového pojištěnce.
     * Obsluhuje celé zadávání a počítá s tím, že může dojít k chybě
     * (databázové nebo datové, pokud zadaná data odmítne třída {@link Pojistenec})
     * V takovém případě umožní uživateli zadat data znovu, přičemž si zapamatuje
     * data již dříve zadaná (stačí upravit ta, která nejsou zadána správně)
     */
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

    /**
     * Vyhledávání uživatelů podle jména, příjmení a/nebo telefonního čísla
     */
    private static void vyhledejTextove() {
        System.out.print("Vyhledávání dle textu. Zadejte jméno, příjmení nebo obojí (oddělené mezerou),\n lze zadat i jen začátek jména či příjmení, a systém zároveň podporuje vyhledávání dle telefonního čísla.\nVyhledávaný řetězec: ");
        String textKVyhledani = sc.nextLine().trim();
        System.out.println("Vyhledávám text: " + textKVyhledani);
        vypisPojistence(evidence.vyhledejPojistence(textKVyhledani));
    }

    /**
     * Vyhledávání uživatelů dle zadaného rozpětí věků
     */
    private static void vyhledejDleVeku() {
        int min = nactiCisloNeboDefault("Vyhledávání pojištěnců dle věku (od-do).\nZadejte minimální věk (0): ", "Minimální věk nebyl určen.", 0);
        int max = nactiCisloNeboDefault("Zadejte maximální věk (MAX): ", "Maximální věk nebyl určen.", Integer.MAX_VALUE);
        vypisPojistence(evidence.vyhledejPojistenceDleVeku(min, max));
    }

    /**
     * Pomocná metoda pro zadání číselných vstupů
     * @param vyzva Informace uživateli o očekávaném vstupu
     * @param chybovaZprava Zpráva zobrazovaná po neplatném vstupu
     * @param defaultVal Defaultní hodnota použitá v případě neplatného zadání
     * @return Výsledná číselná hodnota
     */
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

    /**
     * Pomocná metoda pro samotné vypsání seznamu pojištěnců (získaného z různých metod, viz výše)
     * @param seznam
     */
    private static void vypisPojistence(Collection<Pojistenec> seznam) {
        if (seznam == null || seznam.isEmpty()) {
            System.out.println("Není k dispozici žádný záznam");
        } else {
            for (Pojistenec p : seznam)
                System.out.println(p);
        }
    }
}
