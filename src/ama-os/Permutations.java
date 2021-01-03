import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/*
    https://leetcode.com/problems/permutations/
*/
public class Permutations {
    
    public void Test()
    {
        DisplayResults(Solve(new int[] {1, 2, 3}));
    }

    public void DisplayResults(List<List<Integer>> permutations)
    {
        for (List<Integer> permutation : permutations)
        {
            for (int number : permutation)
                System.out.print(number + " ");
            System.out.println();
        }
    }

    /*
        Time Complexity: O(n.n!), where n refers to the size of the numbers array.

        - O(n.n!) refers to the theoretical maximum number of function calls, which is
        n.n!. There are n numbers in the array, which will take n! function calls.

        Note the usage of the LinkedHashSet, which allows O(1) time for add, contains, and 
        remove functions. An array list was originally used, however, the add, contains, and
        remove functions would be O(n).

        Space Complexity: O(n) + O(n!.n), where n refers to the size of the numbers array.
        If we ignore space by result, then simply O(n).

        - O(n) refers to the maximum recursion stack height.

        - O(n!.n) refers to !n possible combinations of size n.
    */
    public List<List<Integer>> Solve(int[] nums)
    {
        List<List<Integer>> permutationResults = new ArrayList<List<Integer>>();
        Helper(nums, new LinkedHashSet<Integer>(), permutationResults);
        return permutationResults;
    }

    public void Helper(int[] nums, Set<Integer> currCombination, List<List<Integer>> permutations)
    {
        if (currCombination.size() == nums.length)
        {
            permutations.add(new ArrayList<Integer>(currCombination));
            return;
        }

        for (int number : nums)
        {
            if (!currCombination.contains(number))
            {
                currCombination.add(number);
                Helper(nums, currCombination, permutations);
                currCombination.remove(number);
            }
        }
    }
}
