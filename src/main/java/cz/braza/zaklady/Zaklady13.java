package cz.braza.zaklady;

import java.util.Scanner;

public class Zaklady13 {
    public static void easyAscii() {
        System.out.println("ASCII tabulka\n=============");
        for (int i = 33; i < 256; i++)
            System.out.println("" + i + ":" + (char)i);
    }

    public static void middlePalindrom() {
        Scanner sc = new Scanner(System.in, "utf8");
        System.out.println("Zadej palindrom:");
        String slovo = sc.nextLine();
        for (int i = 0; i < (slovo.length() / 2); i++)
            if (slovo.charAt(i) != slovo.charAt(slovo.length() - 1 - i)) {
                System.out.println("Toto není palindrom.");
                return;
            }
        System.out.println("Ano, toto je palindrom.");
    }

    public static void advVigener() {
        Scanner sc = new Scanner(System.in, "utf8");
        System.out.println("Zadejte text k zašifrování:");
        String vstup = sc.nextLine();
        System.out.println("Zadejte heslo:");
        String heslo = sc.nextLine();
        System.out.println(vigener(vstup, heslo));
    }

    public static String vigener(String text, String heslo) {
        String result = "";
        char[] hesloChars = heslo.toLowerCase().toCharArray();
        int pozice = 0;
        for (char c: text.toLowerCase().toCharArray()) {
            int pos = ((int)c + (int)hesloChars[pozice % heslo.length()] - 96);
            if (pos > 122) pos -= 26;
            result += (char)pos;
            pozice++;
        }
        return result;
    }

    public static void main(String[] args) {
        // easyAscii();
        // middlePalindrom();
        advVigener();
    }
}
