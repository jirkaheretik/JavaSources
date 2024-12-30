package cz.braza.oop;

import java.util.HashSet;
import java.util.Scanner;

class Programmer {

    private String name;
    private HashSet<String> programmingLanguages= new HashSet<String>(); // Creating a collection of Programming Languages using HashSet

    public Programmer(String name, String[] programmingLanguages) {
        this.name = name;

        // Filling programming languages from the array programmingLanguages into the collection HashSet<String>
        for (String language : programmingLanguages) {
            this.programmingLanguages.add(language);
        }
    }

    public String getName() { return this.name; }
    public HashSet<String> getProgrammingLanguages() { return this.programmingLanguages; }
}

public class Kolekce6_7 {
    public static final String[] TEXTY_EN = {"Enter the details for programmer no. %d:%n", "Name", "Programming languages (separate with a comma and a space)", "All programming languages", "Common programming languages", "Languages unique to "};
    public static final String[] TEXTY_CZ = {"Zadejte %d. programátora%n", "Jméno", "Programovací jazyky (oddělujte čárkou a mezerou)", "Všechny jazyky", "Společné jazyky", "Jazyky, které umí jen "};
    public static void main(String[] args) {
        Programmer[] programatori = new Programmer[3];
        Scanner sc = new Scanner(System.in);
        String[] TXT = TEXTY_CZ;
        HashSet<String> vsechny = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            System.out.printf(TXT[0], (i + 1));
            System.out.print(TXT[1] + ": ");
            String jmeno = sc.nextLine();
            System.out.print(TXT[2] + ": ");
            String jazyky = sc.nextLine();
            programatori[i] = new Programmer(jmeno, jazyky.split(", "));
            vsechny.addAll(programatori[i].getProgrammingLanguages());
        }
        System.out.println();
        System.out.println(TXT[3] + ": " + vsechny);
        System.out.print(TXT[4] + ": ");
        for (String jazyk: vsechny)
            if (programatori[0].getProgrammingLanguages().contains(jazyk)
                    && programatori[1].getProgrammingLanguages().contains(jazyk)
                    && programatori[2].getProgrammingLanguages().contains(jazyk)
            )
                System.out.print(jazyk + ", ");
        System.out.println();
        System.out.println();
        for (int i = 0; i < programatori.length; i++) {
            System.out.print(TXT[5] + programatori[i].getName() + ": ");
            for (String lang: programatori[i].getProgrammingLanguages()) {
                boolean unique = true;
                for (int j = 0; j < programatori.length; j++)
                    if (i != j && programatori[j].getProgrammingLanguages().contains(lang))
                        unique = false;
                if (unique) System.out.print(lang + ", ");
            }
            System.out.println();
        }

    }
}
