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

    public static String fullVigener(String text, String heslo, boolean encode) {
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String result = "";
        char[] hesloChars = heslo.toLowerCase().toCharArray();
        int pozice = 0;
        for (char c: text.toLowerCase().toCharArray()) {
            boolean isLower = lowerCase.contains("" + c);
            boolean isUpper = upperCase.contains("" + c);
            boolean isLetter = isLower || isUpper;
            int shift = (int) hesloChars[pozice % heslo.length()] - 96;
            int pos = (int)c + (encode ? shift : -shift);
            if (isLetter) {
                if ((isLower && pos > 122) || (isUpper && pos > 90))
                    pos -= 26;
                if ((isLower && pos < 97) || (isUpper && pos < 65))
                    pos += 26;
                result += (char) pos;
            } else result += c;
            pozice++;
        }
        return result;
    }

    public static String caesar(String text, int posun) {
        String result = "";
        int pozice = 0;
        for (char c: text.toLowerCase().toCharArray()) {
            int pos = (int)c + posun;
            if (pos > 122) pos -= 26;
            result += (char)pos;
            pozice++;
        }
        return result;
    }

    public static void piTest() {
        // see https://ivanr3d.com/projects/pi/
        String msg = "Wii kxtszof ova fsegyrpm d lnsrjkujvq roj! Kdaxii svw vnwhj pvugho buynkx tn vwh-gsvw ruzqia. Mrq'x kxtmjw bx fhlhlujw cjoq! Hmg tyhfa gx dwd fdqu bsm osynbn oulfrex, kahs con vjpmd qtjv bx whwxssp cti hmulkudui f Jgusd Yp Gdz!";
        String pwd10 = "adaeibfecehigicb";
        String pwd3  = "cadaeibfecehigic";
        System.out.println(fullVigener(msg, pwd10, true));
        System.out.println(fullVigener(msg, pwd10, false));
        System.out.println(fullVigener(msg, pwd3, true));
        System.out.println(fullVigener(msg, pwd3, false));


        /**
         * Result: the formula for crafting a delightful pie! cutoff our three golden apples of one-four pounds. don't forget to weighten well! add sugar as you want and invite friends, even the silly ones to network and celebrate a happy pi day!
         * No spaces: theformulaforcraftingadelightfulpiecutoffourthreegoldenapplesofonefourpoundsdontforgettoweightenwelladdsugarasyouwantandinvitefriendseventhesillyonestonetworkandcelebrateahappypiday!
         * theformulaforcraftingadelightfulpiecutof43goldenapplesof14poundsdontforgettow8 10welladdsugarasyouwantandinvitefriend7thesilly1st12rkandcelebrateahappypiday!
         */
        // CODE:
        System.out.println(4*3*4*8*10*7*2);
    }

    public static void main(String[] args) {
        // easyAscii();
        middlePalindrom();
        //advVigener();
        // piTest();
        //System.out.println(caesar("TURECKO", 5));
    }
}
