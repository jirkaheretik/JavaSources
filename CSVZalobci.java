package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

class Zaloba {
    private final int rok;
    private final String popis;
    private final double castka;
    public Zaloba(int rok, String popis, double castka) {
        this.rok = rok;
        this.popis = popis;
        this.castka = castka;
    }
    public int getRok() { return rok; }
    public String getPopis() { return popis; }
    public double getCastka() { return castka; }
}

class Databaze {
    private List<Zaloba> data = new ArrayList<>();
    public void pridej(int rok, String popis, double castka) {
        data.add(new Zaloba(rok, popis, castka));
    }

    /**
     * Vraci prumernou zalovanou castku v zadanem roce
     * @param rok
     * @return prumer zalob v roce
     */
    public double prumerZaRok(int rok) {
        double soucet = 0;
        int pocet = 0;
        for (Zaloba z: data) {
            if (z.getRok() == rok) {
                soucet += z.getCastka();
                pocet++;
            }
        }
        return pocet == 0 ? 0 : soucet / pocet;
    }

    /**
     * Najde nejdrazsiho zalobce (soucet vsech jeho zalovanych castek)
     * @return Mapu s jednim prvkem, kde indexem je jmeno/nazev zalobce,
     * hodnotou pak soucet zalovanych castek
     */
    public Map<String, Double> nejdrazsiZalobce() { return null; }

    /**
     * Pro zalobce zadaneho parametrem vraci roky (kazdy max. jednou),
     * kdy ma tento zalobce alespon jeden zaznam v tabulce zalob
     * @param nazev zalobce
     * @return pole roku, kdy tento zalobce alespon jednou zaloval stat
     */
    public int[] rokyProZalobce(String nazev) { return null; }

    /**
     * Vraci nejen roky, ale i castky (resp soucet zalovanych castek v danem roce danym zalobcem)
     * @param nazev zalobce
     * @return Mapu, kde klicem jsou jednotlive roky, hodnotou pak soucet zalovanych castek
     * v danem roce (pro daneho zalobce, samozrejme)
     */
    public Map<Integer, Double> rokyACastkyProZalobce(String nazev) { return null; }

    /**
     * Pro zadaneho zalobce a rok vraci vsechny zalovane castky
     * @param nazev zalobce
     * @param rok (1999-2019)
     * @return pole vsech zalovanych castek daneho zalobce v danem roce
     */
    public double[] castkyZalobceARok(String nazev, int rok) { return null; }

    /**
     * Pro zadaneho zalobce vraci vsechny zalovane castky
     * @param nazev zalobce
     * @return pole vsech zalovanych castek
     */
    public double[] castkyZalobce(String nazev) { return null; }

    /**
     * Vraci souhrn zalovanych castek pres jednotlive zalobce jako mapu
     * @return Mapu, kde indexem je jmeno/nazev zalobce, hodnotou pak soucet jeho
     * zalovanych castek
     */
    public Map<String, Double> castkyZalobci() { return null; }

    /**
     * Vraci jmeno/nazev zalobce, ktery ma nejvetsi absolutni rozdil castek mezi prvnim a poslednim rokem,
     * ve kterem zaloval stat
     * @return jmeno/nazev zalobce
     */
    public String maxRozdil() { return ""; }

    /**
     * Vraci seznam zalob, jejichz zalovana castka spada do rozmezi urceneho parametry
     * @param minimum spodni mez rozsahu (vcetne)
     * @param maximum horni mez rozsahu (vcetne)
     * @return pole zalob, jejich castky vyhovuji zadanemu rozmezi
     */
    public Zaloba[] zalobyVRozmezi(double minimum, double maximum) { return null; }

    /**
     * Vraci mapu, kde ke kazdemu zalobci je prirazen pocet jeho zalob
     * @return Mapa, indexem je jmeno/nazev zalobce, hodnotou pocet jeho zalob vuci statu
     */
    public Map<String, Integer> poctyZalob() { return null; }

    /**
     * Vraci seznam vsech zalob, ktere vyhovuji zadani (viz dale):
     * a) obsahuji dany retezec ve jmenu/nazvu zalobce
     * b) je-li predan retezec odpovidajici cislu, vraci take vsechny zaloby v prislusnem roce
     * c) je-li predan retezec odpovidajici cislu, vraci take vsechny zaloby, jejich zalovane
     *    castky ZACINAJI nebo KONCI (pred desetinnou carkou/teckou) danymi cisly
     * Pokud by nejaky zaznam vyhovoval vice podminkam, bude ve vystupu uveden pouze jednou.
     * @param hledat retezec k hledani
     * @return pole vsech zalob, ktere vyhovuji alespon jednomu kriteriu
     */
    public Zaloba[] najdiZaloby(String hledat) { return null; }
}

public class CSVZalobci {
    public static final String CESTA_K_SOUBORU = "naklady_zastoupeni.csv";

    public static void main(String[] args) {
        Databaze baze = new Databaze();
        try {
            Scanner sc = new Scanner(new File(CESTA_K_SOUBORU));
            String[] popisky = sc.nextLine().split(",");
            while (sc.hasNextLine()) {
                String radek = sc.nextLine();
                String[] data = radek.split(",");
                int rok = Integer.parseInt(data[0]);
                String popis = data[1];
                double castka = Double.parseDouble(data[2]);
                baze.pridej(rok, popis, castka);
            }
            System.out.println("Data nactena ok.");
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        // vypis prumeru v jednotlivych letech:
        for (int rok = 1999; rok < 2023; rok++)
            System.out.printf("Rok %d - prumer %.2f\n", rok, baze.prumerZaRok(rok));
        // TODO: test dalsich metod
    }
}