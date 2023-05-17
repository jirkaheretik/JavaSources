package cz.braza;

import java.util.Arrays;

public class Semafor {
    enum Barvy { CERVENA, ZLUTA, ZELENA};
    static final Barvy[] B_STUJ = { Barvy.CERVENA };
    static final Barvy[] B_PRIPRAVIT = { Barvy.CERVENA, Barvy.ZLUTA };
    static final Barvy[] B_JED = { Barvy.ZELENA };
    static final Barvy[] B_POZOR = { Barvy.ZLUTA };
    enum Stavy { STUJ, PRIPRAVIT, JED, POZOR};

    private Stavy current;
    public Semafor() {
        current = Stavy.STUJ;
    }
    public Semafor(Stavy pocatek) {
        current = pocatek;
    }

    public Stavy getState() { return current; }

    public Stavy next() {
        return switch(current) {
           case STUJ -> Stavy.PRIPRAVIT;
           case PRIPRAVIT -> Stavy.JED;
           case JED -> Stavy.POZOR;
           case POZOR -> Stavy.STUJ;
        };
    }

    public void doNext() {
        current = next();
    }

    public Barvy[] sviti() {
        return switch(current) {
            case STUJ -> B_STUJ;
            case PRIPRAVIT -> B_PRIPRAVIT;
            case JED -> B_JED;
            case POZOR -> B_POZOR;
        };
    }

    public boolean svitiBarva(Barvy barva) {
        return Arrays.asList(sviti()).contains(barva);
    }
}
