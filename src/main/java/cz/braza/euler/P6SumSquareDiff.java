package cz.braza.euler;

public class P6SumSquareDiff {

    private static int sumUpToN(int n) {
        return n * (n + 1) / 2;
    }

    private static int sumOfSquares(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++)
            sum += i * i;
        return sum;
    }
    public static void main(String[] args) {
        int value = 100;
        int square = sumUpToN(value);
        System.out.println(square*square - sumOfSquares(value));

    }
}
