package cz.braza.euler;

/**
 * rohy směrem doprava nahoru jsou vždy (2n+1)^2,
 * jiné rohy od toho -2n, -4n, -6n,
 * resp. to lze i obráceně dle předchozích členů (2(n-1)+1)^2 +2n, +4n, +6n
 *
 * Celkem tedy za každý krok kolem dokola přidávám:
 * 4 * (2*n + 1)^2 - 12*n
 */

public class P28NumberSpiralsDiagonals {
    public static void main(String[] args) {
        int totalSum = 1;
        for (int n = 1; n <= 500; n++)
            totalSum += 4 * (2 * n + 1) * (2 * n + 1) - 12 * n;
        System.out.println("Sum on diagonals: " + totalSum);
    }
}
