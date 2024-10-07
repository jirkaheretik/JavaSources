package cz.braza.leetcode;

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


    public static void main(String[] args) {
        int[] tst = {2, 2, 2, 1, 1, 0};
        applyOperations(tst);
    }
}
