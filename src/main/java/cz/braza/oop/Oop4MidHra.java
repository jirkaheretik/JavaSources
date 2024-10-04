package cz.braza.oop;

import java.util.Scanner;

class Lokace {
    private String nazev;
    private String popis;
    Lokace sever;
    Lokace jih;
    Lokace zapad;
    Lokace vychod;
    public Lokace(String n, String p) {
        nazev = n;
        popis = p;
    }
    public void setSmer(Lokace to, String smer) {
        switch (smer) {
            case "N" -> { sever = to; to.jih = this; }
            case "W" -> { zapad = to; to.vychod = this; }
            case "S" -> { jih = to; to.sever = this; }
            case "E" -> { vychod = to; to.zapad = this; }
        }
    }
    @Override
    public String toString() {
        String smery = "Můžeš jít na";
        if (sever != null) smery += " sever";
        if (jih != null) smery += " jih";
        if (zapad != null) smery += " západ";
        if (vychod != null) smery += " východ";
        return nazev + "\n" + popis + "\n\n" + smery + "\n\nZadej příkaz:";
    }
}

class Hra {
    public static final String ERRMSG = "Tímto směrem nelze jít.";
    Lokace aktualni;
    public Hra() {
        Lokace hrad = new Lokace("Hrad", "Stojíš před okovanou branou gotického hradu, která je zřejmě jediným vchodem do pevnosti.\nKlíčová dírka je pokryta pavučinami, což vzbuzuje dojem, že je budova opuštěná.");
        Lokace les = new Lokace("Les", "Jsi na lesní cestě, která se klikatí až za obzor, kde mizí v siluetě zapadajícího\nslunce. Ticho podvečerního lesa občas přeruší zpěv posledních ptáků.");
        Lokace rozcesti = new Lokace("Lesní rozcestí", "Nacházíš se na lesním rozcestí.");
        Lokace les2 = new Lokace("Les", "Jsi na lesní cestě, která se klikatí až za obzor, kde mizí v siluetě zapadajícího\nslunce. Ticho podvečerního lesa občas přeruší zpěv posledních ptáků.");
        Lokace rybnik = new Lokace("Rybník", "Došel jsi ke břehu malého rybníka. Hladina je v bezvětří jako zrcadlo. Kousek\nod tebe je dřevěná plošina se stavidlem.");
        Lokace les3 = new Lokace("Les", "Jsi na lesní cestě, která se klikatí až za obzor, kde mizí v siluetě zapadajícího\nslunce. Ticho podvečerního lesa občas přeruší zpěv posledních ptáků.");
        Lokace dum = new Lokace("Dům", "Stojíš před svým rodným domem, cítíš vůni čerstvě nasekaného dřeva, která se line\nz hromady vedle vstupních dveří.");
        hrad.setSmer(les, "E");
        les.setSmer(rozcesti, "E");
        rozcesti.setSmer(les2, "E");
        les2.setSmer(rybnik, "E");
        rozcesti.setSmer(les3, "S");
        les3.setSmer(dum, "E");
        aktualni = dum;
    }
    public Lokace getLokace() { return aktualni; }
    public void pohyb(String smer) {
        switch (smer) {
            case "sever" -> {if (aktualni.sever == null) throw new IllegalArgumentException(ERRMSG); aktualni = aktualni.sever; }
            case "jih" -> {if (aktualni.jih == null) throw new IllegalArgumentException(ERRMSG); aktualni = aktualni.jih; }
            case "východ" -> {if (aktualni.vychod == null) throw new IllegalArgumentException(ERRMSG); aktualni = aktualni.vychod; }
            case "západ" -> {if (aktualni.zapad == null) throw new IllegalArgumentException(ERRMSG); aktualni = aktualni.zapad; }
        }
    }
}

public class Oop4MidHra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hra hra = new Hra();
        String prikaz = "";
        while (!"konec".equals(prikaz)) {
            System.out.println(hra.getLokace());
            prikaz = sc.nextLine().trim().toLowerCase();
            try {
                if (prikaz.startsWith("jdi na ")) {
                    hra.pohyb(prikaz.substring(7).trim());
                } else if (!"konec".equals(prikaz))
                    System.out.println("Můj vstupní slovník neobsahuje tento příkaz.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
