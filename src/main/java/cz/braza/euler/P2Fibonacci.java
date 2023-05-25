package cz.braza.euler;

public class P2Fibonacci {
    public static int member1 = 1;
    public static int member2 = 2;

    public static int sumOfEvens = 2;

    public static void main(String[] args) {
        // sum of even fibonacci numbers up to 4M
        while (true) {
            int val = calcNextFibo();
            if (val > 4000000)
                break;
            if (val  % 2 == 0)
                sumOfEvens += val;
        }
        System.out.println("Sum of even Fibonacci numbers up to 4M is " + sumOfEvens);
    }

    private static int calcNextFibo() {
        int next = member1 + member2;
        member1 = member2;
        member2 = next;
        return next;
    }

}
