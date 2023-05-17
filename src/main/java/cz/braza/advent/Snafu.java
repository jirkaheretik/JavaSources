package cz.braza.advent;

/**
 * Zakladni verze:
 * Cisla ve zvlastni petkove soustave (SNAFU), cifry: = (-2), - (-1), 0, 1, 2
 * Priklady:
 * 1=11-2 ... 2022
 * 1=110= ... 2023
 * 1121-1110-1=0 ... 314159265
 * Cil: umet tato cisla secist, vysledek vypsat jako SNAFU
 */
public class Snafu {
    public static final int[] CIFRY = {1, 5, 25, 125, 625, 3125, 15625, 78125, 390625, 1953125, 9765625, 48828125, 244140625, 1220703125};
    public static int[] CIF2;

    static {
        System.out.println("Filling out CIFRY...");
        CIF2 = new int[14];
        CIF2[0] = 1;
        for (int i = 1; i < CIF2.length; i++) {
            CIF2[i] = 5 * CIF2[i - 1];
        }
        System.out.println("Done.");
    }
    public static int snafu2dec(String snafu) {
        int result = 0;
        int delka = snafu.length();
        for (int i = 0; i < delka; i++) {
            char c = snafu.charAt(delka - i - 1);
            int val = switch (c) {
                case '=' -> -2;
                case '-' -> -1;
                default -> Integer.parseInt("" + c);
            };
            result += CIF2[i] * val;
        }
        return result;
    }

    public static String dec2snafu(int vstup) {
        String result = "";
        int overflow = 0;
        while (vstup != 0 || overflow != 0) {
            int modulo = (vstup + overflow) % 5;
            vstup = (vstup + overflow) / 5;
            overflow = 0;
            if (modulo > 2) {
                overflow = 1;
                result = (modulo == 3 ? "=" : "-") + result;
            } else if (modulo < 0) {
                if (modulo > -3) {
                    result = (modulo == -2 ? "=" : "-") + result;
                } else {
                    overflow = -1;
                    result = "" + (modulo + 5) + result;
                }
            } else {
                result = "" + modulo + result;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] desitka = {"1", "2", "1=", "1-", "10", "11", "12", "2=", "2-", "20"};
        String[] vstup = {"1=11-2", "1=110=", "1121-1110-1=0", "2022", "222======100"};
        int suma = 0;
        for (String snafu: vstup)
            suma += snafu2dec(snafu);
        System.out.println(suma + ", snafu: " + dec2snafu(suma));
        System.out.println("1121-1110-1=0" + ", dec: " + snafu2dec("1121-1110-1=0") + " and back: " + dec2snafu(snafu2dec("1121-1110-1=0")));
        System.out.println("===--2--===" + ", dec: " + snafu2dec("===--2--===") + " and back: " + dec2snafu(snafu2dec("===--2--===")));
    }
}
