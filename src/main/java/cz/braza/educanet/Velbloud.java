package cz.braza.educanet;

/**
 * Reseni matematicke hadanky s velbloudem. Puvodni zneni:
 * V miste A v pousti je 3000 bananu (parametr), ktery potrebujeme dostat
 * do mista B (mesto) vzdaleneho 1000 km (parametr). K preprave mame k dispozici
 * velblouda v miste A, ktery unese 1000 bananu najednou (parametr) a spotrebuje
 * 1 banan na kilometr (parametr). Predpokladejme, ze velbloud chodi vzdy po celych
 * kilometrech a banan zere napr. v polovine cesty (tj. musi si ho uz nest s sebou).
 *
 */
public class Velbloud {

    public static int velbloud(int bananu, int vzdalenost, int nosnost, int spotreba) {
        while (bananu > 0 && vzdalenost > 0) {
            int zbytek = bananu % nosnost;
            if (zbytek > 0 && zbytek < 2 * spotreba && bananu > nosnost) {
                // neni vyhodne se vracet pro zbytek bananu
                // System.out.println("km " + vzdalenost + ", zahazuji " + zbytek + " bananu, pro ktere nema smysl se vracet.");
                bananu -= zbytek;
            }
            int cest = (int) Math.ceil(bananu / (double) nosnost) * 2 - 1;
            vzdalenost -= 1;
            bananu -= cest * spotreba;
            //System.out.println("km " + vzdalenost + ", bananu: " + bananu);
        }
        return (vzdalenost == 0) ? bananu : (-1 * vzdalenost);
    }

    public static void main(String[] args) {
        for (int i = 1; i < 1000; i++) {
            int r1 = velbloud(i * 1000, 1000, 1000, 1);
            int r2 = velbloud(i * 1000 + 1, 1000, 1000, 1);
            if (r1 != r2)
                System.out.println(i + "k: " + r1 + " vs " + r2);
        }
        /*
        System.out.println("Zakladni verze: " + velbloud(3000, 1000, 1000, 1));
        System.out.println("3k/k/k/2: " + velbloud(3000, 1000, 1000, 2));
        System.out.println("5k/k/k/1: " + velbloud(5000, 1000, 1000, 1));
        */

    }
}
