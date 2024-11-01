package cz.braza.leetcode;

import java.util.*;

/*
2463. Minimum Total Distance Traveled
Hard
Topics
Companies
Hint
There are some robots and factories on the X-axis. You are given an integer array robot where robot[i] is the position of the ith robot. You are also given a 2D integer array factory where factory[j] = [positionj, limitj] indicates that positionj is the position of the jth factory and that the jth factory can repair at most limitj robots.

The positions of each robot are unique. The positions of each factory are also unique. Note that a robot can be in the same position as a factory initially.

All the robots are initially broken; they keep moving in one direction. The direction could be the negative or the positive direction of the X-axis. When a robot reaches a factory that did not reach its limit, the factory repairs the robot, and it stops moving.

At any moment, you can set the initial direction of moving for some robot. Your target is to minimize the total distance traveled by all the robots.

Return the minimum total distance traveled by all the robots. The test cases are generated such that all the robots can be repaired.

Note that

All robots move at the same speed.
If two robots move in the same direction, they will never collide.
If two robots move in opposite directions and they meet at some point, they do not collide. They cross each other.
If a robot passes by a factory that reached its limits, it crosses it as if it does not exist.
If the robot moved from a position x to a position y, the distance it moved is |y - x|.
 */
public class L2463RobotsRepairInFactories {
    /*
    Works for simple cases, not enough for all testcases though, probably
     */
    public static long minimumTotalDistanceSimplified(List<Integer> robot, int[][] factory) {
        long result = 0;
        // sort both arrays
        Collections.sort(robot);
        Arrays.sort(factory, (o1, o2) -> o1[0] - o2[0]);
        // add one more to the last limit just to simplify conditions:
        factory[factory.length - 1][1]++; // NOTE1
        int botSize = robot.size();
        int factSize = 0;
        for (int[] f: factory)
            factSize += f[1]; // add all limits
        int surplus = factSize - botSize - 1; // see NOTE1
        int fIndex = 0;
        int fLimit = factory[0][1];
        for (int bot: robot) {
            int current = Math.abs(bot - factory[fIndex][0]);
            int next = Math.abs(bot - (fIndex < factory.length - 1 ? factory[fIndex + 1][0] : factory[fIndex][0]));
            if (surplus - fLimit >= 0) {
                // do some heuristic
            }

            fLimit--;
            if (fLimit <= 0)
                fLimit = factory[++fIndex][1];
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] fact = {{2, 1}, {4, 2}, {8, 1}, {3, 3}, {2, 4}, {3, 2}, {2, 5}, {1, 11}};
        List<Integer> bots = new ArrayList<>();
        System.out.println(minimumTotalDistance(bots, fact));
    }

    public static long minimumTotalDistanceTLE(List<Integer> robot, int[][] factory) {
        // sort both arrays
        Collections.sort(robot);
        Arrays.sort(factory, Comparator.comparingInt(a -> a[0]));
        // Flatten factory positions according to their capacities
        List<Integer> factoryPositions = new ArrayList<>();
        for (int[] f : factory) {
            for (int i = 0; i < f[1]; i++) {
                factoryPositions.add(f[0]);
            }
        }

        // Recursively calculate minimum total distance with memoization
        return calculateMinDistance(0, 0, robot, factoryPositions);
    }

    private static long calculateMinDistance(int robotIdx, int factoryIdx, List<Integer> robot, List<Integer> factoryPositions) {
        // All robots assigned
        if (robotIdx == robot.size()) return 0;
        // No factories left to assign
        if (factoryIdx == factoryPositions.size()) return (long) 1e12;

        // Option 1: Assign current robot to current factory
        long assign =
                Math.abs(robot.get(robotIdx) - factoryPositions.get(factoryIdx)) +
                        calculateMinDistance(robotIdx + 1, factoryIdx + 1, robot, factoryPositions);

        // Option 2: Skip current factory for the current robot
        long skip = calculateMinDistance(robotIdx, factoryIdx + 1, robot, factoryPositions);

        // Take the minimum and store in memo
        return Math.min(assign, skip);
    }

    public static long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        // Sort robots and factories by position
        Collections.sort(robot);
        Arrays.sort(factory, Comparator.comparingInt(a -> a[0]));

        // Flatten factory positions according to their capacities
        List<Integer> factoryPositions = new ArrayList<>();
        for (int[] f : factory) {
            for (int i = 0; i < f[1]; i++) {
                factoryPositions.add(f[0]);
            }
        }

        int robotCount = robot.size();
        int factoryCount = factoryPositions.size();

        // Initialize dp array
        long[][] dp = new long[robotCount + 1][factoryCount + 1];

        // Initialize base cases
        for (int i = 0; i < robotCount; i++) {
            dp[i][factoryCount] = (long) 1e12; // No factories left
        }

        // Fill DP table bottom-up
        for (int i = robotCount - 1; i >= 0; i--) {
            for (int j = factoryCount - 1; j >= 0; j--) {
                // Option 1: Assign current robot to current factory
                long assign =
                        Math.abs(robot.get(i) - factoryPositions.get(j)) +
                                dp[i + 1][j + 1];

                // Option 2: Skip current factory for the current robot
                long skip = dp[i][j + 1];

                // Take the minimum option
                dp[i][j] = Math.min(assign, skip);
            }
        }

        // Minimum distance starting from first robot and factory
        return dp[0][0];
    }
}
