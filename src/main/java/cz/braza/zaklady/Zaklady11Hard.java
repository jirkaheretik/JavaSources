package cz.braza.zaklady;

import java.util.Arrays;
import java.util.Scanner;

public class Zaklady11Hard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, "utf8");
        System.out.println("Zadej počet čísel:");
        int pocet = Integer.parseInt(sc.nextLine());
        int[] pole = new int[pocet];
        int[] setridene = new int[pocet];
        for (int i = 0; i < pocet; i++) {
            System.out.println("Zadej " + (i + 1) + ". číslo:");
            pole[i] = Integer.parseInt(sc.nextLine());
            setridene[i] = pole[i];
        }
        Arrays.sort(setridene);
        float median = setridene[pocet / 2];
        for (int i = 0; i < pocet; i++) {
            System.out.println(pole[i] + " se od mediánu odchyluje o " + (pole[i] - median));
        }
    }
}
