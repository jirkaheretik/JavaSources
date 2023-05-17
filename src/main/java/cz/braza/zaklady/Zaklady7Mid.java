package cz.braza.zaklady;

import java.util.Scanner;

public class Zaklady7Mid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, "utf8");
        System.out.println("Zadej smajlíka:");
        String smajlik = sc.nextLine();
        String base = "Tvůj smajlík je ";
        String result = "";
        switch (smajlik) {
            case ":-)":
            case ":)":
                result = "veselý"; break;
            case ":-(":
            case ":(":
                result = "smutný"; break;
            case ":-*":
            case ":*":
                result = "zamilovaný"; break;
            case ":-P":
            case ":P":
                result = "s vyplazeným jazykem"; break;
            default:
                result = "neznámý";
        }
        System.out.println(base + result);

    }
}
