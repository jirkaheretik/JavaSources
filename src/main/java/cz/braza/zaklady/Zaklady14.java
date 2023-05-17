package cz.braza.zaklady;

import java.util.Scanner;

public class Zaklady14 {
    public static final String ABECEDA = "abcdefghijklmnopqrstuvwxyz";
    public static final String[] MORZEOVKA = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....",
            "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-",
            "...-", ".--", "-..-", "-.--", "--.."};

    public static final String[] SMILEYS = { " :)", " :D", " :P" };

    public static String fromMorse(String msg) {
        // rozbití řetězce na znaky morzeovky
        String[] znaky = msg.split(" ");

        StringBuilder vystup = new StringBuilder();
        // iterace znaky morzeovky
        for (String morseuvZnak : znaky) {
            if (morseuvZnak.trim().length() == 0) continue;
            for (int i = 0; i < MORZEOVKA.length; i++) {
                if (MORZEOVKA[i].equals(morseuvZnak)) {
                    vystup.append(ABECEDA.charAt(i));
                    break;
                }
            }
        }
        return vystup.toString();
    }
    public static String toMorse(String msg) {
        StringBuilder vystup = new StringBuilder();
        for (char c: msg.toLowerCase().toCharArray()) {
            try {
                vystup.append(MORZEOVKA[(int) c - 97]).append(" ");
            }
            catch (Exception e) {
                // vystup.append("? ");
            }
        }
        return vystup.toString();
    }

    public static void easyGradingAverage() {
        Scanner sc = new Scanner(System.in, "utf8");
        System.out.println("Výpočet průměru známek\nZadejte známky oddělené čárkou:");
        String[] znamky = sc.nextLine().split(",");
        int sum = 0;
        int pocet = 0;
        for (String znamka: znamky) {
            pocet++;
            sum += Integer.parseInt(znamka.trim());
        }
        System.out.println("Průměr: " + (sum / (double) pocet));
    }
    public static void middleToMorse() {
        Scanner sc = new Scanner(System.in, "utf8");
        System.out.println("Zadejte zprávu k zakódování:");
        String zprava = sc.nextLine();
        System.out.println("Zakódovaná zpráva: " + toMorse(zprava));
    }

    public static String makeFunnier(String msg) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (char c: msg.toCharArray()) {
            switch (c) {
                case '.' -> sb.append(SMILEYS[counter++ % 3]);
                case '?' -> sb.append('?').append(SMILEYS[counter++ % 3]);
                case '!' -> sb.append('!').append(SMILEYS[counter++ % 3]);
                default -> sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String makeFunnierOldSwitch(String msg) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (char c: msg.toCharArray()) {
            switch (c) {
                case '.': sb.append(SMILEYS[counter++ % 3]); break;
                case '?':
                case '!': sb.append(c).append(SMILEYS[counter++ % 3]); break;
                default: sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void hardRozveselovac() {
        Scanner sc = new Scanner(System.in, "utf8");
        System.out.println("Zadej text k rozveselení:");
        String zprava = sc.nextLine();
        System.out.println("Rozveselený text: " + makeFunnier(zprava));
    }

    public static void testMorse() {
        System.out.print("morzeovka: ");
        System.out.println(toMorse("morzeovka"));
        System.out.print("MoRZEovkA: ");
        System.out.println(toMorse("MoRZEovkA"));
        System.out.print("Testovací zpráva: ");
        System.out.println(toMorse("Testovací zpráva"));

        System.out.println("Run twice for a string 'restaurace':");
        System.out.println(fromMorse(toMorse("restaurace")));

    }

    public static void main(String[] args) {

        // testMorse();
        //easyGradingAverage();
        //middleToMorse();
        hardRozveselovac();

    }
}
