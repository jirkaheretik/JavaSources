package cz.braza.oop;

class Auto {
    private String spz;
    private String barva;
    public Auto(String s, String b) {
        spz = s;
        barva = b;
    }
    public String toString() {
        return spz;
    }
    public void zaparkuj(Garaz g) {
        g.zaparkuj(this);
    }
}

class Garaz {
    private Auto zaparkovane;
    public void zaparkuj(Auto kara) {
        zaparkovane = kara;
    }
    public String toString() {
        if (zaparkovane == null)
            return "Prázdná garáž";
        else
            return "V garáži je auto: " + zaparkovane;
    }
}

public class Oop3Hard {
    public static void main(String[] args) {
        Auto car = new Auto("123ABC", "green");
        Garaz tunel = new Garaz();
        // System.out.println(tunel);
        car.zaparkuj(tunel);
        System.out.println(tunel);
    }
}

