package cz.braza.euler;

public class P1Multiples {
    public static void main(String[] args) {
        // sum of multiples of 3 or 5 below 1000
        int sum = 0;
        for (int i = 1; i < 1000; i++)
            if (i % 3 == 0 || i % 5 == 0)
                sum += i;
        System.out.println("Sum of multiples is " + sum);
    }
}
