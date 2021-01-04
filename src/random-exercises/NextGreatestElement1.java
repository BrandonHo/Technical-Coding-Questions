import java.util.HashMap;

/*
    https://leetcode.com/problems/next-greater-element-i/
*/
public class NextGreatestElement1 {
    
    public void Test()
    {
        DisplayResults(Solve(new int[] {4, 1, 2}, new int[] {1, 3, 4, 2}));
        DisplayResults(Solve(new int[] {2, 4}, new int[] {1, 2, 3, 4}));
    }

    public void DisplayResults(int[] result)
    {
        for (int number : result)
            System.out.print(number + " ");
        System.out.println();
    }

    /*
        Time Complexity: O(m.m) + O(n), where m refers to the size of the nums2 array and
        n refers to the size of the nums1 array.

        Space Complexity: O(m) + O(n) [if you count the result array], where m refers to size of the nums2 array
        and n refers to the size of the nums1 array.
    */
    public int[] Solve(int[] nums1, int[] nums2)
    {
        // Edge case - if nums1 array has no elements
        if (nums1.length == 0)
            return new int[0];
        
        // Create hash map to help with quick queries for next biggest number
        HashMap<Integer, Integer> helperMap = new HashMap<Integer, Integer>();
        for (int x = 0; x < nums2.length; x++)
        {
            // Iterate until we find the next biggest number for nums2[x]
            for (int y = x + 1; y < nums2.length; y++)
            {
                if (nums2[y] > nums2[x])
                {
                    helperMap.put(nums2[x], nums2[y]);
                    break;
                }
            }
        }
        
        int[] result = new int[nums1.length];

        // For each of the numbers in nums1, get the next biggest number via hash map
        for (int x = 0; x < nums1.length; x++)
            result[x] = helperMap.getOrDefault(nums1[x], -1);
        
        return result;
    }
}
