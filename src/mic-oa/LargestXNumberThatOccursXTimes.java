import java.util.HashMap;

/*
    Given an array A consisting of N integers, write a function that returns the biggest value X that occurs in A exactly X times.
    If there is no such value, return 0.

    Example 1:

    A = [3, 8, 2, 3, 3, 2]
    3 occurs 3 times
    2 occurs 2 times
    8 occurs once.

    Therefore valid numbers include 3 and 2, in which the maximum is 3.

    Example 2:
    A = [7, 1, 2, 8, 2]
    1 occurs once
    2 occurs 2 times
    7 and 8 only occur once

    Therefore, valid numbers include 1 and 2, in which the maximum is 2.
*/
public class LargestXNumberThatOccursXTimes {

    public void Test()
    {
        System.out.println(Solve(new int[] {3, 8, 2, 3, 3, 2}));
        System.out.println(Solve(new int[] {7, 1, 2, 8, 2}));
        System.out.println(Solve(new int[] {3, 1, 4, 1, 5}));
        System.out.println(Solve(new int[] {5, 5, 5, 5, 5}));
    }

    /*
        Time Complexity: O(n) + O(n), where n refers to the size of array A.

        Space Complexity: O(n), where n refers to the size of array A.
    */
    public int Solve(int[] arrayA)
    {
        HashMap<Integer, Integer> numberToCountMap = new HashMap<Integer, Integer>();

        // Use map to keep track of numbers and their counts
        for (int num : arrayA)
            numberToCountMap.put(num, numberToCountMap.getOrDefault(num, 0) + 1);

        // Base case
        int maximumValidNum = 0;

        // Iterate through all numbers + check if they meet the criteria for being valid and greater than base case
        for (int num : arrayA)
            if (num > maximumValidNum && num == numberToCountMap.get(num))
                maximumValidNum = num;

        return maximumValidNum;
    }
}
