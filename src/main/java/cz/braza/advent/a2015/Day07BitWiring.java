package cz.braza.advent.a2015;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Day07BitWiring {
    public static void main(String[] args) throws Exception {
        List<String> instructions = Files.readAllLines(Paths.get("/home/jirka/src/java0/aoc15_07.txt"));
        Map<String, Short> values = new HashMap<>();
        int regCounter = 0; // how many registers/variables we solved
        int instCounter = 0; // how many instructions we processed
        int parseCounter = 0; // instructions parsed
        while (instructions.size() > 0) {
            for (String line: instructions) {
                String[] parts = line.split(" -> ");

            }
        }
    }
}
