package cz.braza.zaklady;

import java.util.Scanner;

public class Zaklady17 {

    public static final String BASECHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void easyChessboard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                System.out.print(((i+j) % 2 == 0) ? "██" : "  ");
            System.out.println("");
        }
    }

    public static String dec2something(int value, int base) {
        String result = "";
        while (value > 0) {
            result = BASECHARS.charAt(value % base) + result;
            value /= base;
        }
        return result == "" ? "0" : result;
    }
    public static void testDec2Smthg() {
        int[] values = {0, 2023, 6378, 2145665};
        int[] bases = {2, 16, 27, 5};
        for (int val: values)
            for (int base: bases)
                System.out.println(val + " in base " + base + " is " + dec2something(val, base));
    }

    public static void middleSoustavy() {
        Scanner sc = new Scanner(System.in, "utf8");
        System.out.print("Číslo v desítkové soustavě: ");
        int cislo = Integer.parseInt(sc.nextLine());
        System.out.print("Číselná soustava (2-16): ");
        int soustava = Integer.parseInt(sc.nextLine());
        System.out.println("Císlo ve zvolené soustavě: " + dec2something(cislo, soustava));
    }

    public static int[] bubbleSort(int[] vstup) {
        boolean bylaAkce = true;
        while (bylaAkce) {
            bylaAkce = false;
            for (int i = 0; i < vstup.length - 1; i++)
                if (vstup[i] > vstup[i + 1]) {
                    int dummy = vstup[i];
                    vstup[i] = vstup[i + 1];
                    vstup[i + 1] = dummy;
                    bylaAkce = true;
                }
        }
        return vstup;
    }

    public static void hardSorting() {
        Scanner sc = new Scanner(System.in, "utf8");
        System.out.println("Zadej čísla pro seřazení (oddělená čárkou):");
        String[] cisla = sc.nextLine().split(",");
        int[] poleCisel = new int[cisla.length];
        for (int i = 0; i < cisla.length; i++)
            poleCisel[i] = Integer.parseInt(cisla[i].trim());
        System.out.println("Seřazená čísla:");
        int[] serazenePole = bubbleSort(poleCisel);
        System.out.print(serazenePole[0]);
        for (int i = 1; i < serazenePole.length; i++)
            System.out.print(", " + serazenePole[i]);
        System.out.println("");
        //System.out.println(String.join(", ", serazenePole));
    }

    public static void hardItNetwork() {

        int[] pole;
        String[] poleRetezcu;
        String zadanyRetezec;

        Scanner sc = new Scanner(System.in);


        System.out.println("Zadej čísla pro seřazení (oddělená čárkou):");
        zadanyRetezec = sc.nextLine();

        poleRetezcu = zadanyRetezec.split(",", 0);
        pole = new int[poleRetezcu.length];

        for (int i = 0; i < poleRetezcu.length; i++) {
            poleRetezcu[i] = poleRetezcu[i].trim();
            pole[i] = Integer.parseInt(poleRetezcu[i]);
        }

        // Selection sort
        for (int i = 0; i < pole.length - 1; i++) {
            int indexMinima = i;
            for (int j = i + 1; j < pole.length; j++) {
                if (pole[j] < pole[indexMinima]) indexMinima = j;
            }
            int pomocna = pole[i];
            pole[i] = pole[indexMinima];
            pole[indexMinima] = pomocna;
        }


        // Bubble sort
        /* int j = pole.length - 2;
        boolean prohozeno = true;
        while (prohozeno) {
            prohozeno = false;
            for (int i = 0; i <= j; i++) {
                if (pole[i] > pole[i + 1]) {
                    int pomocna = pole[i];
                    pole[i] = pole[i + 1];
                    pole[i + 1] = pomocna;
                    prohozeno = true;
                }
            }
            j--;
        } */

        // Insertion sort
        /* int aktualniCislo;
        int j;
        for (int i = 1; i <= (pole.length - 1); i++) {
            aktualniCislo = pole[i];
            j = i - 1;
            while ((j >= 0) && (pole[j] > aktualniCislo)) {
                pole[j + 1] = pole[j];
                j--;
            }
            pole[j + 1] = aktualniCislo;
        } */

        System.out.println("Seřazená čísla:");

        String text = "";
        for (int i = 0; i < pole.length; i++) {
            if(i == pole.length - 1) {
                text +=  pole[i];
            } else {
                text +=  pole[i] + ", ";
            }
        }

        System.out.println(text);
        System.out.println();
        sc.close();

    }

    public static void main(String[] args) {
        //easyChessboard();
        //testDec2Smthg();
        //middleSoustavy();
        //hardSorting();
        hardItNetwork();
    }
}
