package cz.braza.educanet;

import cz.braza.EratosthenesSieve;
import org.openqa.selenium.json.JsonOutput;

public class Functions {
    /**
     * Zjistit, zda jde o prvočíslo, bez if, switch, while, for
     * @param number
     * @return
     */
    public static boolean isPrime(int number) {
        return number < 2 ? false : number == 2 ? true : number % 2 == 0 ? false : isPrime(number, 3);
    }

    private static boolean isPrime(int number, int divisor) {
        return divisor * divisor > number ? true : number % divisor == 0 ? false : isPrime(number, divisor + 2);
    }



    public static void main(String[] args) {
        int limit = 1_000_000;
        EratosthenesSieve sito = new EratosthenesSieve(limit);
        for (int i = 1; i < limit; i++) {
            if (isPrime(i) != sito.isPrime(i))
                System.out.println(i);
        }
        System.out.println("Done testing.");
    }
}
