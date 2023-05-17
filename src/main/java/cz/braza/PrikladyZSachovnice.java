package cz.braza;

public class PrikladyZSachovnice {
    public static void main(String[] args) {
        for (int j = 1; j <= 8; j++) {
            for (int i = 1; i <= 8; i++)
                if (( i + j) % 2 == 0)
                    System.out.print("  ");
                else
                    System.out.print("##");
            System.out.println();
        }
    }
}
