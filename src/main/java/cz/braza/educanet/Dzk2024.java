package cz.braza.educanet;

import java.util.ArrayList;
import java.util.Scanner;

public class Dzk2024 {
    public static void main(String[] args) {
        ArrayList<String> jmena = new ArrayList<>();
        ArrayList<Long> cisla = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Zadávej mi hodnoty, mohou to být čísla i jména.");
        System.out.println("Na konci ti vypíšu nejmenší, největší a průměr z čísel");
        System.out.println("a první a poslední jméno podle abecedy.");
        System.out.println("Zadávání můžeš kdykoli ukončit zadáním PRÁZDNÉHO řetězce (tj. jen Enter)");
        System.out.println();
        boolean pokracovat = true;
        while (pokracovat) {
            System.out.print("Zadej vstup (ENTER pro konec): ");
            String vstup = sc.nextLine();
            if (vstup.isEmpty())
                pokracovat = false;
            else {
                try {
                    long val = Long.parseLong(vstup);
                    cisla.add(val);
                }
                catch (NumberFormatException nfe) {
                    jmena.add(vstup);
                }
            }
        }
        System.out.println("Zadávání ukončeno. Zadal jsi " + cisla.size() + " čísel a " + jmena.size() + " jmen.");
        if (cisla.size() > 0) {
            long min = cisla.get(0);
            long max = min;
            long sum = min;
            for (int idx = 1; idx < cisla.size(); idx++) {
                long val = cisla.get(idx);
                sum += val;
                if (val > max) max = val;
                if (val < min) min = val;
            }
            System.out.println("Nejmenší číslo: " + min);
            System.out.println("Největší číslo: " + max);
            System.out.println("Průměr čísel: " + (sum / cisla.size()));
        }
        if (jmena.size() > 0) {
            String min = jmena.get(0);
            String max = min;
            for (String jmeno: jmena)
                if (jmeno.compareToIgnoreCase(min) < 0)
                    min = jmeno;
                else if (jmeno.compareToIgnoreCase(max) > 0)
                    max = jmeno;
            System.out.println("První jméno dle abecedy: " + min);
            System.out.println("Poslední jméno dle abecedy: " + max);
        }
    }
}
