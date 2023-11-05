package cz.braza.advent;

import java.io.File;
import java.util.Scanner;

public class AoC202012RainRisk {

    public static void main(String[] args)  throws Exception {
        // PART 1:
        String fileName = "/home/jirka/src/java0/aoc20_12.txt";
        String directions = "NESW";
        Scanner vstup = new Scanner(new File(fileName));
        int xpos = 0;
        int ypos = 0;
        int angle = 90; // east
        while (vstup.hasNextLine()) {
            String line = vstup.nextLine();
            char cmd = line.charAt(0);
            int value = Integer.parseInt(line.substring(1));
            if (cmd == 'F')
                cmd = directions.charAt(angle / 90);
            switch (cmd) {
                case 'N' -> ypos += value;
                case 'E' -> xpos += value;
                case 'S' -> ypos -= value;
                case 'W' -> xpos -= value;
                case 'L' -> angle -= value;
                case 'R' -> angle += value;
            }
            angle %= 360;
            while (angle < 0) {
                System.out.println("Fixing negative angle");
                angle += 360;
            }
        }
        System.out.println("Ship position [" + xpos + "/" + ypos + "], facing " + directions.charAt(angle / 90));

        // PART 2:
        vstup = new Scanner(new File(fileName));
        xpos = 0;
        ypos = 0;
        int dx = 10;
        int dy = 1;
        while (vstup.hasNextLine()) {
            String line = vstup.nextLine();
            char cmd = line.charAt(0);
            int value = Integer.parseInt(line.substring(1));
            if (cmd =='L') {
                cmd = 'R';
                value = 360 - value;
            }
            switch (cmd) {
                case 'N' -> dy += value;
                case 'E' -> dx += value;
                case 'S' -> dy -= value;
                case 'W' -> dx -= value;
                case 'R' -> {switch (value) {
                    case 90 -> {int dummy = dx; dx = dy; dy = -dummy;}
                    case 180 -> {dx = -dx; dy = -dy;}
                    case 270 -> {int dummy = dx; dx = -dy; dy = dummy;}
                    default -> System.out.println("Invalid angle value " + value);
                }}
                case 'F' -> {xpos += value * dx; ypos += value * dy;}
            }
        }
        System.out.println("Ship position [" + xpos + "/" + ypos + "], total " + (Math.abs(xpos) + Math.abs(ypos)));
    }

}
