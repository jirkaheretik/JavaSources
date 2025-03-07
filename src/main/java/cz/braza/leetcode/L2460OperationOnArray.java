package cz.braza.leetcode;

import java.util.Arrays;

/**
 * Plus used the code for #283 (Move Zeroes) as well
 */
public class L2460OperationOnArray {
    private static void arrPrint(int[] nums) {
        System.out.print("[" + nums[0]);
        for (int idx = 1; idx < nums.length; idx++)
            System.out.print(", " + nums[idx]);
        System.out.println("]");
    }

    // runs 1ms, beats 96.4%, 36 testcases
    public static int[] applyOperations(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                nums[i] *= 2;
                nums[i + 1] = 0;
            }
        }
        // now shift zeroes to the end of the array:
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                if (zeroCount > 0) {
                    // push back nums[i] by zeroCount:
                    nums[i - zeroCount] = nums[i];
                    nums[i] = 0;
                }
            } else {
                zeroCount++;
            }
        }
        // return result:
        return nums;
    }

    /*
    Daily 1.3.2025, already solved before.
    Could this be done in a single pass? That is two pointers or something like that

    Nice :-P
    Runs 0ms, beats 100%
     */
    public static int[] applyOperationsNew(int[] nums) {
        int left = 0;
        for (int idx = 0; idx < nums.length - 1; idx++)
            if (nums[idx] != 0)
                if (nums[idx] == nums[idx + 1]) {
                    nums[left++] = nums[idx] * 2;
                    nums[++idx] = 0;
                } else // move non-zero element to the left{
                    nums[left++] = nums[idx];
        // is the last element nonzero?
        if (nums[nums.length - 1] != 0)
            nums[left++] = nums[nums.length - 1];
        // now fill the rest of the array with zeros:
        while (left < nums.length)
            nums[left++] = 0;

        return nums;
    }


    public static void main(String[] args) {
        int[] tst = {312,312,436,892,0,0,528,0,686,516,0,0,0,0,0,445,445,445,445,445,445,984,984,984,0,0,0,0,168,0,0,647,41,203,203,241,241,0,628,628,0,875,875,0,0,0,803,803,54,54,852,0,0,0,958,195,590,300,126,0,0,523,523};
        System.out.println(Arrays.toString(applyOperations(tst)));
        tst = new int[]{312,312,436,892,0,0,528,0,686,516,0,0,0,0,0,445,445,445,445,445,445,984,984,984,0,0,0,0,168,0,0,647,41,203,203,241,241,0,628,628,0,875,875,0,0,0,803,803,54,54,852,0,0,0,958,195,590,300,126,0,0,523,523};
        System.out.println(Arrays.toString(applyOperationsNew(tst)));
    }
}
