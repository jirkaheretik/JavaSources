package cz.braza.oop;

import java.util.Scanner;

class Clovicek {
    private String jmeno;
    private int vek;
    private String kamarad;
    public Clovicek(String jm, int age) {
        jmeno = jm;
        vek = age;
    }
    public void skamaradit(String kamos) { kamarad = kamos;}
    public String toString() {
        return "Ahoj, já jsem " + jmeno + ", je mi " + vek + " let a můj kamarád je " + kamarad;
    }
}
class NakladniAuto {
    public static final int NOSNOST = 3000;
    private int naklad = 0;
    public NakladniAuto() {}
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
    public double vydel() { return prvni / druhe; }
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
        */

        /* ** OOP Mid - Nakladni auto
        NakladniAuto auto = new NakladniAuto();
        auto.naloz(10000);
        auto.naloz(500);
        auto.vyloz(300);
        auto.vyloz(1000);
        System.out.println(auto);
        */

        /* ** OOP Hard - lide, kamaradi */
        String jm1 = "Karel Novák";
        String jm2 = "Cyril Nový";
        Clovicek karel = new Clovicek(jm1, 33);
        Clovicek cyril = new Clovicek(jm2, 27);
        karel.skamaradit(jm2);
        cyril.skamaradit(jm1);
        System.out.println(karel);
        System.out.println(cyril);

    }
}
