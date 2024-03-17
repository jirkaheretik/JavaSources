package cz.braza.zaklady;

import java.util.Scanner;

public class Zaklady9 {
    public static void easy() {
        Scanner sc = new Scanner(System.in, "utf8");
        System.out.println("Kolik ryb si dáš k večeři?");
        int pocet = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < pocet; i++)
            System.out.println("<° )))-< \n");
    }

    public static String lahve(int num) {
        if (num > 4)
            return "" + num + " zelených láhví";
        else if (num > 1)
            return "" + num + " zelené láhve";
        else return "1 zelená láhev";
    }

    public static void middle() {
        for (int i = 10; i > 0; i--)
            System.out.println(lahve(i) + " stojí na stole a jedna láhev spadne");
    }

    public static void shortLahve() {
        for (int i = 10; i > 0; i--)
            System.out.println((i > 4 ? i + " zelených láhví" : i > 1 ? i + " zelené láhve" : "1 zelená láhev" ) + " stojí na stole a jedna láhev spadne");
    }

    public static void hard() {
        Scanner sc = new Scanner(System.in, "utf8");
        System.out.println("Zadejte levou mez 1. intervalu:");
        int mez1L = Integer.parseInt(sc.nextLine());
        System.out.println("Zadejte pravou mez 1. intervalu:");
        int mez1P = Integer.parseInt(sc.nextLine());
        System.out.println("Zadejte levou mez 2. intervalu:");
        int mez2L = Integer.parseInt(sc.nextLine());
        System.out.println("Zadejte pravou mez 2. intervalu:");
        int mez2P = Integer.parseInt(sc.nextLine());
        System.out.println("Dvojice čísel, jejichž součet leží v některém z intervalů:\n(1. číslo je z prvního intervalu a 2. z druhého intervalu)");
        for (int i = mez1L; i <= mez1P; i++)
            for (int j = mez2L; j <= mez2P; j++)
                if ((i + j >= mez1L && i + j <= mez1P) || (i + j >= mez2L && i + j <= mez2P))
                    System.out.print("[" + i + ";" + j + "], ");
    }

    public static void main(String[] args) {
//        easy();
//        middle();
//        hard();
        shortLahve();
    }
}
