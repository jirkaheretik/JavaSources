package cz.braza.oop;

public class StatikaVYtvareni {
    private static int pocet = 42;

    public StatikaVYtvareni() {
        System.out.println("Volám konstruktor!!!");
    }
    public static void main(String[] args) {
        System.out.println("Program.");
        System.out.println(StatikaVYtvareni.pocet);
        pocet += 11;
        System.out.println(pocet);
        StatikaVYtvareni foo = new StatikaVYtvareni();
        foo.pocet += 33;
        System.out.println(pocet);
        System.out.println(foo.pocet);
        StatikaVYtvareni another = new StatikaVYtvareni();
        System.out.println(another.pocet);
        another.pocet = 3;
        System.out.println(foo.pocet);
    }
}
