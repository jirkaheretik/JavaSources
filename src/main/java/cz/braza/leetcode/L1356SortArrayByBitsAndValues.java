package cz.braza.leetcode;

import org.checkerframework.checker.units.qual.A;

import java.util.*;

/*
1356. Sort Integers by The Number of 1 Bits
Easy
Topics
Companies
Hint
You are given an integer array arr. Sort the integers in the array in ascending order by the number of 1's in their binary representation and in case of two or more integers have the same number of 1's you have to sort them in ascending order.

Return the array after sorting it.



Example 1:

Input: arr = [0,1,2,3,4,5,6,7,8]
Output: [0,1,2,4,8,3,5,6,7]
Explantion: [0] is the only integer with 0 bits.
[1,2,4,8] all have 1 bit.
[3,5,6] have 2 bits.
[7] has 3 bits.
The sorted array by bits is [0,1,2,4,8,3,5,6,7]
Example 2:

Input: arr = [1024,512,256,128,64,32,16,8,4,2,1]
Output: [1,2,4,8,16,32,64,128,256,512,1024]
Explantion: All integers have 1 bit in the binary representation, you should just sort them in ascending order.


Constraints:

1 <= arr.length <= 500
0 <= arr[i] <= 10^4
 */
public class L1356SortArrayByBitsAndValues {
    public static final int MAX_BITS_PER_VALUE = 15;
    /*
    First idea:
    Create individual sets/arrays by number of bits, sort each, then merge
    Runs 11ms, beats 27.2%
     */
    public int[] sortByBits(int[] arr) {
        Map<Integer, ArrayList<Integer>> mapa = new HashMap<>();
        // init for empty lists:
        for (int bits = 0; bits < MAX_BITS_PER_VALUE; bits++)
            mapa.put(bits, new ArrayList<>());
        // add nums to different array lists based on number of bits
        for (int num: arr)
            mapa.get(bitCount(num)).add(num);
        // create result array:
        int[] result = new int[arr.length];
        int index = 0;
        for (int bity = 0; bity < MAX_BITS_PER_VALUE; bity++) {
            ArrayList<Integer> hodnoty = mapa.get(bity);
            if (hodnoty.isEmpty()) continue;
            Collections.sort(hodnoty);
            for (int val: hodnoty)
                result[index++] = val;
        }
        return result;
        /*
        Improvements:
        1) do not create result, reuse arr
        2) array of ArrayLists instead of hashmap
         */
    }

    /*
    Array of ArrayLists instead of map
    reusing array given as parameter
    Runs 7ms, beats 65.7% (27.5% does it in 3ms)
     */
    public int[] sortByBitsOptimized(int[] arr) {
        List<Integer>[] mapa = new ArrayList[MAX_BITS_PER_VALUE];
        Arrays.setAll(mapa, e -> new ArrayList<>());
        // add nums to different array lists based on number of bits
        for (int num: arr)
            mapa[bitCount(num)].add(num);
        // fill result array:
        int index = 0;
        for (int bity = 0; bity < MAX_BITS_PER_VALUE; bity++) {
            if (mapa[bity].isEmpty()) continue;
            Collections.sort(mapa[bity]);
            for (int val: mapa[bity])
                arr[index++] = val;
        }
        return arr;
    }

    public static int bitCount(int num) {
        return L3011CanArrayBeSorted.countBitsSet(num);
    }
}
