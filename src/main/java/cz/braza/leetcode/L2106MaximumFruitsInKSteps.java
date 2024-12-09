package cz.braza.leetcode;

/*
2106. Maximum Fruits Harvested After at Most K Steps
Hard
Topics
Companies
Hint
Fruits are available at some positions on an infinite x-axis. You are given a 2D integer array fruits where fruits[i] = [positioni, amounti] depicts amounti fruits at the position positioni. fruits is already sorted by positioni in ascending order, and each positioni is unique.

You are also given an integer startPos and an integer k. Initially, you are at the position startPos. From any position, you can either walk to the left or right. It takes one step to move one unit on the x-axis, and you can walk at most k steps in total. For every position you reach, you harvest all the fruits at that position, and the fruits will disappear from that position.

Return the maximum total number of fruits you can harvest.



Example 1:


Input: fruits = [[2,8],[6,3],[8,6]], startPos = 5, k = 4
Output: 9
Explanation:
The optimal way is to:
- Move right to position 6 and harvest 3 fruits
- Move right to position 8 and harvest 6 fruits
You moved 3 steps and harvested 3 + 6 = 9 fruits in total.
Example 2:


Input: fruits = [[0,9],[4,1],[5,7],[6,2],[7,4],[10,9]], startPos = 5, k = 4
Output: 14
Explanation:
You can move at most k = 4 steps, so you cannot reach position 0 nor 10.
The optimal way is to:
- Harvest the 7 fruits at the starting position 5
- Move left to position 4 and harvest 1 fruit
- Move right to position 6 and harvest 2 fruits
- Move right to position 7 and harvest 4 fruits
You moved 1 + 3 = 4 steps and harvested 7 + 1 + 2 + 4 = 14 fruits in total.
Example 3:


Input: fruits = [[0,3],[6,4],[8,5]], startPos = 3, k = 2
Output: 0
Explanation:
You can move at most k = 2 steps and cannot reach any position with fruits.


Constraints:

1 <= fruits.length <= 105
fruits[i].length == 2
0 <= startPos, positioni <= 2 * 105
positioni-1 < positioni for any i > 0 (0-indexed)
1 <= amounti <= 104
0 <= k <= 2 * 105
 */
public class L2106MaximumFruitsInKSteps {
    /*
    Observations:
    - fruits are sorted by position.
    - we can get from start up to start-k (included) or start+k or something in between
    - drop anything before start-k, note first index in fruits I can reach
    - start summing up up to start (and possibly any leftover)
    - note the sum we got
    - then from left and whenever we find a fruit stop, we drop the last, note the difference, and add anything from the right we can reach
    - do this until we are getting <start; start+k>, then the maximum is our total

// TODO: Unfinished, not the correct result!
     */
    public static int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int leftIndex = 0; // first fruit index we can reach
        int rightIndex = 0;  // first fruit index we cannot reach if we start with leftIndex
        int maxTotalSum = 0;
        int leftover = 0;
        boolean found = false;
        // find first fruit index we can actually reach
        for (int index = 0; index < fruits.length; index++)
            if (found)
                if (fruits[index][0] + leftover > startPos) {
                    rightIndex = index;
                    break; // look no further
                } else
                    maxTotalSum += fruits[index][1];
            else
                if (fruits[index][0] + k <= startPos) {
                    maxTotalSum = fruits[index][1];
                    found = true;
                    leftIndex = index;
                    leftover = k - startPos + fruits[index][0];
                }
        //
        int maxCurrentSum = maxTotalSum;
        while (fruits[leftIndex][0] < startPos || rightIndex >= fruits.length - 1) {
            // remove one:
            maxCurrentSum -= fruits[leftIndex++][1];
            int over = k - startPos + fruits[leftIndex][0];
            while (rightIndex < fruits.length && fruits[rightIndex][0] <= over)
                maxCurrentSum += fruits[rightIndex++][1];
            if (maxCurrentSum < maxTotalSum)
                maxTotalSum = maxCurrentSum;
        }

        return maxTotalSum;
    }

    public static void main(String[] args) {
        int[][] f = {{2, 8},{6, 3},{8,6}};
        System.out.println(maxTotalFruits(f, 5, 4));
        int[][] g = {{0, 9}, {4, 1}, {5, 7}, {6, 2}, {7, 4}, {10, 9}};
        System.out.println(maxTotalFruits(g, 5, 4));
    }
}
