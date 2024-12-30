package cz.braza.advent.a2024;

import cz.braza.advent.AOCHelper;

public class Day12Gardening {
    public static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) {
        String fileName = "/home/jirka/src/java0/aoc24_12ex.txt";
        char[][] map = AOCHelper.readFile2CharArrayWithBorder(fileName, '.');
        AOCHelper.printField(map, "Board:");
        // for every region:
        // count of tiles (greedy)
        // perimeter, which is (3-neighbours) for every cell, +1 for first
        int sumScoreP1 = 0;
        int sumScoreP2 = 0;
        for (int row = 1; row < map.length - 1; row++)
            for (int col = 1; col < map[row].length; col++)
                if (map[row][col] >= 'A' && map[row][col] <= 'Z') {
                    int[] values = new int[3]; // count, borders
                    char cell = map[row][col];
                    greedyCell2(map, row, col, values, cell);
                    //values[1]++;
                    sumScoreP1 += values[0] * values[1];
                    sumScoreP2 += values[0] * values[2];
                    System.out.printf("Processed %c with a size %d, border count %d and side/edges count %d%n", cell, values[0], values[1], values[2]);
                }
        System.out.println("Part 1 sum: " + sumScoreP1);
        System.out.println("Part 2 sum: " + sumScoreP2);
        AOCHelper.printField(map, "Result:");
        // P2: 1353516 too high
    }

    public static void greedyCell(final char[][] map, final int r, final int c, final int[] result, final char expected) {
        if (map[r][c] != expected) return;
        map[r][c] = (char) (expected + 32); // mark as visited
        result[0] += 1;
        int fences = 4;
        int corners = 0;
        for (int[] dir: directions) {
            int newR = r + dir[0];
            int newC = c + dir[1];
            if (map[newR][newC] == expected) {
                greedyCell(map, newR, newC, result, expected);
                fences--;
            } else if (map[newR][newC] - 32 == expected)
                fences--;
            else {
                // Zde kontrolujeme, zda můžeme přičíst roh
                // "roh" nastává, pokud je v okolí buňka, která není "expected"
                // posuzujeme tyto rohy:
                for (int[] cornerDir : directions) {
                    int cornerR = newR + cornerDir[0];
                    int cornerC = newC + cornerDir[1];
                    if (cornerR >= 0 && cornerR < map.length && cornerC >= 0 && cornerC < map[0].length) {
                        if (map[cornerR][cornerC] != expected && map[cornerR][cornerC] != (expected + 32)) {
                            corners++; // Pokud je to jiná oblast, přidáme roh
                            break; // Jen jeden roh pro tuto hranu
                        }
                    }
                }
            }
        }
        result[1] += fences;
        result[2] += corners;
        // TODO!  result[2] = ??
    }

    public static void greedyCell2(final char[][] map, final int r, final int c, final int[] result, final char expected) {
        if (map[r][c] != expected) return;
        map[r][c] = (char) (expected + 32); // označit jako navštíveno
        result[0] += 1; // počet dílků
        int fences = 0; // počet okrajů
        int corners = 0; // počet rohů

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // směrnice (nahoru, dolů, vlevo, vpravo)

        for (int[] dir : directions) {
            int newR = r + dir[0];
            int newC = c + dir[1];

            if (newR < 0 || newR >= map.length || newC < 0 || newC >= map[0].length) {
                // Pokud jsou venku z mapy, je to okraj
                fences++;
            } else if (map[newR][newC] == expected) {
                // Pokračujeme - stejná oblast
                fences++;
                greedyCell(map, newR, newC, result, expected);
            } else if (map[newR][newC] - 32 == expected) {
                // Pokud narazíme na již navštívenou buňku, snižujeme počet okrajů
                fences++;
            } else {
                // Pokud narazíme na jinou oblast
                corners++; // je to roh
            }
        }

        result[1] += fences; // přidání počtu okrajů
        result[2] += corners; // přidání počtu rohů
    }}
