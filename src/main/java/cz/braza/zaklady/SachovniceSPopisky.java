package cz.braza.zaklady;

public class SachovniceSPopisky {
    public static void main(String[] args) {
        for (int r = 0; r < 8; r++) {
            System.out.print("" + (8 - r) + " ");
            for (int c = 0; c < 8; c++)
                System.out.print((r + c) % 2 == 0 ? "   " : "XXX");
            System.out.println();
        }
        System.out.println("   A  B  C  D  E  F  G  H");
    }
}
