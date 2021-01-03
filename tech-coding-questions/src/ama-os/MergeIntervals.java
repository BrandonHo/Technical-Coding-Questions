import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    https://leetcode.com/problems/merge-intervals/
*/
public class MergeIntervals {
    
    public void Test()
    {
        int[][] intervals = new int[][] {
            new int[] {1, 3},
            new int[] {2, 6},
            new int[] {8, 10},
            new int[] {15, 18}
        };

        DisplayResults(Solve(intervals));
    }

    public void DisplayResults(int[][] results)
    {
        for (int[] interval : results)
            System.out.println("[" + interval[0] + "," + interval[1] + "]");
    }

    /*
        Time Complexity: O(n log n) + O(n), where n refers to the number of intervals
        in the specified array.

        - O(n log n) comes from the sort function, which is said to be O(n log n)
        in the worst case according to Java Docs.

        - O(n) comes from iterating through the intervals to merge appropriate intervals.

        Space Complexity: O(n), where n refers to the number of intervals in the specified
        array. This occurs due to creating a temporary array list to hold the merged
        intervals.
    */
    public int[][] Solve(int[][] intervals)
    {
        if (intervals.length == 1)
            return intervals;

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> newIntervals = new ArrayList<>();
        int[] compareInterval = intervals[0];
        for (int index = 1; index < intervals.length; index++)
        {
            int[] nextInterval = intervals[index];
            if (compareInterval[1] >= nextInterval[0])
            {
                compareInterval[1] = Math.max(compareInterval[1], nextInterval[1]);
            }
            else
            {
                newIntervals.add(compareInterval);
                compareInterval = intervals[index];
            }
        }
        newIntervals.add(compareInterval);

        return newIntervals.toArray(new int[newIntervals.size()][]);
    }
}
