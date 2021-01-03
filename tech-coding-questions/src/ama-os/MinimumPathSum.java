/*
    https://leetcode.com/problems/minimum-path-sum/
*/
public class MinimumPathSum {
    
    public void Test()
    {
        int[][] gridTest1 = new int[][] {
            new int[] {1, 3, 1},
            new int[] {1, 5, 1},
            new int[] {4, 2, 1}
        };

        int[][] gridTest2 = new int[][] {
            new int[] {1, 2, 3},
            new int[] {4, 5, 6}
        };

        System.out.println(SolveDP(gridTest1));
        System.out.println(SolveDP(gridTest2));
    }

    /*
        Time Complexity: O(n.m), where n and m refer to the dimensions
        of the grid.

        This is a DP problem as you can calculate the solution for three
        different types of sub-problems:
        - The top row
        - The left row
        - The squares which have a number above and to the left

        Space Complexity: O(n.m), where n and m refer to the dimensions
        of the grid.
    */
    public int SolveDP(int[][] grid)
    {
        // Initialise DP array
        int[][] minimumGridSums = new int[grid.length][grid[0].length];
        minimumGridSums[0][0] = grid[0][0];

        // Calculate min path sum for left row
        for (int x = 1; x < grid.length; x++)
            minimumGridSums[x][0] = minimumGridSums[x - 1][0] + grid[x][0];

        // Calculate min path sum for top row
        for (int y = 1; y < grid[0].length; y++)
            minimumGridSums[0][y] = minimumGridSums[0][y - 1] + grid[0][y];

        // Lastly, calculate min path sum for the various squares
        for (int x = 1; x < grid.length; x++)
            for (int y = 1; y < grid[0].length; y++)
                minimumGridSums[x][y] = grid[x][y] + Math.min(minimumGridSums[x - 1][y], minimumGridSums[x][y - 1]);

        // Return min path sum of the bottom-right-most square
        return minimumGridSums[grid.length - 1][grid[0].length - 1];
    }

    /*
        Same as above solution, however, can optimise space usage by
        only maintaining the bottom-most row of the DP array.
    
        Time Complexity: O(n.m), where n and m refer to the dimensions
        of the grid.

        Space Complexity: O(m), where m refers to the number of columns
        in the grid.
    */
    public int SolveDPOptimal(int[][] grid)
    {
        int[] botRowDP = new int[grid[0].length];

        for (int x = 0; x < grid.length; x++)
        {
            for (int y = 0; y < grid[0].length; y++)
            {
                if (x == 0 && y == 0)
                    botRowDP[y] = botRowDP[y] + grid[x][y];
                else if (x == 0)
                    botRowDP[y] = botRowDP[y - 1] + grid[x][y];
                else if (y == 0)
                    botRowDP[y] = botRowDP[y] + grid[x][y];
                else
                    botRowDP[y] = grid[x][y] + Math.min(botRowDP[y - 1], botRowDP[y]);
            }
        }

        return botRowDP[grid[0].length];
    }
}
