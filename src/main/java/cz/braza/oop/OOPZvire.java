package cz.braza.oop;
class Zvire {
    protected int vaha;
    public Zvire(int vaha) { this.vaha = vaha; }
    public boolean leta() { return vaha < 9; }
    public String vydejZvuk() { return ""; }
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

class Kocka extends Zvire {
    private int pocetZivotu;
    public Kocka(int vaha, int zivoty) {
        super(vaha);
        pocetZivotu = zivoty;
    }
    @Override
    public String vydejZvuk() { return "Mňau mňaaauuu. "; }
}

public class OOPZvire {
    public static void main(String[] args) {
        Zvire[] zoo = new Zvire[5];
        zoo[0] = new Kocka(7, 3);
        zoo[1] = new Pes(12, true);
        Pes alik = new Pes(22, false);
        zoo[2] = alik;
        zoo[3] = new Kocka(4, 9);
        Zvire vrana = new Zvire(8);
        zoo[4] = vrana;

        for (Zvire zvire: zoo) {
            zvire.vydejZvuk();
            System.out.println(zvire);
        }
    }
}
