import java.util.HashMap;

/*
    https://leetcode.com/problems/two-sum/
*/
public class TwoSum {
    
    public void Test()
    {
        DisplayResult(Solve(new int[] {2, 7, 11, 15}, 9));
        DisplayResult(Solve(new int[] {3, 2, 4}, 6));
        DisplayResult(Solve(new int[] {3, 3}, 6));
    }

    public void DisplayResult(int[] result)
    {
        System.out.println(result[0] + "," + result[1]);
    }

    /*
        Time Complexity: O(n) + O(n), or ~O(n), where n refers to the number of integers
        in the specified nums array.

        Space Complexity: O(n) where n refers to the number of integers in the specified
        nums array.
    */
    public int[] Solve(int[] nums, int target)
    {
        HashMap<Integer, Integer> helperMap = new HashMap<Integer, Integer>();
        for (int numIndex = 0; numIndex < nums.length; numIndex++)
            helperMap.putIfAbsent(target - nums[numIndex], numIndex);

        for (int numIndex = 0; numIndex < nums.length; numIndex++)
            if (helperMap.containsKey(nums[numIndex]))
                if (helperMap.get(nums[numIndex]) != numIndex)
                    return new int[] {helperMap.get(nums[numIndex]), numIndex};

        return new int[] {-1, -1};
    }
}
