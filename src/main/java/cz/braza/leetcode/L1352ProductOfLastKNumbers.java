package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.List;

/*
Daily 14.2.2025
SHOW: OOP, progress, cost of size()


1352. Product of the Last K Numbers
Medium
Topics
Companies
Hint
Design an algorithm that accepts a stream of integers and retrieves the product of the last k integers of the stream.

Implement the ProductOfNumbers class:

ProductOfNumbers() Initializes the object with an empty stream.
void add(int num) Appends the integer num to the stream.
int getProduct(int k) Returns the product of the last k numbers in the current list. You can assume that always the current list has at least k numbers.
The test cases are generated so that, at any time, the product of any contiguous sequence of numbers will fit into a single 32-bit integer without overflowing.



Example:

Input
["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]
[[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]

Output
[null,null,null,null,null,null,20,40,0,null,32]

Explanation
ProductOfNumbers productOfNumbers = new ProductOfNumbers();
productOfNumbers.add(3);        // [3]
productOfNumbers.add(0);        // [3,0]
productOfNumbers.add(2);        // [3,0,2]
productOfNumbers.add(5);        // [3,0,2,5]
productOfNumbers.add(4);        // [3,0,2,5,4]
productOfNumbers.getProduct(2); // return 20. The product of the last 2 numbers is 5 * 4 = 20
productOfNumbers.getProduct(3); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
productOfNumbers.getProduct(4); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
productOfNumbers.add(8);        // [3,0,2,5,4,8]
productOfNumbers.getProduct(2); // return 32. The product of the last 2 numbers is 4 * 8 = 32


Constraints:

0 <= num <= 100
1 <= k <= 4 * 10^4
At most 4 * 10^4 calls will be made to add and getProduct.
The product of the stream at any point in time will fit in a 32-bit integer.


Follow-up: Can you implement both GetProduct and Add to work in O(1) time complexity instead of O(k) time complexity?
 */
public class L1352ProductOfLastKNumbers {
}

/* Naive implementation
Just an array list with numbers, when asked for product, we compute it.
getProduct works in O(k) :-(
Still works:

Runs 2441ms, beats 5%, 33 testcases
 */
class ProductOfNumbersNaive {
    int index = -1;
    List<Integer> nums = new ArrayList<>();

    public ProductOfNumbersNaive() {
    }

    public void add(int num) {
        nums.add(num);
        index++;
    }

    public int getProduct(int k) {
        int product = 1;
        for (int i = 0; i < k; i++)
            product *= nums.get(index - i);
        return product;
    }
}

/*
Idea from Editorial: as is prefix sum, we can deine a prefix product, and for any k,
we just return prefix[current] / prefix[current - k]
Reset values when 0 is encountered, and if k > size(), return 0.

Runs 257ms, beats 15.3%,
 */
class ProductOfNumbers10xBetterNoLuck {
    List<Integer> nums = new ArrayList<>();

    public ProductOfNumbers10xBetterNoLuck() {
        nums.add(1);
    }

    public void add(int num) {
        if (num == 0) {
            nums.clear();
            nums.add(1);
        } else
            nums.add(num * nums.get(nums.size() - 1));
    }

    public int getProduct(int k) {
        if (k >= nums.size()) return 0;
        return nums.get(nums.size() - 1) / nums.get(nums.size() - k - 1);
    }
}

/*
Same as before, but count size in the process
Runs 15ms, beats 91.3%, 33 testcases
 */
class ProductOfNumbers {
    List<Integer> nums = new ArrayList<>();
    int size = 0;

    public ProductOfNumbers() {
        nums.add(1);
    }

    public void add(int num) {
        if (num == 0) {
            nums.clear();
            nums.add(1);
            size = 0;
        } else
            nums.add(num * nums.get(size++));
    }

    public int getProduct(int k) {
        if (k > size) return 0;
        return nums.get(size) / nums.get(size - k);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */