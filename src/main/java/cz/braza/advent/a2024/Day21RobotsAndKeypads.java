package cz.braza.advent.a2024;

public class Day21RobotsAndKeypads {
    public static final char STARTKEY = 'A';
    public static final char VOIDKEY = 'X';
    public static String[] INPUT = {"341A", "083A", "802A", "973A", "780A"};
    public static String[] EXAMPLE = {"029A", "980A", "179A", "456A", "379A"};
    public static final char[][] KEYPAD = {{'7', '8', '9'}, {'4', '5', '6'}, {'1', '2', '3'}, {VOIDKEY, '0', STARTKEY}};
    public static final char[][] DIRPAD = {{VOIDKEY, '^', STARTKEY}, {'<', 'v', '>'}};
    public static void main(String[] args) {
        // DBG: 1st example:
        //System.out.println(toCode(toCode(toCode("029A", KEYPAD), DIRPAD), DIRPAD).length());
        int complexity = 0;
        for (String code: INPUT) {
            int length = toCode(toCode(toCode(code, KEYPAD), DIRPAD), DIRPAD).length();
            int numCode = Integer.parseInt(code.substring(0, 3));
            System.out.printf("Processed code %s, instruction length %d, num %d and result %d.%n", code, length, numCode, length * numCode);
            complexity += length * numCode;
        }
        System.out.println("Part 1 complexity: " + complexity);
        // example: ok
        // my data: 214454 too high :-(
        // 211246 too high
        // 202450 too LOW?!
        // 207022 not right :-/
        /*
        Correct answers:
        P1: 203814
        P2: 248566068436630
        See python repository (but somebody elses code). Hints did not work out, other codes
        did not work correctly either, usually got 211246
        And for more debugging:
        341A: 24552 (correct)
        083A: 5478
        802A: 56140
        973A: 66164
        780A: 51480 (correct)
         */
    }

    public static int[] findKey(char key, char[][] pad) {
        for (int r = 0; r < pad.length; r++)
            for (int c = 0; c < pad[r].length; c++)
                if (pad[r][c] == key) return new int[]{r, c};
        throw new IndexOutOfBoundsException("Invalid char looked for in a keypad.");
    }

    public static String repeat(char c, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++)
            sb.append(c);
        return sb.toString();
    }

    public static String line(int from, int to) {
        return repeat(from > to ? '<' : '>', Math.abs(from - to));
    }

    public static String column(int from, int to) {
        return repeat(from > to ? '^' : 'v', Math.abs(from - to));
    }

    public static String toCode(String code, char[][] pad) {
        // find start position first
        int[] pos = findKey(STARTKEY, pad);
        int r = pos[0];
        int c = pos[1];
        pos = findKey(VOIDKEY, pad);
        int VOIDROW = pos[0];
        //System.out.printf("Finished initialization for pad %dx%d, starting pos [%d,%d], void row at %d.%n", pad.length, pad[0].length, r, c, VOIDROW);
        // get the code:
        StringBuffer sb = new StringBuffer();
        for (char inst: code.toCharArray()) {
            pos = findKey(inst, pad);
            // try to change logic once more: go left, if needed and I do not hit the void, then always vertical, then right (or left to void)
            boolean sortedHorizontal = false;
            if (pos[1] >= c && pos[0] != VOIDROW) {
                sb.append(line(c, pos[1]));
                sortedHorizontal = true;
            }
            sb.append(column(r, pos[0]));
            if (!sortedHorizontal)
                sb.append(line(c, pos[1]));

            /*
            // try to change logic:
            // "VOIDCOL" is always ZERO. If we go left (pos[1] < c), prefer line+column if possible, otherwise prefer column+line
            if (pos[1] >= c && pos[0] != VOIDROW) {
                sb.append(column(r, pos[0]));
                sb.append(line(c, pos[1]));
            } else if (r != VOIDROW || pos[1] > 0) {
                sb.append(line(c, pos[1]));
                sb.append(column(r, pos[0]));
            } else {
                sb.append(column(r, pos[0]));
                sb.append(line(c, pos[1]));
            }
             */
            /*
            if (r != VOIDROW || pos[1] != 0) {
                // process line first:
                sb.append(line(c, pos[1]));
                // then column:
                sb.append(column(r, pos[0]));
            } else {
                // column first
                sb.append(column(r, pos[0]));
                // then row
                sb.append(line(c, pos[1]));
            }
             */
            // now add A:
            sb.append('A');
            // set new position:
            r = pos[0];
            c = pos[1];
        }
        return sb.toString();
    }
}
