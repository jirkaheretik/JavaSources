package cz.braza.advent;

import java.io.File;
import java.util.*;

public class AoC202325Wires {
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc23_25.txt";
        Scanner vstup = new Scanner(new File(fileName));
        int linkCount = 0;
        Set<String> nodes = new HashSet<>();
        Map<String, List<String>> graf = new HashMap<>();
        while (vstup.hasNextLine()) {
            String[] data = vstup.nextLine().split(": ");
            nodes.add(data[0]);
            String[] links = data[1].split(" ");
            linkCount += links.length;
            for (String node: links) {
                nodes.add(node);
                addLinkTo(graf, data[0], node);
                addLinkTo(graf, node, data[0]);
            }
        }
        System.out.println("Processed input file, found " + nodes.size() + " nodes and " + linkCount + " links between them.");
        // Processed input file, found 1475 nodes and 3321 links between them.
        System.out.println("Graph size: " + graf.size());
        int links = 0;
        for (String node: graf.keySet()) {
            List<String> others = graf.get(node);
            links += others.size();
            if (others.size() == 3)
                System.out.println(node + " has " + others.size() + " links.");
        }
        System.out.println("There are " + links + " (double-sided) links in total.");
        // plan: go through all the permutations of two nodes and find the shortest path.
        // count usage of each single line to find out the one that is used the most, and remove it
        // from the graph. Repeat twice to see if we got lucky.
        Map<String, Integer> usageCount = new HashMap<>();
    }
    public static void addLinkTo(Map<String, List<String>> graf, String from, String to) {
        if (graf.containsKey(from))
            graf.get(from).add(to);
        else {
            ArrayList<String> nodes = new ArrayList<>();
            nodes.add(to);
            graf.put(from, nodes);
        }
    }
}
