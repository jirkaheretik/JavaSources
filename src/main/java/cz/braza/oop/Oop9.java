package cz.braza.oop;

class Prevodnik {
    public static float toDegrees(double rad) {
        return (float)Math.toDegrees(rad);
    }

    public static float toRadians(double deg) {
        return (float)Math.toRadians(deg);
    }
}

class Cukrovi {
    private String barva;
    private String tvar;
    private int vaha;
    public Cukrovi(String barva, String tvar, int vaha) {
        this.barva = barva;
        this.tvar = tvar;
        this.vaha = vaha;
    }
    @Override
    public String toString() {
        return String.format("Cukroví barvy %s, tvaru %s a váhy %dg", barva, tvar, vaha);
    }
}

class TovarnaNaCukrovi {
    public static Cukrovi vyrobBananove() {
        return new Cukrovi("žlutá", "kulatý", 20);
    }

    public static Cukrovi vyrobJahodove() {
        return new Cukrovi("červená", "kulatý", 15);
    }

    public static Cukrovi vyrobCokoladove() {
        return new Cukrovi("hnědá", "hranatý", 25);
    }
}

/*
Singleton:
 */
class Databaze {
    private static Databaze inst = null;
    private String typ;
    private Databaze() {
        typ = "MySQL";
    }
    public String getTyp() { return typ; }
    public static Databaze vratInstanci() {
        if (inst == null)
            inst = new Databaze();
        return inst;
    }
}

public class Oop9 {
    public static void easy() {
        double radVal = 6.28f;
        double degVal = 90f;
        System.out.println(radVal + " radiánů na stupně: " + Prevodnik.toDegrees(radVal));
        System.out.println("90 stupňů na radiány: " + Prevodnik.toRadians(degVal));
    }

    public static void middle() {
        for (int i = 0; i < 5; i++) {
            System.out.println(TovarnaNaCukrovi.vyrobBananove());
        }
        for (int i = 0; i < 8; i++) {
            System.out.println(TovarnaNaCukrovi.vyrobJahodove());
        }
        for (int i = 0; i < 12; i++) {
            System.out.println(TovarnaNaCukrovi.vyrobCokoladove());
        }
    }

    public static void hard() {
        System.out.println(Databaze.vratInstanci().getTyp());
    }

    public static void main(String[] args) {
        middle();
    }
}
