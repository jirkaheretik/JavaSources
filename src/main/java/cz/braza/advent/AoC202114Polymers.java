package cz.braza.advent;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;

/**
 * Polymerization, 10 steps in part one, 40 steps (longs) in part two
 *
 * Initial polymer: COPBCNPOBKCCFFBSVHKO
 *
 * Rules:
 * NS -> H
 * FS -> O
 * PO -> C
 * NV -> N
 * CK -> B
 * FK -> N
 * PS -> C
 * OF -> F
 * KK -> F
 * PP -> S
 * VS -> K
 * VB -> V
 * BP -> P
 * BB -> K
 * BF -> C
 * NN -> V
 * NO -> F
 * SV -> C
 * OK -> N
 * PH -> P
 * KV -> B
 * PN -> O
 * FN -> V
 * SK -> V
 * VC -> K
 * BH -> P
 * BO -> S
 * HS -> H
 * HK -> S
 * HC -> S
 * HF -> B
 * PC -> C
 * CF -> B
 * KN -> H
 * CS -> N
 * SP -> O
 * VH -> N
 * CC -> K
 * KP -> N
 * NP -> C
 * FO -> H
 * FV -> N
 * NC -> F
 * KB -> N
 * VP -> O
 * KO -> F
 * CP -> F
 * OH -> F
 * KC -> H
 * NB -> F
 * HO -> P
 * SC -> N
 * FF -> B
 * PB -> H
 * FB -> K
 * SN -> B
 * VO -> K
 * OO -> N
 * NF -> B
 * ON -> P
 * SF -> H
 * FP -> H
 * HV -> B
 * NH -> B
 * CO -> C
 * PV -> P
 * VV -> K
 * KS -> P
 * OS -> S
 * SB -> P
 * OC -> N
 * SO -> K
 * BS -> B
 * CH -> V
 * PK -> F
 * OB -> P
 * CN -> N
 * CB -> N
 * VF -> O
 * VN -> K
 * PF -> P
 * SH -> H
 * FH -> N
 * HP -> P
 * KF -> V
 * BK -> H
 * OP -> C
 * HH -> F
 * SS -> V
 * BN -> C
 * OV -> F
 * HB -> P
 * FC -> C
 * BV -> H
 * VK -> S
 * NK -> K
 * CV -> K
 * HN -> K
 * BC -> K
 * KH -> P
 */
public class AoC202114Polymers {
    /**
     * Returns string consisting of unique letters only
     * @param input
     * @return
     */
    private static String getLetters(String input) {
        String uniques = "";
        for (char c : input.toCharArray()) {
            if (!uniques.contains("" + c))
                uniques += c;
        }
        return uniques;
    }

    /**
     * Finds target value, which is frequency of the mostly used letter reduced by frequency of the
     * least used letter
     * @param step
     * @param uniques
     * @return
     */
    private static long countValue(Map<String, Long> step, String uniques, char first, char last) {
        long[] freqs = new long[uniques.length()];
        // +1 for first and last char, as they are not counted twice:
        freqs[uniques.indexOf(first)] += 1;
        freqs[uniques.indexOf(last)] += 1;
        // now cycle through whole map and add values:
        for (var entry: step.entrySet()) {
            String key = entry.getKey();
            long val = entry.getValue();
            freqs[uniques.indexOf(key.charAt(0))] += val;
            freqs[uniques.indexOf(key.charAt(1))] += val;
        }
        // find highest and lowest
        long min = Long.MAX_VALUE;
        long max = 0;
        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] > max)
                max = freqs[i];
            if (freqs[i] < min)
                min = freqs[i];
        }
        // return value: (NOTE that all values are still doubled)
        return (max - min) / 2;
    }
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc21_14.txt";
        Scanner vstup = new Scanner(new File(fileName));
        // String input = "COPBCNPOBKCCFFBSVHKO";
        String input = vstup.nextLine().trim();
        char prvniZnak = input.charAt(0);
        char posledniZnak = input.charAt(input.length() - 1);
        String uniques = getLetters(input);
        System.out.println("Initial polymer: " + input  + ", unique letters: " + uniques + ", first: " + prvniZnak + ", last: " + posledniZnak);
        vstup.nextLine(); // reading empty line
        // reading instructions:
        Map<String, Character> instrukce = new HashMap<>();
        while (vstup.hasNextLine()) {
            String[] data = vstup.nextLine().split(" -> ");
            instrukce.put(data[0], data[1].charAt(0));
            System.out.println(data[0]  + " polymerize by adding " + instrukce.get(data[0]) + " in the middle, thus forming new pairs " + data[0].charAt(0) + data[1] + " and " + data[1] + data[0].charAt(1));
        }
        // initial step:
        Map<String, Long> polymer = new HashMap<>();
        for (int i = 0; i < input.length() - 1; i++) {
            String key = "" + input.charAt(i) + input.charAt(i + 1);
            polymer.put(key, polymer.getOrDefault(key, 0L) + 1);
        }
        for (int i = 1; i <= 40; i++) {
            // make a step:
            Map<String, Long> nextStep = new HashMap<>();
            for (var entry : polymer.entrySet()) {
                String key = entry.getKey();
                long count = entry.getValue();
                char middle = instrukce.get(key);
                String k1 = "" + key.charAt(0) + middle;
                String k2 = "" + middle + key.charAt(1);
                nextStep.put(k1, nextStep.getOrDefault(k1, 0L) + count);
                nextStep.put(k2, nextStep.getOrDefault(k2, 0L) + count);
            }
            polymer = nextStep;
            System.out.println("Step " + i + ". result: " + countValue(polymer, uniques, prvniZnak, posledniZnak));
        }
    }
}
