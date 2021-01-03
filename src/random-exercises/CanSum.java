import java.util.HashMap;

/*
    Question:

    You are given a target number and an array of numbers.

    Write a function to check whether the target number can be made up of 
    numbers from the array. Numbers in the array can be reused.
*/
public class CanSum {

    public void Test() {

        // System.out.println(PerformDP(7, new int[] { 2, 3 }));
        // System.out.println(PerformDP(7, new int[] { 5, 3, 4, 7 }));
        // System.out.println(PerformDP(7, new int[] { 2, 4 }));
        // System.out.println(PerformDP(8, new int[] { 2, 3, 5 }));
        // System.out.println(PerformDP(300, new int[] { 7, 14 }));

        System.out.println(DPTabulation(7, new int[] { 2, 3 }));
        System.out.println(DPTabulation(7, new int[] { 5, 3, 4, 7 }));
        System.out.println(DPTabulation(7, new int[] { 2, 4 }));
        System.out.println(DPTabulation(8, new int[] { 2, 3, 5 }));
        System.out.println(DPTabulation(300, new int[] { 7, 14 }));
    }

    /*
     * Time Complexity: O(n.m), where n is target size, m is numbers array size.
     * From iterating through boolean array and using numbers array to update
     * values.
     * 
     * Space Complexity: O(n), where n is target size. From boolean array created
     * for the result
     */
    public boolean DPTabulation(int target, int[] numbers) {

        boolean[] resultArray = new boolean[target + 1];
        resultArray[0] = true;

        for (int x = 0; x < resultArray.length; x++)
            if (resultArray[x])
                for (int number : numbers)
                    if (x + number < resultArray.length)
                        resultArray[x + number] = true;

        return resultArray[target];
    }

    public boolean PerformDP(int target, int[] numbers) {
        HashMap<Integer, Boolean> helperMemoMap = new HashMap<Integer, Boolean>();
        return DP(target, numbers, helperMemoMap);
    }

    /*
     * Time complexity: O(n.m), where n is the target and m is the length of the
     * numbers array. This time complexity is due to all calculations being done in
     * the first branch of the tree, and thus stored in the hash map for quick
     * queries.
     * 
     * Space complexity: O(n), height of the tree, recursion stack height.
     */
    public boolean DP(int target, int[] numbers, HashMap<Integer, Boolean> memo) {

        if (memo.containsKey(target))
            return memo.get(target);
        if (target == 0)
            return true;
        if (target < 0)
            return false;

        for (int number : numbers) {
            if (DP(target - number, numbers, memo)) {
                memo.put(target, true);
                return memo.get(target);
            }
        }

        memo.put(target, false);
        return memo.get(target);
    }

    /*
     * Time complexity: O(m^n), where m is the length of the numbers array and n is
     * the target number (m choices, n is the height of the tree)
     * 
     * Space complexity: O(n), where n is the target number (height of tree),
     * recursion stack highest length.
     */
    public boolean PerformCanSumBruteForce(int target, int[] numbers) {

        if (target == 0)
            return true;
        if (target < 0)
            return false;

        for (int number : numbers)
            if (PerformCanSumBruteForce(target - number, numbers))
                return true;
        return false;
    }
}
