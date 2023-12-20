package cz.braza.oop;

import java.util.Scanner;

class Clovicek {

    private static int pocetLidi = 0;
    private String jmeno;
    private int vek;
    private String kamarad;
    public Clovicek(String jm, int age) {
        jmeno = jm;
        vek = age;
        Clovicek.prictiJednoho();
    }

    public Clovicek() {
        this("Neznámý", 0);
    }
    public void skamaradit(String kamos) { kamarad = kamos;}

/*
    @Override
    public String toString() {
        return "Ahoj, já jsem " + jmeno + ", je mi " + vek + " let a můj kamarád je " + kamarad;
    }

 */
    @Override
    public boolean equals(Object other) {
        if (other instanceof  Clovicek) {
            Clovicek druhy = (Clovicek) other;
            return jmeno.equals(druhy.jmeno) && vek == druhy.vek;
        } else return false;
    }

    private static  void prictiJednoho() { pocetLidi++; }
    public static int getPocetLidi() { return pocetLidi; }

}

class Programator extends Clovicek {
    public Programator(String jm, int v, String lang) {
        super(jm, v);
        jazyk = lang;
    }
    private String jazyk;
    public String getJazyk() { return jazyk; }
}

class NakladniAuto {
    public static final int NOSNOST = 3000;
    private int naklad = 0;
    public void naloz(int hmotnost) {
        if (hmotnost >= 0 && hmotnost + naklad <= NOSNOST)
            naklad += hmotnost;
    }
    public void vyloz(int hmotnost) {
        if (hmotnost >= 0 && hmotnost <= naklad)
            naklad -= hmotnost;
    }

    public String toString() {
        return "V nákladním autě je naloženo " + naklad + " kg";
    }
}

class Kalkulacka {
    private double prvni;
    private double druhe;
    public Kalkulacka(double first, double second) {
        prvni = first;
        druhe = second;
    }
    public double secti() { return prvni + druhe; }
    public double odecti() { return prvni - druhe; }
    public double vynasob() { return prvni * druhe; }
    public double vydel() {
        if (druhe == 0)
            throw new ArithmeticException("Nulou dělit nelze!");
        return prvni / druhe;
    }

    /**
     * Později přidaná metoda pro celočíselné dělení, zejména pro ilustraci
     * vzniku výjimky, protože dělení dvou double čísel (metoda @see Kalkulacka.vydel)
     * hází @see Infinity namísto výjimky.
     * @param a Dělenec
     * @param b Dělitel
     * @return Celočíselný podíl
     * @throws ArithmeticException
     */
    public static int vydelCelociselne(int a, int b) { return a / b; }

    /**
     * Později přidaná statická metoda, lépe testovatelná než původní kalkulačka
     * @param a první činitel
     * @param b druhý činitel
     * @return součet obou čísel
     */
    public static double secti(double a, double b) { return a + b; }
}

class Zviratko {
    private int vaha;
    public Zviratko(int vaha) {
        this.vaha = vaha;
    }
    public boolean leta() { return vaha < 9; }

    public void nakrm(int jidlo) {
        if (jidlo > 0) vaha += jidlo;
    }

    @Override
    public String toString() {
        return "Vážím " + vaha + " kg a " + (leta()? "létám": "nelétám" ) ;
    }

    public void vypis() {
        System.out.println(this);
    }

}
public class Oop2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, "utf8");
        /* **OOP easy - Kalkulacka
        System.out.println("Zadej 1. číslo:");
        double prvni = Double.parseDouble(sc.nextLine());
        System.out.println("Zadej 2. číslo:");
        double druhe = Double.parseDouble(sc.nextLine());
        Kalkulacka calc = new Kalkulacka(prvni, druhe);
        System.out.println("Součet: " + calc.secti());
        System.out.println("Rozdíl: " + calc.odecti());
        System.out.println("Součin: " + calc.vynasob());
        System.out.println("Podíl: " + calc.vydel());
        System.out.println("Celočíselný podíl: " + calc.vydelCelociselne((int) prvni, (int) druhe));
        */

        /* ** OOP Mid - Nakladni auto
        NakladniAuto auto = new NakladniAuto();
        auto.naloz(20000);
        auto.vyloz(6000);
        auto.naloz(500);
        auto.vyloz(300);
        auto.naloz(5000);
        auto.vyloz(1000);
        System.out.println(auto);
         */

        /* ** OOP Hard - lide, kamaradi
        String jm1 = "Karel Novák";
        String jm2 = "Cyril Nový";
        Clovicek karel = new Clovicek(jm1, 33);
        Clovicek cyril = new Clovicek(jm2, 27);
        karel.skamaradit(jm2);
        cyril.skamaradit(jm1);
        System.out.println(karel);
        System.out.println(cyril);
*/

        Clovicek karel = new Clovicek("Karel", 42);
        karel = new Clovicek("Kája", 20);
        karel = new Clovicek("Petr", 13);
        System.out.println(Clovicek.getPocetLidi());
        //System.out.println(karel);

        Clovicek josef = new Programator("Josef", 39, "Java");
        if (josef instanceof Programator)
            System.out.println( ((Programator)josef).getJazyk() );
        System.out.println(karel);
        //System.out.println(karel == inst2);
        //System.out.println(karel.equals(inst2));
        //System.out.println(inst2.equals(inst2));
        //karel = new Clovicek("Josef", 39);
        //karel = new Clovicek("Josef", 39);

        //System.out.println(Clovicek.getPocetLidi());


/*
        double a = 42;
        double b = 0;
        System.out.println(a / b);

 */

        /* OOP Zviratko */
        //Zviratko vrana = new Zviratko(5);
        //vrana.vypis();
        //vrana.nakrm(4);
        //vrana.vypis();

        //System.out.println(vrana);
/*
        Kalkulacka k = new Kalkulacka(-15, 0);
        System.out.println(k.vydel());
        System.out.println(Kalkulacka.vydelCelociselne(15, 5));
        System.out.println(Kalkulacka.vydelCelociselne(15, 0));

 */
    }
}
