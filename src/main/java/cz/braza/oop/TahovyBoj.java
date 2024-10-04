package cz.braza.oop;

import java.util.Random;

class Kostka {

    private Random random;
    private int pocetSten;

    public Kostka(int sten) {
        pocetSten = sten;
        random = new Random();
    }

    public Kostka() {
        this(6);
    }

    public int vratPocetSten() {
        return pocetSten;
    }

    public int hod() {
        return random.nextInt(pocetSten) + 1;
    }
}

class Bojovnik {
    /**
     * Jméno bojovníka
     */
    private String jmeno;
    /**
     * Život v HP
     */
    private int zivot;
    /**
     * Maximální zdraví
     */
    private int maximalniZivot;
    /**
     * Útok v HP
     */
    private int utok;
    /**
     * Obrana v HP
     */
    private int obrana;
    /**
     * Instance hrací kostky
     */
    private Kostka kostka;
    /**
     * Poslední zpráva
     */
    private String zprava;

    private void nastavZpravu(String zprava) {
        this.zprava = zprava;
    }

    public String vratPosledniZpravu() {
        return zprava;
    }

    public Bojovnik(String jmeno, int zivot, int utok, int obrana, Kostka kostka) {
        this.jmeno = jmeno;
        this.zivot = zivot;
        this.maximalniZivot = zivot;
        this.utok = utok;
        this.obrana = obrana;
        this.kostka = kostka;
    }

    @Override
    public String toString() {
        return jmeno;
    }

    public boolean jeZivy() {
        return zivot > 0;
    }

    public String grafickyZivot() {
        String grafickyZivot = "[";
        int celkem = 20;
        double pocetDilku = Math.round(((double) zivot / maximalniZivot) * celkem);
        if ((pocetDilku == 0) && (jeZivy())) {
            pocetDilku = 1;
        }
        for (int i = 0; i < pocetDilku; i++) {
            grafickyZivot += "#";
        }
        for (int i = 0; i < celkem - pocetDilku; i++) {
            grafickyZivot += " ";
        }
        grafickyZivot += "]";
        return grafickyZivot;
    }

    public void branSe(int uder) {
        int zraneni = uder - (obrana + kostka.hod());
        String zprava;
        if (zraneni > 0) {
            zivot -= zraneni;
            zprava = String.format("%s utrpěl poškození %s hp", jmeno, zraneni);
            if (zivot <= 0) {
                zivot = 0;
                zprava += " a zemřel";
            }
        } else {
            zprava = String.format("%s odrazil útok", jmeno);
        }
        nastavZpravu(zprava);
    }

    public void utoc(Bojovnik souper) {
        int uder = utok + kostka.hod();
        nastavZpravu(String.format("%s útočí s úderem za %s hp", jmeno, uder));
        souper.branSe(uder);
    }
}

class Arena {
    private Bojovnik bojovnikA;
    private Bojovnik bojovnikB;
    private Kostka kostka;

    public Arena(Bojovnik bojovnikA, Bojovnik bojovnikB, Kostka kostka) {
        this.bojovnikA = bojovnikA;
        this.bojovnikB = bojovnikB;
        this.kostka = kostka;
    }
    private void vykresli() {
        System.out.println("-------------- Aréna -------------- \n");
        System.out.println("Zdraví bojovníků: \n");
        System.out.printf("%s %s%n", bojovnikA, bojovnikA.grafickyZivot());
        System.out.printf("%s %s%n", bojovnikB, bojovnikB.grafickyZivot());
    }
    private void vypisZpravu(String zprava) {
        System.out.println(zprava);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            System.err.println("Chyba, nepodařilo se uspat vlákno!");
        }
    }
    public void zapas() {
        // původní pořadí
        Bojovnik bojovnikA = this.bojovnikA;
        Bojovnik bojovnikB = this.bojovnikB;
        System.out.println("Vítejte v aréně!");
        System.out.printf("Dnes se utkají %s s %s! %n%n", bojovnikA, bojovnikB);
        // prohození bojovníků
        boolean zacinaBojovnikB = (kostka.hod() <= kostka.vratPocetSten() / 2);
        if (zacinaBojovnikB) {
            bojovnikA = this.bojovnikB;
            bojovnikB = this.bojovnikA;
        }
        System.out.printf("Začínat bude bojovník %s! %nZápas může začít...%n", bojovnikA);

        // cyklus s bojem
        while (bojovnikA.jeZivy() && bojovnikB.jeZivy()) {
            bojovnikA.utoc(bojovnikB);
            vykresli();
            vypisZpravu(bojovnikA.vratPosledniZpravu()); // zpráva o útoku
            vypisZpravu(bojovnikB.vratPosledniZpravu()); // zpráva o obraně
            if (bojovnikB.jeZivy()) {
                bojovnikB.utoc(bojovnikA);
                vykresli();
                vypisZpravu(bojovnikB.vratPosledniZpravu()); // zpráva o útoku
                vypisZpravu(bojovnikA.vratPosledniZpravu()); // zpráva o obraně
            }
            System.out.println();
        }
    }
}

public class TahovyBoj {
    public static void main(String[] args) {
        // vytvoření objektů
        Kostka kostka = new Kostka(10);
        Bojovnik zalgoren = new Bojovnik("Zalgoren", 100, 20, 10, kostka);
        Bojovnik shadow = new Bojovnik("Shadow", 60, 18, 15, kostka);
        Arena arena = new Arena(zalgoren, shadow, kostka);
        // zápas
        arena.zapas();
    }
}
