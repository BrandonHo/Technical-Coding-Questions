import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
    Question:

    Let's say you have a target number, and an array of numbers.
    Find the smallest combination possible from the array of numbers
    that sums up to the target number. Numbers from the array can be
    used multiple times.
*/
public class BestSum {

    public void Test() {

        // System.out.println(BruteForce(7, new int[] { 5, 3, 4, 7 }));
        // System.out.println(BruteForce(8, new int[] { 2, 3, 5 }));
        // System.out.println(BruteForce(8, new int[] { 1, 4, 5 }));
        // System.out.println(BruteForce(100, new int[] { 1, 2, 5, 25 }));

        System.out.println(PerformDP(7, new int[] { 5, 3, 4, 7 }));
        System.out.println(PerformDP(8, new int[] { 2, 3, 5 }));
        System.out.println(PerformDP(8, new int[] { 1, 4, 5 }));
        System.out.println(PerformDP(100, new int[] { 1, 2, 5, 25 }));

        System.out.println("");

        System.out.println(DPTabulation(7, new int[] { 5, 3, 4, 7 }));
        System.out.println(DPTabulation(8, new int[] { 2, 3, 5 }));
        System.out.println(DPTabulation(8, new int[] { 1, 4, 5 }));
        System.out.println(DPTabulation(100, new int[] { 1, 2, 5, 25 }));
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

        List<List<Integer>> resultArray = new ArrayList<List<Integer>>();

        for (int x = 0; x <= target; x++)
            resultArray.add(new ArrayList<Integer>());

        for (int number : numbers)
            resultArray.get(number).add(number);

        for (int x = 1; x < target; x++) {
            if (resultArray.get(x).size() != 0) {
                for (int number : numbers) {
                    if (x + number < resultArray.size()) {

                        if (resultArray.get(x + number).size() == 0
                                || resultArray.get(x).size() + 1 < resultArray.get(x + number).size()) {
                            resultArray.get(x + number).clear();
                            resultArray.get(x + number).add(number);
                            resultArray.get(x + number).addAll(resultArray.get(x));
                        }
                    }
                }
            }
        }

        return resultArray.get(target);
    }

    public List<Integer> PerformDP(int target, int[] numbers) {
        HashMap<Integer, List<Integer>> helperMap = new HashMap<Integer, List<Integer>>();
        return DP(target, numbers, helperMap);
    }

    /*
     * Time Complexity: O(m.n).O(m), where m refers to the length of numbers array
     * and n refers to the target number. O(m.n) refers to required tree branches
     * for each number in recursion calls, while O(m) refers to constructing valid
     * combination arrays.
     * 
     * Space Complexity: O(n) + O(n.n), where n refers to the target number. The
     * O(n) refers to the tree depth in recursion calls, while the O(n.n) refers to
     * the storage of keys/integer arrays in hash map.
     */
    public List<Integer> DP(int target, int[] numbers, HashMap<Integer, List<Integer>> memoMap) {

        if (memoMap.containsKey(target))
            return memoMap.get(target);
        if (target == 0)
            return List.of();
        if (target < 0)
            return null;

        List<Integer> minCombinationResult = null;

        for (int number : numbers) {

            List<Integer> potentialResult = DP(target - number, numbers, memoMap);
            if (potentialResult != null) {

                List<Integer> validResult = new ArrayList<Integer>(potentialResult);
                validResult.add(number);

                if (minCombinationResult == null || validResult.size() < minCombinationResult.size())
                    minCombinationResult = validResult;
            }
        }

        memoMap.put(target, minCombinationResult);
        return memoMap.get(target);
    }

    /*
     * Time Complexity: O(m^n).O(m), where m refers to length of numbers array, n
     * refers to the target number. O(m^n) refers to the branching factor and tree
     * height, while O(m) refers to the copy of worst case list/array.
     * 
     * Space Complexity: O(n.n), where n refers to target number (tree height). This
     * comes from storing the min combination array for each recursive call.
     */
    public List<Integer> BruteForce(int target, int[] numbers) {
        if (target == 0)
            return List.of();
        if (target < 0)
            return null;

        List<Integer> minCombinationResult = null;

        for (int number : numbers) {

            List<Integer> potentialResult = BruteForce(target - number, numbers);
            if (potentialResult != null) {

                List<Integer> validResult = new ArrayList<Integer>(potentialResult);
                validResult.add(number);

                if (minCombinationResult == null || validResult.size() < minCombinationResult.size())
                    minCombinationResult = validResult;
            }
        }

        return minCombinationResult;
    }
}
