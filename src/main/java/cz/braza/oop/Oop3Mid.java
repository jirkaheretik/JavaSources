package cz.braza.oop;


import java.util.Random;

class GeneratorVet {
    public static final String[] PODMETY = {"jednorožec", "programátor", "manažer", "hroch", "T-rex"};
    public static final String[] PRISUDKY = { "spal", "ležel", "vařil", "uklízel", "derivoval"};
    public static final String[] PRIVLASTKY = {"modrý", "velký", "hubený", "nejlepší", "automatizovaný"};
    public static final String [] PRISLOVCE = {"rychle", "s oblibou", "hodně", "málo", "se zpožděním"};
    public static final String[] PUM = {"pod stolem", "v lese", "u babičky", "v práci", "na stole"};
    public static final Random rnd = new Random();

    public static String dejSlovo(String[] pole) {
        return pole[rnd.nextInt(pole.length)];
    }

    public static String generujVetu() {
        return dejSlovo(PRIVLASTKY) + " " + dejSlovo(PODMETY) + " " + dejSlovo(PRISLOVCE) + " " + dejSlovo(PRISUDKY) + " " + dejSlovo(PUM);
    }
}
public class Oop3Mid {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            System.out.println(GeneratorVet.generujVetu());
    }
}
