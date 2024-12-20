package cz.braza.oop;

class Clovek {
    String jmeno;
    int vek;
    double unava = 0;

    public Clovek(String jm, int v) {
        jmeno = jm;
        vek = v;
    }

    public void behej(int km) {
        unava += km;
        if (unava > 20) {
            unava = 20;
            System.out.println("Jsem příliš unavený");
        }
    }

    public void spi(int hodin) {
        unava -= 10 * hodin;
        if (unava < 0)
            unava = 0;
    }

    public String toString() {
        return jmeno + " (" + vek + ")";
    }
}
public class Oop3Easy {
    public static void main(String[] args) {
        Clovek karel = new Clovek("Karel Nový", 25);
        System.out.println(karel);
        for (int i = 0; i < 3; i++)
            karel.behej(10);
    }
}
