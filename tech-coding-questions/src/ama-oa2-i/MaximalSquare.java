/*
    https://leetcode.com/problems/maximal-square/
*/
public class MaximalSquare {

    public void Test() {

        System.out.println(PerformMS(new char[][] { { '1', '0', '1', '0', '0' }, { '1', '0', '1', '1', '1' },
                { '1', '1', '1', '1', '1' }, { '1', '0', '0', '1', '0' } }));
    }

    /*
     * DP tabulation/matrix method used here. The matrix here is used to store a
     * value that indicates the biggest square that can be constructed with its
     * position as the bottom right corner.
     * 
     * Time Complexity: O(n.m), where n refers to number of rows and m refers to
     * number of columns in matrix.
     * 
     * Space Complexity: O(n.m), which is the DP matrix that is used to store
     * maximum 1 square data.
     */
    public int PerformMS(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int[][] dp = new int[matrix.length][matrix[0].length];
        int result = 0;

        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[0].length; y++) {
                if (matrix[x][y] == '1') {
                    dp[x][y] = 1;
                    if (x > 0 && y > 0)
                        dp[x][y] += Math.min(dp[x - 1][y], Math.min(dp[x][y - 1], dp[x - 1][y - 1]));
                    result = Math.max(result, dp[x][y]);
                }
            }
        }

        return result * result;
    }
}
