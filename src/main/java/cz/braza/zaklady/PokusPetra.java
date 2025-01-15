package cz.braza.zaklady;

import java.util.Scanner;

public class PokusPetra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Zadej počet ryb:");
        int pocet = sc.nextInt(); // pitomé nextInt(), navíc ignoruje hodnotu
        for (int i = 0; i <= 10; i++)
            for (int j = 0; j <= 10; j++)
                System.out.println((i * j) + "0<< %d");
                // doufám, že %d nebo << je tu jen náhodou a nemá to nic dělat
                // nebo připomínat (ani rybu, ani printf)
    }
}
