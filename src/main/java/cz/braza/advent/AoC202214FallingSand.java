package cz.braza.advent;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class AoC202214FallingSand {
    public static int SIZE = 200;
    public static char[][] POLE = new char[2 * SIZE][SIZE];
    public static int XMIN = 300;
    public static int YABYSS = 170;

    public static void tiskniPole() {
        System.out.println();
        for (int y = 0; y < SIZE; y++) {
            for (int x = 80; x < 220; x++)
                System.out.print(POLE[x][y]);
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < POLE.length; i++)
            Arrays.fill(POLE[i], ' ');
        String fileName = "/home/jirka/src/java0/aoc22_14.txt";
        Scanner vstup = new Scanner(new File(fileName));
        int ymax = 0;
        while (vstup.hasNextLine()) {
            String line = vstup.nextLine();
            String outLine = "";
            String[] coords = line.split(" -> ");
            int prevX = -1;
            int prevY = -1;

            for (String coord: coords) {
                String[] vals = coord.trim().split(",");
                int x = Integer.parseInt(vals[0]);
                int y = Integer.parseInt(vals[1]);
                // System.out.println("DBG. Current coord [" + prevX + "," + prevY + "] moving towards [" + x + "," + y + "]");
                if (y > ymax) ymax = y;
                if (prevX > 0 && prevY > 0) {
                    // work towards [x, y]
                    POLE[prevX - XMIN][prevY] = '#';
                    while (prevX != x || prevY != y) {
                        prevX += Integer.signum(x - prevX);
                        prevY += Integer.signum(y - prevY);
                        POLE[prevX - XMIN][prevY] = '#';
                        // System.out.println("DBG [" + prevX + "," + prevY + "]");
                    }
                    // now x == prevX and y == prevY
                    outLine += " -> " + prevX + "," + prevY;
                } else {
                    prevX = x;
                    prevY = y;
                    outLine = "" + x + "," + y;
                }
            }
            // System.out.println("IN : " + line);
            // System.out.println("OUT: " + outLine + "\n");
            YABYSS = ymax;
            // end of the world for part two:
            for (int x = 0; x < POLE.length; x++)
                POLE[x][YABYSS + 2] = '#';
        }
        POLE[500 - XMIN][0] = '+';
        tiskniPole();
        int sandCount = 0;
        boolean sandFallingThrough = false;
        while (!sandFallingThrough) {
            // go with a single sand seed:
            int x = 500;
            int y = 0;
            sandCount++;
            boolean cameToRest = false;
            // falling:
            while (!cameToRest) {
                if (POLE[x - XMIN][y + 1] == ' ')
                    y += 1;
                else if (POLE[x - XMIN - 1][y + 1] == ' ') {
                    y += 1;
                    x -= 1;
                } else if (POLE[x - XMIN + 1][y + 1] == ' ') {
                        y += 1;
                        x += 1;
                } else {
                    // cannot go to any place lower +/-1
                    cameToRest = true;
                    POLE[x - XMIN][y] = 'O';
                }
                if (y > YABYSS) {
                    cameToRest = true; // so as to escape the circuit, do not enter O anywhere
                    sandFallingThrough = true;
                }
            }
        }
        System.out.println("Sand falling through now after " + (sandCount - 1) + " seeds.");
        tiskniPole();
    }
}