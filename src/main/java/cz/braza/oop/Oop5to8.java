package cz.braza.oop;

class Ptak {
    protected int hlad;
    protected int vaha;
    public Ptak() {
        hlad = 100;
        vaha = 50;
    }
    public void jez(int mnozstvi) {
        if (mnozstvi > 0) {
            hlad -= mnozstvi;
            vaha += mnozstvi;
        }
    }
    public String toString() {
        return String.format("Jsem pták s váhou %d a hladem %d.", vaha, hlad);
    }
}

class AngryPtak extends Ptak {
    private int miraProvokace;
    public AngryPtak() {
        super();
        miraProvokace = 50;
    }
    public void provokuj(int prirustek) {
        miraProvokace += (hlad > 0 ? 2 : 1) * prirustek;
    }
    public String toString() {
        return String.format("Jsem angry-pták s váhou %d a hladem %d, míra mého vzteku je %d.", vaha, hlad, miraProvokace);
    }
}

class Javista extends Clovek {
    protected String ide;
    public Javista(String jmeno, int vek, String ide) {
        super(jmeno, vek);
        this.ide = ide;
    }
    public void programuj(int pocetRadku) {
        unava += (double) pocetRadku / 10;
        if (unava > 20) {
            unava = 20;
            System.out.println("Jsem příliš unavený, abych programoval");
        }

    }
}

class Obrazec {
    protected String barva;
    public Obrazec(String barva) {
        this.barva = barva;
    }
    public double spoctiObsah() { return 0; }
}

class Obdelnik extends Obrazec {
    protected double a;
    protected double b;
    public Obdelnik(String barva, double a, double b) {
        super(barva);
        this.a = a;
        this.b = b;
    }
    @Override
    public double spoctiObsah() {
        return a * b;
    }
}

class Trojuhelnik extends Obrazec {
    protected double a;
    protected double b;
    protected double c;
    public Trojuhelnik(String barva, double a, double b, double c) {
        super(barva);
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Override
    public double spoctiObsah() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}

public class Oop5to8 {
    public static void easy() {
        Ptak ab = new Ptak();
        System.out.println(ab);
        ab.jez(20);
        System.out.println(ab);
        AngryPtak bb = new AngryPtak();
        System.out.println(bb);
        bb.provokuj(5);
        System.out.println(bb);
        bb.jez(100);
        bb.provokuj(5);
        System.out.println(bb);
    }

    public static void middle() {
        Javista karel = new Javista("Karel Nový", 25, "IntelliJ IDEA");
        System.out.println(karel);
        karel.behej(10);
        karel.behej(10);
        karel.programuj(5);
        karel.behej(10);
        karel.spi(8);
        karel.programuj(100);
    }

    public static void hard() {
        double obsahStromu = 0;
        for (int i = 0; i < 4; i++)
            obsahStromu += new Trojuhelnik("zelený", 25, 15, 15).spoctiObsah();
        obsahStromu += new Obdelnik("hnědý", 3, 26).spoctiObsah();
        System.out.println("Obsah stromu je " + obsahStromu + " cm^2");
    }

    public static void main(String[] args) {
        hard();
    }
}
