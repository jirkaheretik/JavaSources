package cz.braza.zaklady;

import java.util.Scanner;

public class ForCyklusPokusy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // For cyklus bez vnitřku:
        for (int i = 0; i < 10; System.out.print((i++) + " "));
        System.out.println("\n==========================================");
        // for cyklus bez aktualizace:
        for (int i = 10; i > 0; )
            System.out.print((i--) + " ");
        System.out.println("\n==========================================");
        // cyklus bez podmínky:
        for (int i = 0; ; i += 3)
            if (i > 20) break; else System.out.print(i + " ");
        System.out.println("\n==========================================");
        // for cyklus bez inicializace:
        System.out.print("Zadejte základ k umocnění: ");
        int zaklad = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Zadejte mocninu: ");
        int mocnina = Integer.parseInt(sc.nextLine().trim());
        long vysledek = 1;
        for (; mocnina > 0; mocnina--)
            vysledek *= zaklad;
        System.out.println("Výsledek je " + vysledek);
        System.out.println("==========================================");

    }
}
