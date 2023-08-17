package cz.braza.euler;

public class P38PandigitalMultiples {
    public static void main(String[] args) {
        for (int i = 9876; i > 9123; i--) {
            long tmp = 100002 * i;
            if (P32PandigitalProducts.isPandigital(String.valueOf(tmp)))
                System.out.println(tmp);
        }
    }
}
