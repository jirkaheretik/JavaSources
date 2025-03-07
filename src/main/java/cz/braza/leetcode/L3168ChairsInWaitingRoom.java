package cz.braza.leetcode;

/*
3168. Minimum Number of Chairs in a Waiting Room
Easy
Topics
Companies
Hint
You are given a string s. Simulate events at each second i:

If s[i] == 'E', a person enters the waiting room and takes one of the chairs in it.
If s[i] == 'L', a person leaves the waiting room, freeing up a chair.
Return the minimum number of chairs needed so that a chair is available for every person who enters the waiting room given that it is initially empty.
 */
public class L3168ChairsInWaitingRoom {
    public int minimumChairs(String s) {
        int minChairs = 0;
        int currentChairs = 0;
        for (char c: s.toCharArray())
            if (c == 'E') {
                currentChairs++;
                if (currentChairs > minChairs) minChairs = currentChairs;
            } else
                currentChairs--;
        return minChairs;
    }
}
