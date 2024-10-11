package cz.braza.leetcode;

public class L1963SwapToBalancedString {
    public static int minSwaps(String s) {
        int leftCount = 0;
        int rightCount = 0;
        int swapCount = 0;
        int stringLength = s.length();
        for (int idx = 0; idx < stringLength; idx++) {
            if (s.charAt(idx) == '[')
                leftCount++;
            else
                if (rightCount + 1 > leftCount) {
                    leftCount++;
                    swapCount++;
                } else
                    rightCount++;
            if (leftCount - rightCount + idx >= stringLength) break; // we are done here
        }
        return swapCount;
    }

    public static void main(String[] args) {

    }
}
