package cz.braza.advent.a2024;

import java.io.File;
import java.util.*;

public class Day24CrossedWires {
    public static final int MAXINPUT = 44;
    public static final int MAXZ = 45;
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        String fileName = "/home/jirka/src/java0/aoc24_24.txt";
        Scanner vstup = new Scanner(new File(fileName));
        HashMap<String, Integer> values = new HashMap<>();
        HashMap<String, String> rules = new HashMap<>();
        boolean readInputs = true;
        while (vstup.hasNextLine()) {
            String line = vstup.nextLine();
            if (line.isEmpty()) {
                readInputs = false;
                continue;
            }
            // read input "bits" first
            if (readInputs) {
                String[] inputs = line.split(": ");
                values.put(inputs[0], Integer.parseInt(inputs[1]));
            } else {
                // read rules
                String[] inputs = line.split(" -> ");
                rules.put(inputs[1], inputs[0]);
            }
        }
        System.out.printf("Reading inputs over, we now have %d values and %d rules.%n", values.size(), rules.size());
        long output = 0;
        for (int bit = MAXZ; bit >= 0; bit--) {
            String regName = "z" + (bit < 10 ? "0" : "") + bit;
            output += (long)getValue(regName, values, rules) << bit;
        }
        System.out.println("Part 1 result: " + output);
        /*** JUST DBG, what are X and Y:
        output = 0;
        for (int bit = MAXZ - 1; bit >= 0; bit--) {
            String regName = "x" + (bit < 10 ? "0" : "") + bit;
            output += (long)getValue(regName, values, rules) << bit;
        }
        System.out.println("X = " + output);
        output = 0;
        for (int bit = MAXZ - 1; bit >= 0; bit--) {
            String regName = "y" + (bit < 10 ? "0" : "") + bit;
            output += (long)getValue(regName, values, rules) << bit;
        }
        System.out.println("Y = " + output);
        */
        long endTime = System.currentTimeMillis();
        System.out.printf("Part 1 run %d ms.%n", endTime - startTime);
        doPart2(rules);
        endTime = System.currentTimeMillis();
        System.out.printf("Total program run %d ms.%n", endTime - startTime);
    }

    public static int getValue(String key, HashMap<String, Integer> values, HashMap<String, String> rules) {
        if (values.containsKey(key)) return values.get(key);
        System.out.println("Processing " + key);
        String[] rule = rules.get(key).split(" ");
        int left = getValue(rule[0], values, rules);
        int right = getValue(rule[2], values, rules);
        int result = 0;
        if (("AND".equals(rule[1]) && left == 1 && right == 1) ||
                ("OR".equals(rule[1]) && left + right > 0) ||
                ("XOR".equals(rule[1]) && left != right))
            result = 1;
        values.put(key, result);
        System.out.printf("Got value %d for key %s.%n", result, key);
        return result;
    }

    /*
    Finds the target of a "rule" given by left and right operands and operation
     */
    public static String returns(String left, String right, String op, HashMap<String, String> rules) {
        if (left == null || right == null) {
            System.out.printf("Returning null... for wires '%s' and '%s'.%n", left, right);
            return null;
        }
        String s1 = left + " " + op + " " + right;
        String s2 = right + " " + op + " " + left;
        for (String key: rules.keySet()) {
            String rule = rules.get(key);
            if (s1.equals(rule) || s2.equals(rule)) {
                // System.out.println("Returning " + key);
                return key;
            }
        }
        System.out.printf("Returning null... for rule '%s' or '%s'.%n", s1, s2);
        return null;
    }

    public static void doPart2(HashMap<String, String> rules) {
        List<String> swapped = new ArrayList<>();
        String carry = null;
        for (int z = 0; z <= MAXZ; z++) {
            String name = z < 10 ? "0" + z : "" + z;
            //System.out.println("Look point 0-1");
            String sum1 = returns("x" + name, "y" + name, "XOR", rules);
            //System.out.println("Look point 0-2");
            String carry1 = returns("x" + name, "y" + name, "AND", rules);
            String newCarry = null;
            String carry2 = null;
            String sum2 = null;
            if (carry != null) {
                //System.out.println("Look point 1");
                carry2 = returns(carry, sum1, "AND", rules);
                if (carry2 == null) {
                    System.out.printf("(0) Adding %s and %s.%n", sum1, carry1);
                    swapAndRerun(sum1, carry1, rules);
                    swapped.add(sum1);
                    swapped.add(carry1);
                    // swap them:
                    String dummy = sum1;
                    sum1 = carry1;
                    carry1 = dummy;
                    //System.out.printf("Look point 2 after swap of %s and %s.%n", sum1, carry1);
                    carry2 = returns(carry, sum1, "AND", rules);
                }
                //System.out.println("Look point 3");
                sum2 = returns(carry, sum1, "XOR", rules);
                if (sum1 != null && sum1.startsWith("z")) {
                    System.out.printf("(1) Adding %s and %s.%n", sum1, sum2);
                    swapAndRerun(sum2, sum1, rules);
                    // swap and note:
                    String dummy = sum1;
                    sum1 = sum2;
                    sum2 = dummy;
                    swapped.add(sum1);
                    swapped.add(sum2);
                }
                if (carry1 != null && carry1.startsWith("z")) {
                    System.out.printf("(2) Adding %s and %s.%n", carry1, sum2);
                    swapAndRerun(sum2, carry1, rules);
                    // swap and note:
                    String dummy = sum2;
                    sum2 = carry1;
                    carry1 = dummy;
                    swapped.add(carry1);
                    swapped.add(sum2);
                }
                if (carry2 != null && carry2.startsWith("z")) {
                    System.out.printf("(3) Adding %s and %s.%n", carry2, sum2);
                    swapAndRerun(sum2, carry2, rules);
                    // swap and note:
                    String dummy = sum2;
                    sum2 = carry2;
                    carry2 = dummy;
                    swapped.add(carry2);
                    swapped.add(sum2);
                }
                //System.out.println("Look point 4");
                newCarry = returns(carry2, carry1, "OR", rules);
            } else newCarry = null;
            if (newCarry != null && newCarry.startsWith("z") && !newCarry.equals("z" + name)) {
                System.out.printf("(4) Adding %s and %s.%n", newCarry, sum2);
                // swap and re-run:
                swapAndRerun(newCarry, sum2, rules);
                // swap and log:
                String dummy = newCarry;
                newCarry = sum2;
                sum2 = dummy;
                swapped.add(newCarry);
                swapped.add(sum2);
            }
            carry = (carry == null) ? carry1 : newCarry;
        }
        swapped.sort(String::compareTo);
        System.out.println("Swapped items: " + swapped.size());
        System.out.println("Part 2: " + swapped);
    }

    public static void swapAndRerun(String s1, String s2, HashMap<String, String> rules) {
        String rule1 = rules.get(s1);
        String rule2 = rules.get(s2);
        rules.put(s1, rule2);
        rules.put(s2, rule1);
        doPart2(rules);
    }
}
