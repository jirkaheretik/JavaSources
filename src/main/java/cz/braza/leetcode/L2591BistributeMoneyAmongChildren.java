package cz.braza.leetcode;

/*
2591. Distribute Money to Maximum Children
Easy
Topics
Companies
Hint
You are given an integer money denoting the amount of money (in dollars) that you have and another integer children denoting the number of children that you must distribute the money to.

You have to distribute the money according to the following rules:

All money must be distributed.
Everyone must receive at least 1 dollar.
Nobody receives 4 dollars.
Return the maximum number of children who may receive exactly 8 dollars if you distribute the money according to the aforementioned rules. If there is no way to distribute the money, return -1.



Example 1:

Input: money = 20, children = 3
Output: 1
Explanation:
The maximum number of children with 8 dollars will be 1. One of the ways to distribute the money is:
- 8 dollars to the first child.
- 9 dollars to the second child.
- 3 dollars to the third child.
It can be proven that no distribution exists such that number of children getting 8 dollars is greater than 1.
Example 2:

Input: money = 16, children = 2
Output: 2
Explanation: Each child can be given 8 dollars.


Constraints:

1 <= money <= 200
2 <= children <= 30
*/
class L2591BistributeMoneyAmongChildren {
    /*
    Very low acceptance rate among easy problems.
    We count how many times we can get 8 from the money, adjust to the number of children.
    Then, if lower count, we must not give any child exactly 4 dollars and each child has to get at least 1

    Runs 2ms, beats 56.8%, 3802 testcases, (43.2% runs in 1ms)
    */
    public int distMoney(int money, int children) {
        if (money < children) return -1;
        int eightCount = money / 8;
        int rest = money % 8;
        if (eightCount >= children) return 8 * children == money ? children : children - 1;
        while (rest < children - eightCount || (children - eightCount == 1 && rest == 4)) { // we don't have enough for the rest of the children
            rest += 8;
            eightCount--;
        }
        return eightCount;
    }
}
