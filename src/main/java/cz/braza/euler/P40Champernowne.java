package cz.braza.euler;

/**
 * Champernowne's Constant: 0,123456789101112131415... (concatenated integers)
 * d(n) - nth digit after the decimal point
 * what is d(1) x d(10) x d(100) x d(1000) x d(10000) x d(100000) x d(1000000)
 */
public class P40Champernowne {
    public static int getNthDigit(int n) {
        return 1;
    }

    public static int getProductOfDigits(int[] marks) {
        int product = 1;
        final int MAX = 1000000;
        int nextMark = 0;
        int length = 0;
        for (int i = 1; i < MAX; i++) {

        }
        return 0;
    }
    public static int createBufferAndCount(int[] marks) {
        StringBuilder sb = new StringBuilder("1234567891011121314151617181920");
        int num = 21;
        while (sb.length() < 1000000) {
            sb.append(num++);
        }
        int product = 1;
        for (int mark: marks) {
            char c = sb.charAt(mark-1);
            product *= ((int)c-48);
            System.out.println("D(" + mark + ") = " + c + ", product = " + product);
        }
        return product;
    }
    public static void main(String[] args) {
        int product = 1; // d1, d10 also 1
        int[] marks= {1, 10, 100, 1000, 10000, 100000, 1000000};
        // product = getNthDigit(100) * getNthDigit(1000) * getNthDigit(10000) * getNthDigit(100000) * getNthDigit(1000000);
        // System.out.println(product);
        System.out.println(createBufferAndCount(marks));
    }
}
