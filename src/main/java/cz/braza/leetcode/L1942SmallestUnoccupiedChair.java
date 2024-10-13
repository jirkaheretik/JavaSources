package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class L1942SmallestUnoccupiedChair {
    public static final int MAX_N = 10000;
    public int smallestChair(int[][] times, int targetFriend) {
        int arrivalOfTarget = times[targetFriend][0];
        // sort by arrival:
        Arrays.sort(times, Comparator.comparingInt(o -> o[0]));
        boolean[] chairs = new boolean[MAX_N];
        ArrayList<int[]> occupied = new ArrayList<>();
        for (int[] friend: times) {
            int arrival = friend[0];
            int departure = friend[1];
            // first, release all the chairs emptied by now:
            ArrayList<Integer> toRelease = new ArrayList<>();
            for (int index = 0; index < occupied.size(); index++) {
                int[] couple = occupied.get(index);
                if (couple[1] <= arrival) {
                    // free the chair:
                    chairs[couple[0]] = false;
                    // remove from the list:
                    toRelease.add(index);
                }
            }
            Collections.sort(toRelease);
            Collections.reverse(toRelease);
            for (int idx: toRelease)
                occupied.remove(idx);
            // now, find first unoccupied chair:
            for (int chairNumber = 0; chairNumber < MAX_N; chairNumber++)
                if (!chairs[chairNumber])
                    if (arrival == arrivalOfTarget)
                        return chairNumber;
                    else {
                        chairs[chairNumber] = true;
                        int[] couple = { chairNumber, departure };
                        occupied.add(couple);
                        break;
                    }
        }
        return 0; // never happens
    }

    public static void main(String[] args) {

    }
}
