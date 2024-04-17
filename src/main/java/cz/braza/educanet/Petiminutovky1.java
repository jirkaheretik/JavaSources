package cz.braza.educanet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
class Vypocet {
    ArrayList<Integer> cisla = new ArrayList<>();

    public void Addcislo(int cislo){
        cisla.add(cislo);
    }
    public void FindMedian(){
        Collections.sort(cisla);
        int lever = 0;
        while (cisla.size()>1){

            if (lever == 0){
                cisla.remove(cisla.size()-1);
                lever++;
            }
            else if (lever == 1){
                cisla.remove(0);
                lever--;
            }
        }
        System.out.println("Medián "+cisla);
    }

}
public class Petiminutovky1 {
    public static int getMax(int[] array) {
        int result = array[0];
        for (int i = 1; i < array.length; i++)
            if (array[i] > result)
                result = array[i];
        return result;
    }

    public static int getMaxFP(int[] array) {
        return getMaxFP(array[0], 1, array);
    }

    private static int getMaxFP(int currentMax, int firstIndex, int[] array) {
        return firstIndex >= array.length ? currentMax :
                array[firstIndex] > currentMax ? getMaxFP(array[firstIndex], firstIndex + 1, array) :
                        getMaxFP(currentMax, firstIndex + 1, array);
    }

    public static void printSevenNotFiveDivisors(int lower, int upper) {
        for (int val = lower; val < upper; val++)
            if (val % 7 == 0 && val % 5 != 0)
                System.out.println(val);
    }

    public static int fromBinary(String input) {
        int result = 0;
        int digitValue = 1;
        for (int i = input.length() - 1; i >= 0; i--) {
            if (input.charAt(i) == '1')
                result += digitValue;
            digitValue *= 2;
        }
        return result;
    }

    public static void printColumn(int[][] tabulka) {
        for(int i = 0; i < tabulka.length; i++){
            System.out.println(tabulka[i][1]);
        }
    }


    // Jakub Kočí - bumprásk
    public static int findResult(int number){
        for(String strNum : Integer.toString(number).split("")){
            int num = Integer.parseInt(strNum);
            if((num == 3 || number % 3 == 0)&&(num == 5 || number % 5 == 0)) return 3;
            if(number % 3 == 0) return 1;
            if(number % 5 == 0) return 2;
            switch(num){
                case 3: return 1;
                case 5: return 2;
            }
        }
        return 0;
    }
    public static void printMultiplicationTable() {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.printf("%3d ", i * j);
            }
            System.out.println();
        }
    }
    public static int binarniNaDesitkove(String binarni) {
        int vysledek = 0;
        for (int i = 0; i < binarni.length(); i++) {
            vysledek *= 2;
            vysledek += binarni.charAt(i) - '0';
        }
        return vysledek;
    }
    static int prevod(String cislo){
        int celek = 0;
        int nasobek = 1;

        for (int i = 0; i <= cislo.length()-1;i++){

            if (cislo.charAt(i) == '1'){
                celek += nasobek;
            }
            nasobek *= 2;
        }
        return celek;
    }

    public static void main(String[] args) {
/*        int[] testVals = {-11, 34, -42, 42, 56, 2, 4, 5};
        int[][] tabVals = {{1, 2, 3}, {2, 3, 4}, {3, 4, 5}, {6, 7, 8}};
        printColumn(tabVals);
        System.out.println();
        System.out.println(getMax(testVals));
        System.out.println(getMaxFP(testVals));
        printSevenNotFiveDivisors(30, 78);
        System.out.println(fromBinary("100000000000"));
        System.out.println(fromBinary("111111111111111111"));

        Vypocet vypocet = new Vypocet();
        vypocet.Addcislo(5);
        vypocet.Addcislo(15);
        vypocet.Addcislo(4);
        vypocet.Addcislo(2);
        vypocet.Addcislo(22);
        vypocet.Addcislo(4);
        vypocet.Addcislo(0);
        vypocet.Addcislo(97);
        vypocet.Addcislo(67);
        vypocet.Addcislo(67);
        vypocet.Addcislo(67);
        vypocet.FindMedian();
        */
        System.out.println(fromBinary("100000010000"));
        System.out.println(fromBinary("111111111111111111"));
        System.out.println(binarniNaDesitkove("100000010000"));
        System.out.println(binarniNaDesitkove("111111111111111111"));
        System.out.println(prevod("100000010000"));
        System.out.println(prevod("111111111111111111"));
    }
}
