package cz.braza.advent;

import java.io.File;
import java.util.*;

class Cards {
    public static final String ORD = "23456789TJQKA";
    public static final String ORDP2 = "J23456789TQKA";

    // powers of thirteen:
    public static final int[] VALS = {1, 13, 169, 2197, 28561, 371293};
    public static final String[] TYPES = {"11111", "2111", "221", "311", "32", "41", "5"};
    String hand;
    int value;
    int valueP2;
    int bid;
    Cards(String h, int b) {
        hand = h;
        bid = b;
        value = countValue(hand, false);
        valueP2 = countValue(hand, true);
        // System.out.println(this);
    }

    public String toString() {
        return "HAND: " + hand + ", bid: " + bid + ", value: " + value;
    }

    public int getValue() { return value; }
    public int getValueP2() { return valueP2; }
    int countValue(String hand, boolean forPart2) {
        // find out hand type:
        int result = VALS[5] * evaluate(hand, forPart2);
        for (int i = 0; i < hand.length(); i++) {
            char card = hand.charAt(hand.length() - 1 - i);
            result += VALS[i] * (forPart2 ? ORDP2.indexOf(card) : ORD.indexOf(card));
        }
        return result;
    }

    /** find out hand type:
     * 0 - High card (five different)
     * 1 - One pair
     * 2 - two pair
     * 3 - three of a kind
     * 4 - full house
     * 5 - four of a kind
     * 6 - five of a kind
     * @param hand
     * @return
     */
    int evaluate(String hand, boolean forPart2) {
        int[] freqs = new int[13];
        for (char c : hand.toCharArray())
            freqs[forPart2 ? ORDP2.indexOf("" + c) : ORD.indexOf("" + c)]++;
        // handle Jacks if we are in Part2:
        if (forPart2 && freqs[0] > 0) {
            // we have jacks to handle
            int maxFreq = 0;
            int maxIdx = 0;
            for (int i = 1; i < freqs.length; i++)
                if (freqs[i] > maxFreq) {
                    maxFreq = freqs[i];
                    maxIdx = i;
                }
            // special case JJJJJ:
            if (maxIdx > 0) {
                freqs[maxIdx] += freqs[0];
                freqs[0] = 0;
            }
        }
        // end of Jacks handling
        Arrays.sort(freqs);
        String type = "";
        int i = freqs.length - 1;
        while (freqs[i] > 0) {
            type += freqs[i];
            i--;
        }
        for (int idx = 0; idx < TYPES.length; idx++)
            if (TYPES[idx].equals(type))
                return idx;
        System.out.println("Unknown hand type " + type + " of hand " + hand);
        return 0;
    }
}

public class AoC202307CamelCards {
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc23_07.txt";
        Scanner vstup = new Scanner(new File(fileName));
        List<Cards> myCards = new ArrayList<>();
        while (vstup.hasNextLine()) {
            String[] rec = vstup.nextLine().split(" ");
            myCards.add(new Cards(rec[0], Integer.parseInt(rec[1])));
        }
        System.out.println("Done reading. First: " + myCards.get(0) + ", last: " + myCards.get(myCards.size() - 1));
        myCards.sort(Comparator.comparing(Cards::getValue));
        System.out.println("After sorting. First: " + myCards.get(0) + ", last: " + myCards.get(myCards.size() - 1));
        int sum = 0;
        int index = 1;
        for (Cards hand: myCards)
            sum += hand.bid * (index++);
        System.out.println("Total winnings part 1: " + sum);
        myCards.sort(Comparator.comparing(Cards::getValueP2));
        System.out.println("After sorting for P2. First: " + myCards.get(0) + ", last: " + myCards.get(myCards.size() - 1));
        sum = 0;
        index = 1;
        for (Cards hand: myCards)
            sum += hand.bid * (index++);
        System.out.println("Total winnings part 2: " + sum);
    }
}
