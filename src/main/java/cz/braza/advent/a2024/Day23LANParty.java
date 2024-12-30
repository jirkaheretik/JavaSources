package cz.braza.advent.a2024;

import java.io.File;
import java.util.*;

public class Day23LANParty {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        String fileName = "/home/jirka/src/java0/aoc24_23.txt";
        Scanner vstup = new Scanner(new File(fileName));
        Map<String, List<String>> adj = new HashMap<>();
        List<String> part66 = new ArrayList<>();
        int lines = 0;
        while (vstup.hasNextLine()) {
            lines++;
            String[] nodes = vstup.nextLine().split("-");
            List<String> dummy = adj.getOrDefault(nodes[0], new ArrayList<>());
            dummy.add(nodes[1]);
            adj.put(nodes[0], dummy);
            dummy = adj.getOrDefault(nodes[1], new ArrayList<>());
            dummy.add(nodes[0]);
            adj.put(nodes[1], dummy);
        }
        System.out.printf("Read input, have %d nodes from %d lines.%n", adj.size(), lines);
        Set<String> threes = new HashSet<>();
        for (String comp: adj.keySet()) {
            List<String> my = adj.get(comp);
            //System.out.printf("Node %s with adjacency list %s of size %d.%n", comp, my, my.size());
            //if (!comp.startsWith("t")) continue;
            int interconnected = 0;
            for (int left = 0; left < my.size() - 1; left++)
                for (int right = left + 1; right < my.size(); right++) {
                    String ln = my.get(left);
                    String rn = my.get(right);
                    if (adj.get(ln).contains(rn) && adj.get(rn).contains(ln)) {
                        interconnected++;
                        if (comp.startsWith("t")) threes.add(getThrice(comp, ln, rn));
                    }
                }
            /*
            Based on some visualizations and hints, for PART2 we are looking for full 13 interconnected
            nodes, that is in each adj list, 12 are the group and 1 connection outside.
            We count interconnected pairs and print out the list. Then identify the number (66) and
            create list of nodes with that many interconnected neighbours.
             */
            // System.out.printf("%s has %d interconnected nodes.%n", comp, interconnected);
            if (interconnected == 66)
                part66.add(comp);
        }
        System.out.println("Part 1: threes size: " + threes.size());
        part66.sort(String::compareTo);
        System.out.println("Part 2 password: " + String.join(",", part66));
        long endTime = System.currentTimeMillis();
        System.out.printf("Total program run %d ms.%n", endTime - startTime);
    }

    public static final String getThrice(String s1, String s2, String s3) {
        List<String> dummy = new ArrayList<>();
        dummy.add(s1);
        dummy.add(s2);
        dummy.add(s3);
        dummy.sort(String::compareTo);
        return dummy.toString();
    }
}
