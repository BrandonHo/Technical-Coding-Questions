/*
    https://leetcode.com/problems/search-a-2d-matrix-ii/
*/
public class Search2DMatrixII {

    public void Test() {
        int[][] test1 = new int[][] { new int[] { 1, 4, 7, 11, 15 }, new int[] { 2, 5, 8, 12, 19 },
                new int[] { 3, 6, 9, 16, 22 }, new int[] { 10, 13, 14, 17, 24 }, new int[] { 18, 21, 23, 26, 30 }, };
        int target1 = 5;

        int[][] test2 = new int[][] { new int[] { 1, 1 }, };
        int target2 = 2;

        System.out.println(SearchMatrix(test1, target1));
        System.out.println(SearchMatrix(test2, target2));
    }

    /*
     * Time Complexity: O(m + n), where m and n refer to row and column sizes of the
     * matrix respectively.
     * 
     * Space Complexity: O(1).
     */
    public boolean SearchMatrix(int[][] matrix, int target) {
        int rowIndex = 0;
        int colIndex = matrix[0].length - 1;

        while (rowIndex < matrix.length && colIndex >= 0) {

            int matrixValue = matrix[rowIndex][colIndex];

            if (matrixValue == target)
                return true;
            else if (matrixValue > target)
                colIndex--;
            else
                rowIndex++;
        }

        return false;
    }
}
