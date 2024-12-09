package cz.braza.advent.a2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Day05OrderedPrinting {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "/home/jirka/src/java0/aoc24_05.txt";
        Scanner vstup = new Scanner(new File(fileName));
        int middleSum = 0;
        int badSumP2 = 0;
        Set<String> rules = new HashSet<>();
        Map<String, List<String>> order = new HashMap<>();
        boolean readingRules = true;
        while (vstup.hasNextLine()) {
            String line = vstup.nextLine();
            if (line.isEmpty()) {
                readingRules = false;
                System.out.println(order);
                continue; // next line
            }
            if (readingRules) {
                rules.add(line);
                String[] vals = line.split("\\|");
                List<String> current = order.getOrDefault(vals[0], new ArrayList<>());
                current.add(vals[1]);
                order.put(vals[0], current);
                // System.out.printf("DBG %s split to %s and %s, list: %s, map: %n", line, vals[0], vals[1], current.toString(), order.toString());
            } else {
                int val = processLine(line, rules, order);
                System.out.println("DBG " + (val > 0 ? "valid line with middle " + val : "INVALID line") + " " + line);
                if (val > 0)
                    middleSum += val;
                else badSumP2 -= val;
            }
        }
        System.out.println("Part 1 - sum of middle pages: " + middleSum);
        System.out.println("Part 2 - sum of corrected middle page positions: " + badSumP2);
        // 3485 too low
    }

    /*
    Processing a line: splitting by a comma, parsing to an array of integers.
    go through the array and for each indices i < j, try to find "j|i" in the list of rules
    if so, line is NOT valid, return 0.
    If no line breaking the rules is found, return middle value.
     */
    public static int processLine(String line, final Set<String> rules, Map<String, List<String>> order) {
        String[] pages = line.split(",");
        if (isCorrectlyOrdered(pages, rules))
            return Integer.parseInt(pages[pages.length / 2]);
        else
            return findCorrectOrdering(pages, rules, order);
    }

    public static boolean isCorrectlyOrdered(String[] pages, final Set<String> rules) {
        for (int page = 1; page < pages.length; page++)
            for (int previous = 0; previous < page; previous++)
                if (rules.contains(pages[page] + "|" + pages[previous]))
                    return false;
        return true;
    }

    public static int findCorrectOrdering(String[] pages, final Set<String> rules, Map<String, List<String>> order) {
        System.out.println("Looking for a correct ordering of " + Arrays.toString(pages));
        Map<String, Integer> ruleCount = new HashMap<>();
        List<String> pagesList = Arrays.asList(pages);
        for (String page: pages) {
            List<String> linked = order.getOrDefault(page, new ArrayList<>());
            int count = 0;
            for (String item: linked)
                if (pagesList.contains(item))
                    count++;
            ruleCount.put(page, count);
        }
        // sort by number of direct conditions
        Arrays.sort(pages, (o1, o2) -> ruleCount.get(o2) - ruleCount.get(o1));
        System.out.println("Voilá! Found an ordering: " + Arrays.toString(pages));
        return -1 * Integer.parseInt(pages[pages.length / 2]);
    }
}
