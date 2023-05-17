package cz.braza.zaklady;

import java.util.Scanner;

public class Zaklady11Mid {
    public static boolean inArray(String[] arr, String val) {
        for (String item: arr)
            if (item.equals(val))
                return true;
        return false;
    }
    public static void main(String[] args) {
        final String[] OVOCE = { "jablko", "hruška", "pomeranč", "jahoda", "banán", "kiwi", "malina"};
        final String[] ZELENINA = { "zelí", "okurka", "rajče", "paprika", "ředkev", "mrkev", "brokolice" };
        Scanner sc = new Scanner(System.in, "utf8");
        int pocetSlov = 0;
        while (true) {
            System.out.println("Zadej název libovolného ovoce nebo zeleniny:");
            String slovo = sc.nextLine().trim().toLowerCase();
            pocetSlov++;
            if (inArray(OVOCE, slovo))
                System.out.println("Zadal jsi ovoce");
            else if (inArray(ZELENINA, slovo))
                System.out.println("Zadal jsi zeleninu");
            else
                System.out.println("Tvoje slovo nemám v seznamu");
            System.out.println("Přeješ si zadat další slovo? (ano/ne)");
            if (sc.nextLine().trim().toLowerCase().equals("ne"))
                break;
        }
        System.out.println("Zadal jsi " + pocetSlov + " slov");
    }
}
