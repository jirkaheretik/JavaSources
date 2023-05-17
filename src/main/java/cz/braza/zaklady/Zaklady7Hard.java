package cz.braza.zaklady;

import java.util.Scanner;

public class Zaklady7Hard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, "utf8");
        System.out.println("Zadejte postupně koeficienty a,b,c kvadratické rovnice ax^2+bx+c=0 :");
        double a = Double.parseDouble(sc.nextLine());
        double b = Double.parseDouble(sc.nextLine());
        double c = Double.parseDouble(sc.nextLine());
        if (a == 0) {
            System.out.println("Není kvadratická rovnice");
            return;
        }
        double d = b * b - 4 * a * c;
        if (d < 0)
            System.out.println("Neexistuje řešení v oblasti reálných čísel");
        else if (d == 0)
            System.out.println("Rovnice má jeden kořen x = " + (-b / (2 * a)));
        else {
            double odm = Math.sqrt(d);
            System.out.println("Rovnice má 2 reálné kořeny x1 = " + ((odm - b) / (2 * a)) + ", x2 = " + ((-odm - b) / (2 * a)));
        }

    }
}
