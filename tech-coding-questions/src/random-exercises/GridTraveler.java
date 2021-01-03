import java.util.HashMap;

/*
    Question:

    We have a N x M grid. As a traveler, starting from the top left of the grid,
    you can move either down or right, one block at a time.

    Count the number of ways that you, the traveler, can reach the bottom right of the grid.
*/
public class GridTraveler {

    public void Test() {

        // System.out.println(BruteForce(1, 1));
        // System.out.println(BruteForce(2, 3));
        // System.out.println(BruteForce(3, 2));
        // System.out.println(BruteForce(3, 3));
        // System.out.println(BruteForce(18, 18));

        // System.out.println(PerformDP(1, 1));
        // System.out.println(PerformDP(2, 3));
        // System.out.println(PerformDP(3, 2));
        // System.out.println(PerformDP(3, 3));
        // System.out.println(PerformDP(18, 18));

        System.out.println(DPTabulation(1, 1));
        System.out.println(DPTabulation(2, 3));
        System.out.println(DPTabulation(3, 2));
        System.out.println(DPTabulation(3, 3));
        System.out.println(DPTabulation(18, 18));
    }

    /*
     * Time Complexity: O(x).O(y), walkthrough dimensions of the table.
     * 
     * Space Complexity: O(x).O(y), storing table information.
     */
    public long DPTabulation(int x, int y) {
        long[][] table = new long[x + 1][y + 1];
        table[1][1] = 1;

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (i + 1 < table.length)
                    table[i + 1][j] += table[i][j];
                if (j + 1 < table[i].length)
                    table[i][j + 1] += table[i][j];
            }
        }

        return table[x][y];
    }

    public long PerformDP(int x, int y) {
        HashMap<String, Long> helperMemoMap = new HashMap<String, Long>();
        return DP(x, y, helperMemoMap);
    }

    /*
     * Time Complexity: O(x).O(y), due to caching/memoisation (no repeated
     * computation).
     * 
     * Space Complexity: O(n) + O(m), due to recursion stack height + map data
     */
    public long DP(long x, long y, HashMap<String, Long> memo) {
        String key = x + "," + y;

        if (memo.containsKey(key))
            return memo.get(key);
        if (x == 1 && y == 1)
            return 1;
        if (x == 0 || y == 0)
            return 0;

        memo.put(key, DP(x - 1, y, memo) + DP(x, y - 1, memo));
        return memo.get(key);
    }

    /*
     * Time Complexity: O(2^[n + m]), two choices per decision, will make decision
     * according to the exponent [n + m] times (height of the tree).
     * 
     * Space Complexity: O(n) + O(m), due to recursion stack height. Will not get
     * higher due to recursion stack removal/additions through tree branches.
     */
    public long BruteForce(long x, long y) {

        if (x == 1 && y == 1)
            return 1;
        if (x == 0 || y == 0)
            return 0;

        return BruteForce(x - 1, y) + BruteForce(x, y - 1);
    }
}
