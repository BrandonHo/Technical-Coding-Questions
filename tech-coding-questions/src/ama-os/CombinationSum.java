import java.util.ArrayList;
import java.util.List;

/*
    https://leetcode.com/problems/combination-sum/
*/
public class CombinationSum {

    public void Test()
    {
        DisplayResults(Solve(new int[] {2, 3, 6, 7}, 7));
        DisplayResults(Solve(new int[] {2, 3, 5}, 8));
    }

    public void DisplayResults(List<List<Integer>> results)
    {
        for (List<Integer> result : results)
        {
            for (int number : result)
                System.out.print(number + " ");
            System.out.println();
        }
    }

    /*
        Time Complexity: O(m^n), where m refers to the number of candidate numbers (branching factor) and
        n refers to the target number (recursion stack, tree height).

        Space Complexity: O(n.n), where n refers to target number. There can potentially be n number of combinations
        that have n length.
    */
    public List<List<Integer>> Solve(int[] candidates, int target)
    {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        RecursiveHelper(new ArrayList<Integer>(), 0, candidates, target, results);
        return results;
    }

    public void RecursiveHelper(List<Integer> currCombination, int start, int[] candidates, int target, List<List<Integer>> result)
    {
        // If target negative, then impossible -> stop iteration
        if (target < 0)
            return;

        // If target = 0, then valid combination -> add current combination to result list
        if (target == 0)
            result.add(new ArrayList<Integer>(currCombination));

        // Recursion using ith number for combination
        for (int i = start; i < candidates.length; i++)
        {
            currCombination.add(candidates[i]);

            // Note that the ith number is used for recursion, therefore only consider numbers from ith position
            RecursiveHelper(currCombination, i, candidates, target - candidates[i], result);

            // Remove last number from combination (since all combinations with it has been completed)
            currCombination.remove(currCombination.size() - 1);
        }
    }
}
