package cz.braza.advent.a2024;

import java.io.File;
import java.util.*;

public class Day22MonkeySecretNumbers {
    public static final int LINES = 1771;
    public static final int MINMAX = 7; // minimal maximum value to look for in Part 2. If 9, we get 10k different intstrings, +3k for 8 and another +3k for 7
    public static final long PRUNE = (1 << 24) - 1; // all ones for pruning in getNext()

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        String fileName = "/home/jirka/src/java0/aoc24_22.txt";
        Scanner vstup = new Scanner(new File(fileName));
        long vals[] = new long[LINES];
        int idx = 0;
        long sum = 0;
        while (vstup.hasNextLine()) {
            vals[idx] = Integer.parseInt(vstup.nextLine());
            sum += getIth(vals[idx++], 2000);
        }
        System.out.println("Part 1 sum: " + sum);
        long endTime = System.currentTimeMillis();
        System.out.printf("Program run %d ms.%n", endTime - startTime);
        exampleTestingP2B();
        // part 2:
        // example
        // System.out.println("Part 2 (EXAMPLE) max: " + computePart2(new long[]{1, 2, 3, 2024}));
        // my input:
        System.out.println("Part 2 max: " + computePart2(vals));
        endTime = System.currentTimeMillis();
        System.out.printf("Total program run %d ms.%n", endTime - startTime);
    }

    public static void exampleTestingP2() {
        int[] lookfor = {-2, 1, -1, 3};
        long[] EX = {1, 2, 3, 2024};
        int sum = 0;
        for (long ex: EX) {
            int reward = getReward(lookfor, ex);
            System.out.println("Reward for " + ex + " is " + reward);
            sum += reward;
        }
        System.out.println("Sum of bananas: " + sum);
    }

    public static void exampleTestingP2A() {
        int[] lookfor = {-2, 1, -1, 3};
        getReward(lookfor, 123, 10);
    }

    public static void exampleTestingP2B() {
        int[] lookfor = {-2, 1, -1, 3};
        long[] codes = {1, 2, 3, 2024};
        System.out.println(getSumReward(lookfor, codes));
    }

    public static void exampleTesting() {
        long code = 123;
        for (int i = 1; i <= 10; i++) {
            code = getNext(code);
            System.out.println(i + ". " + code);
        }
        long[] EX = {1, 10, 100, 2024};
        for (long num: EX)
            System.out.println(num + ": " + getIth(num, 2000));
    }

    public static long getNext(long code) {
        code ^= code << 6;    // multiply and mix
        code &= PRUNE;        // prune
        code ^= code >> 5;    // divide by 32 and mix
        code &= PRUNE;        // prune
        code ^= code << 11;   // multiply by 2048 and mix
        return code & PRUNE;  // prune and return
    }

    public static long getIth(long code, int iteration) {
        for (int i = 0; i < iteration; i++)
            code = getNext(code);
        return code;
    }

    /*
    Generate up to 2000 steps, and the first time we see the sequence, return bananas (reward), 0 otherwise
     */
    public static int getReward(int[] lookFor, long code) {
        return getReward(lookFor, code, 2000);
    }

    public static int getReward(int[] lookFor, long code, int STEPS) {
        int steps = 0;
        int idxToLookFor = 0;
        int lastVal = (int) (code % 10);
        while (steps < STEPS) {
            code = getNext(code);
            steps++;
            int val = (int) (code % 10);
            int valDif = val - lastVal;
            lastVal = val;
            if (valDif == lookFor[idxToLookFor]) {
                idxToLookFor++;
                if (idxToLookFor == 4) return val;
            } else
                idxToLookFor = valDif == lookFor[0] ? 1 : 0;
        }
        return 0;
    }

    public static int getSumReward(int[] lookFor, long[] codes) {
        int sum = 0;
        for (long code: codes)
            sum += getReward(lookFor, code);
        return sum;
    }

    /*
    We get list of codes, we assume the best price will need to have value 9 at least somewhere (or not, MINMAX introduced, does affect running time, but not tragically).
    Therefore, for each code in the list:
    - generate up to 2000 steps, compute last 4-diff sequence, and if value is 9 (or at least MINMAX):
      - look if it is not already in the map, if so, skip
      - look if it gets us THAT result for a given code (as it may have appeared before with lower value)
      - call getSumReward(sequence, code) and store key in the set to not try it again, remembering max in the process
     */
    public static int computePart2(long[] codes) {
        Set<String> results = new HashSet<>();
        int maximum = 0;
        int[] look = new int[4]; // working list of diffs
        int lookIdx = 0; // index of the next diff to enter
        for (long myCode: codes) {
            long code = myCode; // myCode is initial code, code itself changes
            int steps = 0;
            int lastVal = (int) (code % 10);
            while (steps < 2000) {
                code = getNext(code);
                steps++;
                int val = (int) (code % 10);
                int valDif = val - lastVal;
                look[lookIdx++] = valDif;
                lastVal = val;
                if (val >= MINMAX && steps >= 4) {
                    // possible best
                    int[] toLook = { look[lookIdx % 4], look[(lookIdx + 1) % 4], look[(lookIdx + 2) % 4], look[(lookIdx + 3) % 4]};
                    // does it even return this value for my code (as it could be sooner with a different value)?! do not go further if not:
                    if (getReward(toLook, myCode) >= MINMAX) {
                        String key = Arrays.toString(toLook);
                        if (!results.contains(key)) {
                            int currReward = getSumReward(toLook, codes);
                            results.add(key);
                            if (currReward > maximum) {
                                System.out.printf("Processed a new sequence %s with better reward %d.%n", key, currReward);
                                maximum = currReward;
                            }
                        }
                    }
                }
                lookIdx %= 4;
            }
        }
        System.out.println("End of Part 2, our cache has " + results.size() + " entries.");
        return maximum;
    }
}
