package cz.braza.educanet;

import java.util.HashSet;

/**
 * Demonstrace kolekcí, různých typů, equals, hashCode, chyby a problémy
 */

class ObycClovek {
    public final String jmeno;
    public final int vek;
    public ObycClovek(String jmeno, int vek) {
        this.jmeno = jmeno;
        this.vek = vek;
    }

    /**
     * Nemám-li equals, porovnává se jen stejná adresa
     * Mám-li equals, musím mít i hashCode, jinak kolekce nefungují
     *
     * Equals má být:
     * 1) reflexivní (x.equals(x) == true)
     * 2) symetrická (x.equals(y) == y.equals(x))
     * 3) tranzitivní (x==y && y==z => x==z)
     *
     * @param oth
     * @return
     */
    public boolean equals(Object oth) {
        //System.out.println("Volam equals pro " + this + " a druhy objekt " + oth);
        if (oth == null || !(oth.getClass().getName().equals(getClass().getName()))) return false;
        ObycClovek other = (ObycClovek) oth;
        return jmeno.equals(other.jmeno) && vek == other.vek;
    }


    /**
     * Hloupá, ale funkční implementace hashCode
     * @return
     */
    public int hashCode() { return 42; }
}

class Programator extends ObycClovek {
    public final String jazyk;
    public Programator(String jm, int v, String prg) {
        super(jm, v);
        jazyk = prg;
    }

    @Override
    public boolean equals(Object oth) {
        if (oth == null || !(oth instanceof Programator)) return false;
        Programator other = (Programator) oth;
        return jmeno.equals(other.jmeno) && vek == other.vek && jazyk.equals(other.jazyk);
    }

}

public class Kolekce {
    public static void main(String[] args) {
        HashSet mnozina = new HashSet();
/*
        System.out.println("Počet prvků: " + mnozina.size());
        mnozina.add(new Osoba("Karel Novak", 42));
        System.out.println("Počet prvků: " + mnozina.size());
        mnozina.add(new Osoba("Karel Novak", 42));
        System.out.println("Počet prvků: " + mnozina.size());
        Osoba kno = new Osoba("Karel Novak", 42);
        mnozina.add(kno);
        System.out.println("Počet prvků: " + mnozina.size());
        mnozina.add(kno);
        System.out.println("Počet prvků: " + mnozina.size());

        Programator knp = new Programator("Karel Novak", 42, "Java");
        System.out.println("Programator.equals(Osoba): " + knp.equals(kno));
        System.out.println("Osoba.equals(Programator): " + kno.equals(knp));

*/
        ObycClovek kno = new ObycClovek("Karel Novak", 42);
        Programator knp = new Programator("Karel Novak", 42, "Java");
        System.out.println("Programator.equals(Osoba): " + knp.equals(kno));
        System.out.println("Osoba.equals(Programator): " + kno.equals(knp));

        mnozina.clear();
        System.out.println("Počet prvků: " + mnozina.size());
        mnozina.add(kno);
        System.out.println("Počet prvků: " + mnozina.size());
        mnozina.add(knp);
        System.out.println("Počet prvků: " + mnozina.size());

        mnozina.clear();
        System.out.println("Počet prvků: " + mnozina.size());
        mnozina.add(knp);
        System.out.println("Počet prvků: " + mnozina.size());
        mnozina.add(kno);
        System.out.println("Počet prvků: " + mnozina.size());

    }
}
