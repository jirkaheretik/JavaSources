package cz.braza;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToKuBacktracking {
    public static void main(String[] args) {

        Random rand = new Random();
        Instant time1 = Instant.now();
        System.out.println(time1);
        int[] nums = new int[20];


        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextInt(100);
        }

        List<List<Integer>> subsets = generateSubsets(nums);
        //System.out.println(subsets.size());
        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }
        Instant time2 = Instant.now();
        System.out.println(time2.getEpochSecond() - time1.getEpochSecond() + "s");
    }

    public static List<List<Integer>> generateSubsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}