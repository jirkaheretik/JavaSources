package cz.braza.advent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Reading the map into the char[][] array, then adding beams, following it till it goes out of the map
 * (or just works there for way too long). When a splitter is found, follow one lead, and add the other
 * one for later.
 * TODO Improvement: actually END the current beam and add TWO new ones, which should resolve duplicates
 * much more quickly.
 *
 * For part 2, we are supposed to try every possible variant of a beam entering a border. Instead of
 * several cycles I just try one, note maximum, and run another code with a different settings, four
 * in total.
 * From West, max is 7603, from East it is 7922, then 7903 from South and 7943 from North, which is the
 * final answer for a second star.
 */

public class AoC202316ReflectingBeams {
    public static void main(String[] args) {
        char[][] field = AOCHelper.readFile2CharArray("/home/jirka/src/java0/aoc23_16.txt");
        final int MAX = field.length;
        int maxEnergized = 0;

        char startDir = 'S';
        for (int startY = 0; startY < MAX; startY++) {

            boolean[][] energized = new boolean[MAX][MAX];
            List<String> beams = new ArrayList<>();
            Set<String> processed = new HashSet<>();
            beams.add(startY + "-0-" + startDir);
            while (beams.size() > 0) {
                String dummy = beams.get(0);
                beams.remove(0);
                if (processed.contains(dummy))
                    continue;
                else
                    processed.add(dummy);
                String[] beam = dummy.split("-");
                int x = Integer.parseInt(beam[0]);
                int y = Integer.parseInt(beam[1]);
                char direction = beam[2].charAt(0);
                System.out.println("Processing beam at [" + x + "," + y + "] direction " + direction);
                int steps = 0;
                while (true) {
                    //System.out.println("DBG: " + x + "-" + y + "-" + direction);
                    energized[y][x] = true;
                    steps++;
                    boolean freeStep = false;
                    switch (field[y][x]) {
                        case '.' -> freeStep = true;
                        case '-' -> {
                            if (direction == 'N' || direction == 'S') {
                                String b = x + "-" + y + "-E";
                                if (!beams.contains(b)) {
                                    beams.add(b);
                                    // System.out.println("Adding new beam " + b);
                                }
                                direction = 'W';
                                x--;
                            } else freeStep = true;
                        }
                        case '|' -> {
                            if (direction == 'E' || direction == 'W') {
                                String b = x + "-" + y + "-N";
                                if (!beams.contains(b)) {
                                    beams.add(b);
                                    // System.out.println("Adding new beam " + b);
                                }
                                direction = 'S';
                                y++;
                            } else freeStep = true;
                        }
                        case '/' -> {
                            switch (direction) {
                                case 'W' -> {
                                    direction = 'S';
                                    y++;
                                }
                                case 'E' -> {
                                    direction = 'N';
                                    y--;
                                }
                                case 'N' -> {
                                    direction = 'E';
                                    x++;
                                }
                                case 'S' -> {
                                    direction = 'W';
                                    x--;
                                }
                            }
                            // System.out.println("-- changing direction to " + direction + " at [" + x + "," + y + "]");
                        }
                        case '\\' -> {
                            switch (direction) {
                                case 'W' -> {
                                    direction = 'N';
                                    y--;
                                }
                                case 'E' -> {
                                    direction = 'S';
                                    y++;
                                }
                                case 'N' -> {
                                    direction = 'W';
                                    x--;
                                }
                                case 'S' -> {
                                    direction = 'E';
                                    x++;
                                }
                            }
                            // System.out.println("-- changing direction to " + direction + " at [" + x + "," + y + "]");
                        }
                    }
                    if (freeStep)
                        switch (direction) {
                            case 'N' -> y--;
                            case 'S' -> y++;
                            case 'W' -> x--;
                            case 'E' -> x++;
                        }
                    if (x < 0 || y < 0 || y >= MAX || x >= MAX || steps > 20000) {
                        // System.out.println("Bailing out");
                        break;
                    }
                }
            }
            System.out.println("Beams size: " + beams.size());
            int countEnergized = 0;
            for (int row = 0; row < energized.length; row++)
                for (int col = 0; col < energized[row].length; col++)
                    if (energized[row][col])
                        countEnergized++;
            if (startY == 0)
                System.out.println("Part1: Total number of energized tiles is " + countEnergized);
            if (countEnergized > maxEnergized)
                maxEnergized = countEnergized;
            // 1165 -- too low
        }
        System.out.println("Part2 one direction, max: " + maxEnergized);
    }
}
