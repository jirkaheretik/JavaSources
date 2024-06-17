package cz.braza.educanet;

interface Obrazec {
    double spoctiObsah();
    double spoctiObvod();
}

class Ctverec implements Obrazec {
    private String nazev;
    private double hrana;

    public Ctverec(String nazev, double hrana) {
        this.nazev = nazev;
        setHrana(hrana);
    }

    public void setHrana(double hrana) {
        this.hrana = hrana > 0 ? hrana : -hrana;
    }

    @Override
    public double spoctiObsah() {
        return hrana * hrana;
    }

    @Override
    public double spoctiObvod() {
        return 4 * hrana;
    }

    @Override
    public String toString() {
        return "Čtverec " + nazev + " s hranou " + hrana;
    }
}

public class PetiminutovkyKveten {
    public static double mocnina(double base, int exp) {
        return exp < 0 ? mocnina(1/base, -exp) : exp == 0 ? 1 : base * mocnina(base, exp - 1);
    }

    public static String obratSlovaVRetezci(String vstup) {
        String[] slova = vstup.split(" ");
        for (int idx = 0; idx < slova.length; idx++)
            slova[idx] = obratSlovo(slova[idx]);
        return String.join(" ", slova);
    }

    /**
     * Funkce obrátí jednotlivá slova v řetězci
     * @param text vstupní text k obrácení slov
     * @return řetězec s obrácenými slovy
     */
    public static String obratSlovo(String text) {
        String obracene = "";
        for (int idx = text.length() - 1; idx >= 0; idx--)
            obracene += text.charAt(idx);
        return obracene;
    }

    public static void main(String[] args) {
        System.out.println(mocnina(0.25, -7));
        System.out.println(obratSlovaVRetezci("Karel   nese  10     polen."));
        Ctverec koso = new Ctverec("Kára", -27.14159);
        koso.setHrana(-4 * Math.PI);
        System.out.println(koso);
    }
}