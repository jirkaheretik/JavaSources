package cz.braza.zaklady;

import java.util.Scanner;

public class Skoleni {

    public static final Scanner sc = new Scanner(System.in, "utf-8");
    public static final String[] DNY = {"neděle", "pondělí", "úterý", "středa", "čtvrtek", "pátek", "sobota"};

    private static void template() {
        System.out.println("Hello World!");
    }

    private static void kalkulacka() {

        System.out.println("Kalkulačka\n==========\nZadejte první číslo: ");
        double prvniCislo = Double.parseDouble(sc.nextLine());
        System.out.println("Zadejte druhé číslo:");
        double druheCislo = Double.parseDouble(sc.nextLine());

        System.out.println("Součet: " + (prvniCislo + druheCislo));
        System.out.println("Rozdíl: " + (prvniCislo - druheCislo));
        System.out.println("Součin: " + (prvniCislo * druheCislo));
        System.out.println("Podíl: " + (prvniCislo / druheCislo));

    }
    public static void main(String[] args) {
        // komentáře

        /* víceřádkové
        komentáře
        :-D
         */

//         template();
//         kalkulacka();
//        retezceUkol();
//        podminky();
        //nasobilka();
        //sachovnice();
        //pole();

        // pokus - pretty print pole:
        System.out.printf("[%s]\n", String.join(", ", DNY));

        //denVTydnu(4);
        //System.out.println(prumer("13456,476576,67000, 50006, 12000, 12455"));

    }

    private static void denVTydnu(int den) {
        System.out.println(DNY[den % DNY.length]);
    }

    private static double prumer(String text) {
        String[] castky = text.split(",");
        int soucet = 0;
        for (String castka : castky) {
            soucet += Integer.parseInt(castka.trim());
        }
        return (double) soucet / castky.length;
    }

    private static void pole() {

    }

    private static void sachovnice() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                if (((i + j) % 2) == 0)
                    System.out.print("  ");
                else
                    System.out.print("XX");
            System.out.println();
        }
    }

    private static void nasobilka() {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++)
                System.out.print((i * j) + "\t");
            System.out.println();
        }
    }

    private static void podminky() {
        System.out.println("Zadejte první číslo:");
        int prvni = Integer.parseInt(sc.nextLine());
        System.out.println("Zadejte druhé číslo:");
        int druhe = Integer.parseInt(sc.nextLine());
        if (prvni > druhe)
            System.out.println("První je větší");
        else if (prvni == druhe)
            System.out.println("Obě jsou stejná");
        else
            System.out.println("Druhé je větší");
        System.out.println("Nyní zadejte libovolný text:");
        String veta = sc.nextLine();
        if (veta.trim() == veta.toUpperCase().trim())
            System.out.println("Řetězec je celý napsaný velkými písmeny.");
        else
            System.out.println("Tvůj řetězec zřejmě obsahuje i malá písmena.");

    }

    private static void retezceUkol() {
        String vstup = " C++ je %d KRÁT lepší! ";
        String vystup = vstup.trim().toLowerCase().replace("c++", "Java");
        System.out.println(vystup.startsWith("Java"));
        System.out.println(vystup.contains("krát"));
        System.out.printf(vystup, vystup.length() * 3);
    }
}
