package cz.braza.educanet;

import java.util.ArrayList;
import java.util.Arrays;

public class BigIntDivider {
    /**
     * Euler 3: Find highest prime divisor of 600851475143L
     */

    public static long findNum(long cislo) {
        long nejvetsi = 1;

        while (cislo % 2 == 0) {
            nejvetsi = 2;
            cislo /= 2;
        }

        for (long i = 3; i <= Math.sqrt(cislo); i += 2) {
            while (cislo % i == 0) {
                nejvetsi = i;
                cislo /= i;
            }
        }

        if (cislo > 1) {
            nejvetsi = cislo;
        }
        return nejvetsi;

    }
    public static boolean jePrvocislo(long cislo) {
        if (cislo <= 1) {
            return false;
        }
        for (long i = 2; i < cislo; i++) {
            if (cislo % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        long num = 600851475143L;
        // long num = 1024 * 997;
        System.out.println(findNum(num));

        long velCislo = 81;
        int cislo = 3;
        int[] list = new int[20];
        int pozice = 0;
        while(cislo<10000){
            int pocet = 0;
            for(int i = 2;i<cislo;i++){
                if(cislo%i == 0){
                    pocet++;
                }
            }
            if(pocet == 0){
                if(velCislo%cislo == 0){
                    list[pozice] = cislo;
                    pozice++;
                }
            }
            cislo++;
        }
        Arrays.sort(list);
        for (int i = 0; i < list.length; i++)
            System.out.print(list[i] + " ");
        System.out.println(list[list.length-1]);
    }
}
