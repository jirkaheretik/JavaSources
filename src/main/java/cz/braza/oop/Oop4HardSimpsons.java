package cz.braza.oop;

class Osoba {
    Osoba otec;
    Osoba matka;
    String jmeno;
    public Osoba(String name, Osoba father, Osoba mother) {
        jmeno = name;
        otec = father;
        matka = mother;
    }
    public void printTree() {
        System.out.println(jmeno);
        if (otec != null) otec.printTree();
        if (matka != null) matka.printTree();
    }
}

class Rodokmen {
    private Osoba koren;
    public Rodokmen(Osoba root) {
        koren = root;
    }
    public void tiskniRodokmen() {
        System.out.println("Rodokmen pro osobu " + koren.jmeno);
        koren.printTree();
        System.out.println();
    }
}

public class Oop4HardSimpsons {
    public static void main(String[] args) {

        // Vytvoření a navázání osob
        Osoba abraham = new Osoba("Abraham Simpson", null, null);
        Osoba penelope = new Osoba("Penelope Olsen", null, null);
        Osoba pan = new Osoba("Pan Bouvier", null, null);
        Osoba jackie = new Osoba("Jackie Bouvier", null, null);
        Osoba herb = new Osoba("Herb Powers", abraham, penelope);
        Osoba homer = new Osoba("Homer Simpson", abraham, penelope);
        Osoba marge = new Osoba("Marge Bouvier", pan, jackie);
        Osoba selma = new Osoba("Selma Bouvier", pan, jackie);
        Osoba bart = new Osoba("Bart Simpson", homer, marge);

        // Zde dokonči úlohu svým kódem...
        Rodokmen rodokmen = new Rodokmen(bart);
        rodokmen.tiskniRodokmen();
        rodokmen = new Rodokmen(homer);
        rodokmen.tiskniRodokmen();
    }
}
