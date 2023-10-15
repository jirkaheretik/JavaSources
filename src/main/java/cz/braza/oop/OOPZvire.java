package cz.braza.oop;

enum Barva { CERVENA, MODRA, FIALOVA }

abstract class Zvire {
    protected int vaha;
    public Zvire(int vaha) { this.vaha = vaha; }
    public boolean leta() { return vaha < 9; }
    abstract public String vydejZvuk();

    @Override
    public boolean equals(Object obj) {
        System.out.println("Equals ze Zvirete");
        if (obj instanceof Zvire) {
            Zvire other = (Zvire) obj;
            return vaha == other.vaha;
        } else
        return super.equals(obj);
    }
/*
    @Override
    public String toString() {
        return "Jsem " + getClass().getSimpleName() + ", vážím " + vaha + " kg, " +
                (leta() ? "" : "ne") + "létám a dělám " + vydejZvuk();
    }

     */
}

class Pes extends Zvire {
    private boolean obojek;
    public Pes(int vaha, boolean maObojek) {
        super(vaha);
        obojek = maObojek;
    }
    public boolean maObojek() { return obojek; }
    @Override
    public String vydejZvuk() { return "Haf haf! "; }

}

class Kocka extends Zvire implements Cloneable {
    private int pocetZivotu;
    public Kocka(int vaha, int zivoty) {
        super(vaha);
        pocetZivotu = zivoty;
    }
    @Override
    public String vydejZvuk() { return "Mňau mňaaauuu. "; }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Equals z Kocky");
        if (obj instanceof Kocka) {
            Kocka other = (Kocka) obj;
            return vaha == other.vaha && pocetZivotu == other.pocetZivotu;
        } else
            return super.equals(obj);
    }
}

public class OOPZvire {
    public static void main(String[] args) {
        Zvire[] zoo = new Zvire[5];
        zoo[0] = new Kocka(7, 3);
        zoo[1] = new Pes(12, true);
        Pes alik = new Pes(22, false);
        zoo[2] = alik;
        zoo[3] = new Kocka(4, 9);
        Zvire vrana = new Zvire(8) {
            @Override
            public String vydejZvuk() {
                return "Nic :-)";
            }
        };
        zoo[4] = vrana;

        for (Zvire zvire: zoo) {
            System.out.println(zvire.vydejZvuk());
            System.out.println(zvire);
        }


        /*
        Zvire kos = new Zvire(4);

        Zvire micka = new Kocka(4, 7);
        System.out.println(micka.equals(kos));
        System.out.println(kos.equals(micka));

         */
    }
}
