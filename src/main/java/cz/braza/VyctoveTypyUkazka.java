package cz.braza;
interface Vypocet {
    int umocni();
}

enum Vycet implements Vypocet {
    BOURAK(3), TRUBKA(42), SROUB(11), OHYBAK(-1);
    int val;
    Vycet(int value) { val = value;}
    public void setValue(int newVal) { val = newVal; }

    @Override
    public String toString() {
        return super.toString() + "(" + val + ")";
    }

    @Override
    public int umocni() {
        return val * val;
    }
}

enum JinyVycet implements Vypocet {
    X1 { public int umocni() { return 36; }} ,
    X2 { public int umocni() { return 49; }},
    X3 { public int umocni() { return 121; }}
}

class Pokus {
    String jmeno;
    Vycet hodnota;
    public Pokus(String jm, Vycet v) {
        jmeno = jm; hodnota = v;
    }
    public String toString() {
        return "Pokus '" + jmeno + "' s hodnotou " + hodnota;
    }
}

public class VyctoveTypyUkazka {
    public static void main(String[] args) {
        Vycet promenna = Vycet.OHYBAK;

        Pokus inst = new Pokus("Prvni", promenna);
        System.out.println(inst);
        promenna.setValue(promenna.umocni());
        System.out.println(inst);

        Vypocet novyVypocet = Vycet.BOURAK;
        novyVypocet = JinyVycet.X3;
        novyVypocet = new Vypocet() {
            @Override
            public int umocni() {
                return 888;
            }
        };
    }
}
