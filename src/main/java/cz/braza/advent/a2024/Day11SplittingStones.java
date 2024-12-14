package cz.braza.advent.a2024;

import java.util.Map;
import java.util.HashMap;

public class Day11SplittingStones {
    public static final int[] EXAMPLE = {125, 17}; // 22 after 6 steps, 55312 after 25 steps
    public static final int[] INPUT = {8435, 234, 928434, 14, 0, 7, 92446, 8992692};

    public static void main(String[] args) {
        Map<Long, Long> cache = new HashMap<>();
        // dbg:
        //System.out.println("Example 6 steps: " + (doStep(125, 6, cache) + doStep(17, 6, cache)));
        //System.out.println("Example 25 steps: " + (doStep(125, 25, cache) + doStep(17, 25, cache)));
        // real data:
        long sum = 0;
        for (int num: INPUT)
            sum += doStep(num, 25, cache);
        System.out.println("Part 1 number of stones after 25 steps: " + sum);
        // 182080 - too low, actually it is 182081
        sum = 0;
        for (int num: INPUT) {
            long subres = doStep(num, 75, cache);
            System.out.println(num + " - stones " + subres);
            sum += subres;
        }
        System.out.println("Part 2 number of stones after 75 steps: " + sum);
    }

    public static long doStep(long number, int remains, Map<Long, Long> cache) {
        long key = number * 100 + remains;
        if (cache.containsKey(key)) {
            //System.out.println("Cache hit");
            return cache.get(key);
        }
        if (remains == 0) {
            //System.out.print(number + " ");
            cache.put(key, 1L);
            return 1;
        }
        long res = 0;
        if (number == 0) res = doStep(1, remains - 1, cache);
        else {
            int digits = (int) Math.floor(Math.log10(number) + 1);
            if (digits > 0 && digits % 2 == 0) {
                long tens = (int) Math.pow(10, digits / 2);
                res = doStep(number / tens, remains - 1, cache) + doStep(number % tens, remains - 1, cache);
            } else res = doStep(number * 2024, remains - 1, cache);
        }
        cache.put(key, res);
        return res;
    }
}
