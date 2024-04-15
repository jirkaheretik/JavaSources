package cz.braza.educanet;

import java.util.*;

public class Logik {
    public static final String SYMBOLS = "0123456789";
    public static final int LENGTH = 4;
    public static final int MAX_LENGTH = 50;
    public static final String BLACK_SYMBOL = "O";
    public static final String WHITE_SYMBOL = "o";
    public static final String LNG_ENTER_GUESS = "Zadejte váš pokus: ";
    public static final String LNG_GAME_ON = "Myslím si kombinaci z [" + SYMBOLS + "] s délkou " + LENGTH;
    public static final String LNG_FULL_GUESS = "Výborně! To je konec hry.";
    public static final String LNG_NUMBER_OF_TRIES = "Počet kroků k uhodnutí kombinace: ";
    public static final String LNG_ANOTHER_GAME = "Přejete si hrát ještě jednou (ano/ne)?";
    public static final String LNG_YES = "ano";
    public static final String LNG_SEE_YOU = "Program LOGIK se s vámi loučí a přeje vám hezký den!";
    public static final String LNG_POSSIBILITIES = "Počet zbývajících možností: ";
    public static final String CMD_LIST = "list"; // list all possibilities, given current history
    public static final String CMD_HELP = "help"; // show possible commands
    public static final String CMD_SHOW = "show"; // show me a valid move
    public static final String CMD_COUNT = "count"; // show number of combinations
    public static final String COMMANDS = " " + CMD_COUNT + " " + CMD_HELP + " " + CMD_LIST + " " + CMD_SHOW + " "; // supported commands

    private String myValue;
    private HashMap<String, Integer> history;

    public Logik() {
        myValue = generateValue(SYMBOLS, LENGTH, false);
        System.out.println(LNG_GAME_ON);
        history = new HashMap<>();
    }

    public void doGame() {
        Scanner sc = new Scanner(System.in);
        int result = 0;
        int guesses = 0;
        while (result < LENGTH * 2 * MAX_LENGTH) {
            System.out.print(LNG_ENTER_GUESS);
            String guess = sc.nextLine().trim();
            String cmd = " " + guess.toLowerCase() + " ";
            if (COMMANDS.contains(cmd)) {
                switch (guess) {
                    case CMD_COUNT -> System.out.println(LNG_POSSIBILITIES + getPossibilitiesCount());
                    case CMD_LIST -> System.out.println(getPossibilities());
                    case CMD_SHOW -> System.out.println(showMove());
                    case CMD_HELP -> System.out.println(COMMANDS);
                }
            } else {
                guesses++;
                result = compareGuess(guess);
                history.put(guess, result);
                System.out.println(visualGuess(result));
                if (guesses > 3) {
                    System.out.println(LNG_POSSIBILITIES + getPossibilitiesCount());
                }
            }
        }
        System.out.println(LNG_FULL_GUESS);
        System.out.println(LNG_NUMBER_OF_TRIES + guesses);
    }

    public List<String> getPossibilities() {
        List<String> possibles = new ArrayList<>();
        for (int a = 0; a < 10; a++) {
            for (int b = 0; b < 10; b++) {
                if (b == a) continue;
                for (int c = 0; c < 10; c++) {
                    if (c == a || c == b) continue;
                    for (int d = 0; d < 10; d++) {
                        if (d == a || d == b || d == c) continue;
                        String guess = String.valueOf(a) + String.valueOf(b) + String.valueOf(c) + String.valueOf(d);
                        boolean found = true;
                        for (String key: history.keySet()) {
                            int value = compareGuess(key, guess);
                            if (value != history.get(key)) {
                                found = false;
                                break;
                            }
                        }
                        if (found)
                            possibles.add(guess);
                    }
                }
            }
        }
        return possibles;
    }

    public int getPossibilitiesCount() {
        return getPossibilities().size();
    }

    public String showMove() {
        List<String> possibilities = getPossibilities();
        Random rnd = new Random();
        return possibilities.get(rnd.nextInt(possibilities.size()));
    }

    public int compareGuess(String guess) {
        return compareGuess(myValue, guess);
    }

    public static int compareGuess(String value, String guess) {
        if (value.length() != guess.length()) {
            System.out.println("WARNING! Guess length does not match value length!");
            return 0;
        }
        int black = 0;
        int white = 0;

        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == value.charAt(i))
                black++;
            else if (value.contains("" + guess.charAt(i)))
                white++;
        }
        return 2 * MAX_LENGTH * black + white;
    }

    public static String visualGuess(int value) {
        int black = value / 2 / MAX_LENGTH;
        int white = value % (2 * MAX_LENGTH);
        String result = "";
        for (int i = 0; i < black; i++)
            result += BLACK_SYMBOL;
        for (int i = 0; i < white; i++)
            result += WHITE_SYMBOL;
        return result;
    }

    public static String generateValue(String symbols, int length, boolean canRepeat) {
        Random rnd = new Random();
        String result = "";
        if (length > symbols.length() && !canRepeat)
            throw new IllegalArgumentException("Cannot create longer string than available chars allow.");
        while (result.length() < length) {
            int pos = rnd.nextInt(symbols.length());
            String another = "" + symbols.charAt(pos);
            if (canRepeat || !result.contains(another))
                result += another;
        }
        return result;
    }

    public static void testGenerate() {
        System.out.println(generateValue("0123456789", 4, false));
        System.out.println(generateValue("0123456789", 10, false));
        try {
            System.out.println(generateValue("0123456789", 12, false));
        }
        catch (IllegalArgumentException iae) {
            System.out.println("Tohle neklaplo: " + iae.getMessage());
        }
        System.out.println(generateValue("0123456789", 4, true));
        System.out.println(generateValue("0123456789", 10, true));
        System.out.println(generateValue("0123456789", 12, true));
        System.out.println(generateValue("ABCDEFGHIJ", 6, false));
    }

    public static void testCompare() {
        System.out.println(visualGuess(compareGuess("1234", "5678")));
        System.out.println(visualGuess(compareGuess("3333", "5345")));
        System.out.println(visualGuess(compareGuess("5345", "3333")));
        System.out.println(visualGuess(compareGuess("1234", "3241")));
    }

    public static void testVisual() {
        System.out.println(visualGuess(107));
        System.out.println(visualGuess(0));
        System.out.println(visualGuess(3));
        System.out.println(visualGuess(304));
    }

    public static void main(String[] args) {
        boolean goAgain = true;
        while (goAgain) {
            Logik l = new Logik();
            l.doGame();
            Scanner sc = new Scanner(System.in);
            System.out.println(LNG_ANOTHER_GAME);
            goAgain = LNG_YES.equals(sc.nextLine().trim().toLowerCase());
        }
        System.out.println(LNG_SEE_YOU);
    }
}
