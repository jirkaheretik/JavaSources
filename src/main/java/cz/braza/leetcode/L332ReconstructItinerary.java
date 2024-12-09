package cz.braza.leetcode;

import java.util.*;

/*
332. Reconstruct Itinerary
Hard
Topics
Companies
You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.

All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.

For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.



Example 1:


Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
Output: ["JFK","MUC","LHR","SFO","SJC"]
Example 2:


Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.


Constraints:

1 <= tickets.length <= 300
tickets[i].length == 2
fromi.length == 3
toi.length == 3
fromi and toi consist of uppercase English letters.
fromi != toi
 */
public class L332ReconstructItinerary {
    // Take the content of L2097 from where I got here, and update for String
    /*
    Insight:
    Look at the problem as Eulerian path (goes through all the edges),
    numbers are nodes, pairs are connections/vertices.
    Build PriorityQueue in adjacency list, as we are supposed to always pick up
    next node based on alphabetical order.

    Process pairs, build adjancency list (where to go from current node),
    We then pick a starting node JFK and visit first node from there. Once we are stuck
    (no more outgoing edges), we add the node to the solution and backtrack,
    picking another edge until nothing is left and we are back at the starting node.
    Runs 6ms, beats 69.9%
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> adjacency = new HashMap();
        // process pairs/edges:
        for (List<String> ticket: tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            // add outgoing line:
            if (!adjacency.containsKey(from))
                adjacency.put(from, new PriorityQueue<>());
            adjacency.get(from).add(to);
        }
        // initialize resulting traverse order:
        LinkedList<String> order = new LinkedList<>();
        String place = "JFK";
        visit("JFK", adjacency, order);
        return order;
    }

    private static void visit(String place, Map<String, PriorityQueue<String>> adjacency, LinkedList<String> order) {
        // do we have where to go?
        while (!adjacency.getOrDefault(place, new PriorityQueue<>()).isEmpty())
            // if so, pick/remove target node and visit it:
            visit(adjacency.get(place).poll(), adjacency, order);
        // if not, add the node to the list and backtrack
        order.addFirst(place);
    }

    public static void main(String[] args) {
        // [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
    }
}
