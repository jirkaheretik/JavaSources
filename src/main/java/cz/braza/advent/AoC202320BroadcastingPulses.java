package cz.braza.advent;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
1. Reading the input, list of nodes together with their destination nodes (where they are sending signals)
2. ArrayDeque for pulses (pulse is source, destination, low/high). Always start with low pulse to broadcast, wait till the queue is empty, count high and low pulses
3. Do (2) 1000x, then output high x low count
 */

enum Type {
    // Whatever received, sends to all the destination modules
    BROADCAST,
    // ignore high pulse. Start in off state. on low pulse, switch state and sends its new state
    FLIPFLOP,
    // find all sources, remember last info from each (low at the beginning). When pulse received, update the tables for the source. Then send low pulse if all inputs are high, otherwise send high
    NAND
}

class Module {

}
public class AoC202320BroadcastingPulses {
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc23_20.txt";
        Scanner vstup = new Scanner(new File(fileName));
        long sumOfRating = 0;
        boolean readingWorkflow = true;
        Map<String, String> sorting = new HashMap<>();
        while (vstup.hasNextLine()) {
            String[] data = vstup.nextLine().split(" -> ");
            // data[0] type+name (type is % or & or nothing)
            String[] destinations = data[1].split(", ");
        }
    }
}
