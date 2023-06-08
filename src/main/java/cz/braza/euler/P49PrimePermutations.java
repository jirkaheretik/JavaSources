package cz.braza.euler;

import cz.braza.Eratosthenes2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 *  1487, 4817, 8147 je aritmeticka rada (cleny se lisi o 3330), zaroven jsou vsechna tri cisla prvocisla a jde o vzajemne permutace.
 *  Najdete jeste jednu takovou radu mezi 4mistnymi cisly
 */
public class P49PrimePermutations {

    public static boolean isRising(int num) {
        int last = 10;
        while (num > 0) {
            if (num % 10 > last) return false; // number is not rising
            last = num % 10;
            num /= 10;
        }
        return true;
    }
    public static void main(String[] args) {
        Eratosthenes2 sito = new Eratosthenes2(10000);

        // testovani isRising:
        /*
        int[] testNumbers = { 1235, 3098, 6789, 1467, 2668, 3049};
        for (int num: testNumbers)
            System.out.println("" + num + " rising: " + isRising(num));
         */

        // testovani permutaci:
        /*
        System.out.println(Arrays.toString(makePermutationsOfPrimes(1234, sito)));
        System.out.println(Arrays.toString(makePermutationsOfPrimes(2089, sito)));
        System.out.println(Arrays.toString(makePermutationsOfPrimes(3689, sito)));
         */

        // hlavni algoritmus:
        for (int cislo = 1234; cislo < 4000; cislo++) {
            if (!isRising(cislo)) continue; // work only with rising numbers as not to repeat itself
            // make permutations:
            // remove composed (non prime) numbers
            int[] permutace = makePermutationsOfPrimes(cislo, sito);
            // out: continue if we do not have at least three numbers
            if (permutace.length < 3) continue;
            // sort
            // Arrays.sort(permutace);
            // find out differences, if that is arithmetic sequence
            printArrWithDiffs(permutace);
        }
        // 2699 (270) 2969 (3330) 6299 (3330) 9629 ==> 296962999629 je hledany vysledek!
    }

    private static int[] makePermutationsOfPrimes(int cislo, Eratosthenes2 sito) {
        int i = cislo % 10;
        cislo /= 10;
        int j = cislo % 10;
        cislo /= 10;
        int k = cislo % 10;
        cislo /= 10;
        int l = cislo % 10;
        TreeSet<Integer> cisla = new TreeSet<Integer>();
        cisla.add(1000*i+100*j+10*k+l);
        cisla.add(1000*i+100*j+10*l+k);
        cisla.add(1000*i+100*k+10*j+l);
        cisla.add(1000*i+100*k+10*l+j);
        cisla.add(1000*i+100*l+10*j+k);
        cisla.add(1000*i+100*l+10*k+j);

        cisla.add(1000*j+100*i+10*k+l);
        cisla.add(1000*j+100*i+10*l+k);
        cisla.add(1000*j+100*k+10*i+l);
        cisla.add(1000*j+100*k+10*l+i);
        cisla.add(1000*j+100*l+10*i+k);
        cisla.add(1000*j+100*l+10*k+i);

        cisla.add(1000*k+100*j+10*i+l);
        cisla.add(1000*k+100*j+10*l+i);
        cisla.add(1000*k+100*i+10*j+l);
        cisla.add(1000*k+100*i+10*l+j);
        cisla.add(1000*k+100*l+10*j+i);
        cisla.add(1000*k+100*l+10*i+j);

        cisla.add(1000*l+100*j+10*k+i);
        cisla.add(1000*l+100*j+10*i+k);
        cisla.add(1000*l+100*k+10*j+i);
        cisla.add(1000*l+100*k+10*i+j);
        cisla.add(1000*l+100*i+10*j+k);
        cisla.add(1000*l+100*i+10*k+j);

        ArrayList<Integer> dobraCisla = new ArrayList<>();
        for (int num : cisla) {
            if (sito.isPrime(num) && num > 1000)
                dobraCisla.add(num);
        }
        int[] result = new int[dobraCisla.size()];
        int index = 0;
        for (int num: dobraCisla) {
            result[index++] = num;
        }
        return result;
    }

    public static void printArrWithDiffs(int[] arr) {
        String result = "" + arr[0];
        for (int i = 1; i < arr.length; i++) {
            result += " (" + (arr[i] - arr[i - 1]) + ") " + arr[i];
        }
        System.out.println(result);
    }
}
