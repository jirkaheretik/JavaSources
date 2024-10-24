package cz.braza.euler;

public class P54Poker {
    public static final String[] COMBINATIONS = {"HighCard", "OnePair", "TwoPairs", "ThreeOfAKind", "Straight", "Flush", "FullHouse", "Poker", "StraightFlush", "RoyalFlush"};
    public static final String CARD_HEIGHTS = "23456789TJQKA";
    public static final String SUITS = "CDHS";

    public static int evaluateHand() {
        // return combination * 100 + highestCard;
        return 101;
    }

    public static void main(String[] args) {
        /*
        1. Read file /home/jirka/src/java0/euler54_poker.txt
        2. For each line, split in half for two hands, evaluate each, increase counter if P1 has better combination

        Evaluation:
        a. Flush first - if all five cards have the same color (no need to check which), it is one of the flushes, set flag
        b. Order cards by height
        c. Check for straight (five consecutive), set flag
        d. check for count of the same height - 4 (poker), 3 (full house if two others same, otherwise three), 2 (twice or not), else high card only
        e. rank the combination by its index (higher is better) * 100, plus highest card index (to distinguish some combinations)
         */
    }
}
