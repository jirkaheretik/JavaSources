package cz.braza;

public class ObjektyTestStatiky {
    public static void pokus() {
        System.out.println("Tohle je pokus");
    }
    public void dynamickyPokus() {
        System.out.println("Tohle je v poho.");
        this.pokus();
    }

    public static void main(String[] args) {
        ObjektyTestStatiky o = new ObjektyTestStatiky();
        ObjektyTestStatiky.pokus();
        o.dynamickyPokus();
        o.pokus();

    }
}
