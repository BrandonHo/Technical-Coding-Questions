import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
    Question:

    Let's say you have a target number, and an array of numbers.
    Return a combination of numbers that sum up to the target number.
    If there are multiple combinations, you can return any combination.
    Numbers in the array can be used multiple times.
*/
public class HowSum {

    public void Test() {

        // System.out.println(BruteForce(7, new int[] { 2, 3 }));
        // System.out.println(BruteForce(7, new int[] { 5, 3, 4, 7 }));
        // System.out.println(BruteForce(7, new int[] { 2, 4 }));
        // System.out.println(BruteForce(8, new int[] { 2, 3, 5 }));
        // System.out.println(BruteForce(300, new int[] { 7, 14 }));

        // System.out.println(PerformDP(7, new int[] { 2, 3 }));
        // System.out.println(PerformDP(7, new int[] { 5, 3, 4, 7 }));
        // System.out.println(PerformDP(7, new int[] { 2, 4 }));
        // System.out.println(PerformDP(8, new int[] { 2, 3, 5 }));
        // System.out.println(PerformDP(300, new int[] { 7, 14 }));

        // System.out.println("");

        // System.out.println(DPTabulation(7, new int[] { 2, 3 }));
        // System.out.println(DPTabulation(7, new int[] { 5, 3, 4, 7 }));
        // System.out.println(DPTabulation(7, new int[] { 2, 4 }));
        // System.out.println(DPTabulation(8, new int[] { 2, 3, 5 }));
        // System.out.println(DPTabulation(300, new int[] { 7, 14 }));
    }

    /*
     * Time Complexity: O(n) + O(m) + O(n.m.m), where n refers to target size and m
     * refers to size of numbers array.
     * 
     * -> O(n) refers to initialisation of result array which has same size as
     * target
     * 
     * -> O(m) refers to setup of result array, which makes m numbers of calls for
     * setup.
     * 
     * -> O(n.m.m) refers to calculating valid combinations of m numbers that can be
     * summed up from 1 to n (target size). Also, during each iteration, we copy m
     * numbers to store a valid combination for a target number.
     * 
     * Space Complexity: O(n.n). There are n combinations in the results array, and
     * each combination could, worst case, store combinations of n size.
     */
    public List<Integer> DPTabulation(int target, int[] numbers) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();

        for (int x = 0; x <= target; x++)
            result.add(new ArrayList<Integer>());

        for (int number : numbers)
            if (number <= target)
                result.get(number).add(number);

        for (int x = 1; x <= target; x++) {
            if (result.get(x).size() != 0) {
                for (int number : numbers) {
                    if (x + number < result.size()) {

                        // Cleared array here to store latest combination
                        result.get(x + number).clear();
                        result.get(x + number).add(number);
                        result.get(x + number).addAll(result.get(x));

                        // This code currently stores the first possible combination
                        // if (result.get(x + number).size() == 0) {

                        // result.get(x + number).add(number);
                        // result.get(x + number).addAll(result.get(x));
                        // }
                    }
                }
            }
        }

        return result.get(target);
    }

    public List<Integer> PerformDP(int target, int[] numbers) {
        HashMap<Integer, List<Integer>> helperMemoMap = new HashMap<Integer, List<Integer>>();
        return DP(target, numbers, helperMemoMap);
    }

    /*
     * Time Complexity: O(m.n).O(n), where m refers to the length of the numbers
     * array and n refers to the target number. O(m.n) refers to recursive calls,
     * while the O(n) refers to array copying.
     * 
     * Space Complexity: O(m) + O(m.m), where m refers to the length of the numbers
     * array. O(m) refers to the storage of numbers in a list, while O(m.m) refers
     * to the maximum number of keys in helper map vs stored lists in the map.
     */
    public List<Integer> DP(int target, int[] numbers, HashMap<Integer, List<Integer>> helperMemoMap) {

        if (helperMemoMap.containsKey(target))
            return helperMemoMap.get(target);
        if (target == 0)
            return new ArrayList<Integer>();
        if (target < 0)
            return null;

        for (int number : numbers) {

            List<Integer> result = DP(target - number, numbers, helperMemoMap);
            if (result != null) {

                result.add(number);
                helperMemoMap.put(target, result);
                return result;
            }
        }

        helperMemoMap.put(target, null);
        return helperMemoMap.get(target);
    }

    /*
     * Time Complexity: O(m^n).O(m), where m refers to the length of the numbers
     * array (branching factor) and n refers to the target number (tree length). The
     * second part refers to the additions made to the list throughout the tree.
     * 
     * Space Complexity: O(m + m), where m refers to the length of the numbers array
     * (recursion stack and the worst case list space)
     */
    public List<Integer> BruteForce(int target, int[] numbers) {

        if (target == 0)
            return new ArrayList<Integer>();
        if (target < 0)
            return null;

        for (int number : numbers) {

            List<Integer> result = BruteForce(target - number, numbers);
            if (result != null) {

                result.add(number);
                return result;
            }
        }

        return null;
    }
}
