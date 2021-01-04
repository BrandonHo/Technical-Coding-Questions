import java.util.Stack;

/*
    https://leetcode.com/problems/next-greater-element-ii/
*/
public class NextGreatestElement2 {
    
    public void Test()
    {
        DisplayResults(BruteForce(new int[] {1, 2, 1}));
        DisplayResults(StackSolution(new int[] {1, 2, 1}));

        DisplayResults(BruteForce(new int[] {3, 1, 2, 4}));
        DisplayResults(StackSolution(new int[] {3, 1, 2, 4}));
    }

    public void DisplayResults(int[] result)
    {
        for (int number : result)
            System.out.print(number + " ");
        System.out.println();
    }

    /*
        Time Complexity: O(2n), or ~O(n), where n refers to the size of the nums array.

        Space Complexity: O(n) [stack] + O(n) [only if you count the results array], or ~O(n), where
        n refers to the size of the nums array.
    */
    public int[] StackSolution(int[] nums)
    {
        // Edge cases
        if (nums.length == 0)
            return new int[0];
        if (nums.length == 1)
            return new int[] { -1 };

        // The stack will be storing indices of the various numbers in the nums array
        Stack<Integer> stackHelper = new Stack<Integer>();

        int[] result = new int[nums.length];

        // Base case - add first element
        stackHelper.push(0);

        // Need to iterate through the list twice at maximum (worst case front and back for a number)
        for (int helperIndex = 1; helperIndex < 2 * nums.length; helperIndex++)
        {
            // Once we find a number that is greater than the top number in stack -> continuously pop indices from stack as results
            while (!stackHelper.isEmpty() && nums[helperIndex % nums.length] > nums[stackHelper.peek()])
                result[stackHelper.pop()] = nums[helperIndex % nums.length];

            // Lastly, within first iteration, add the number to a stack
            if (helperIndex < nums.length)
                stackHelper.push(helperIndex);
        }

        // If the stack still has indices, then these indices do not have a result
        while (!stackHelper.isEmpty())
            result[stackHelper.pop()] = -1;

        return result;
    }

    /*
        Time Complexity: O(n.n), where n refers to the size of the nums array.

        Space Complexity: O(n) [only if you count the results array], where n refers
        to the size of the nums array.
    */
    public int[] BruteForce(int[] nums)
    {
        // Edge cases
        if (nums.length == 0)
            return new int[0];
        if (nums.length == 1)
            return new int[] { -1 };
        
        int[] result = new int[nums.length];
        
        // Iterate through each of the numbers in array
        for (int numIndex = 0; numIndex < nums.length; numIndex++)
        {   
            // Search ahead
            int[] searchResult = SearchHelper(numIndex + 1, nums.length, nums[numIndex], nums);

            // Search behind if nothing was found ahead
            if (searchResult[0] == 0)
                searchResult = SearchHelper(0, numIndex, nums[numIndex], nums);

            // Annotate result
            result[numIndex] = searchResult[1];
        }
        
        return result;
    }

    public int[] SearchHelper(int startIndex, int endIndex, int target, int[] nums)
    {
        for (int checkIndex = startIndex; checkIndex < endIndex; checkIndex++)
        {
            if (target < nums[checkIndex])
            {
                return new int[] {1, nums[checkIndex]};
            }
        }
        
        return new int[] {0, -1};
    }
}
