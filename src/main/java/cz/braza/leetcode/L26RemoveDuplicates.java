package cz.braza.leetcode;

import java.util.Arrays;

public class L26RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        int skipped = 0;
        int previous = Integer.MIN_VALUE;
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] == previous) {
                skipped++;
                nums[index] = Integer.MAX_VALUE;
            } else previous = nums[index];
        }
        Arrays.sort(nums);
        return nums.length - skipped;
    }

}
