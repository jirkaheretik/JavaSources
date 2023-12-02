package cz.braza.educanet;

/**
 * Short program to show why it is dangerous to compare strings by equality operator (==)
 * and a couple of ways to work around the issue.
 *
 * Strings are immutable, interned by compile time.
 * While you build string in a program, it may be allocated elsewhere and equality
 * comparison thus fails.
 *
 * We can either intern strings before comparing, or (preferred) use their equals() method, which
 * actually compares content.
 */

public class StringComparations {
    private static void printRes(boolean test) {
        System.out.println(test ? "This is OK" : "Problem");
    }
    public static void main(String[] args) {
        String yes = "ANO";
        String yesSmall = "ano";
        String yesBits = "A" + "N" + "O";
        String a = "A";
        String n = "N";
        String o = "O";
        printRes(yes == yesBits);
        System.out.println("===================================");
        printRes(yes == yesSmall.toUpperCase());
        printRes(yes == (a + n + o));
        printRes(yesSmall.toUpperCase() == (a + n + o));
        System.out.println("===================================");
        printRes(yes.equals(yesSmall.toUpperCase()));
        printRes(yes.equals(a + n + o));
        printRes(yesSmall.toUpperCase().equals(a + n + o));
        System.out.println("===================================");

        printRes(yes == yesSmall.toUpperCase().intern());
        printRes(yes == (a + n + o).intern());
        printRes(yesSmall.toUpperCase().intern() == (a + n + o).intern());
    }
}
