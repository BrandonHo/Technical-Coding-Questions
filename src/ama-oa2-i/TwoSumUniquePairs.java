import java.util.HashMap;

/*
    https://leetcode.com/problems/two-sum/

    Small twist - you are required to return the number of unique pairs.

    Example 1

    Input: nums = [1, 1, 2, 45, 46, 46], target = 47
    Output: 2
    Explanation:
    1 + 46 = 47
    2 + 45 = 47

    Example 2

    Input: nums = [1, 1], target = 2
    Output: 1
    Explanation:
    1 + 1 = 2

    Example 3

    Input: nums = [1, 5, 1, 5], target = 6
    Output: 1
    Explanation:
    [1, 5] and [5, 1] are considered the same.
*/
public class TwoSumUniquePairs {

    public void Test() {
        System.out.println(Solve(new int[] { 1, 1, 2, 45, 46, 46 }, 47));
        System.out.println(Solve(new int[] { 1, 1 }, 2));
        System.out.println(Solve(new int[] { 1, 5, 1, 5 }, 6));
    }

    /*
     * NB: Important to notice that numbers of unique pairs are only used once to
     * make up a number. The only time when a number is used twice to make up a
     * target is when the number is target / 2. For example: 2 is used twice to make
     * up 4.
     * 
     * Time Complexity: O(n) + O(n), where n refers to the size of the nums array.
     * 
     * Space Complexity: O(n), where n refers to the size of the nums array.
     */
    public int Solve(int[] nums, int target) {

        // Edge case
        if (nums.length == 0)
            return -1;

        // Store target-num calculations + occurences in hash map
        HashMap<Integer, Integer> helperMap = new HashMap<Integer, Integer>();
        for (int num : nums)
            if (!helperMap.containsKey(target - num))
                helperMap.put(target - num, 1);
            else
                helperMap.put(target - num, helperMap.get(target - num) + 1);

        // Count number of pairs that make up target
        int counter = 0;
        for (int num : nums) {
            if (helperMap.containsKey(num)) {

                // Has the pair (num and target-sum) been used before?
                if (helperMap.get(num) > 0) {

                    // Update counter + update the hash map with usage
                    counter++;
                    helperMap.put(num, 0);

                    // Update map if the pair did not have equal numbers
                    if (num != target - num)
                        helperMap.put(target - num, 0);
                }
            }
        }

        return counter;
    }
}
