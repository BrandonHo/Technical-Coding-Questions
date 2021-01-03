/*
    https://leetcode.com/problems/valid-sudoku/
*/
public class ValidSudoku {
    
    public void Test()
    {
        char[][] board = new char[][]{
            new char[] { '5','3','.','.','7','.','.','.','.'},
            new char[] { '6','.','.','1','9','5','.','.','.'},
            new char[] { '.','9','8','.','.','.','.','6','.'},
            new char[] { '8','.','.','.','6','.','.','.','3'},
            new char[] { '4','.','.','8','.','3','.','.','1'},
            new char[] { '7','.','.','.','2','.','.','.','6'},
            new char[] { '.','6','.','.','.','.','2','8','.'},
            new char[] { '.','.','.','4','1','9','.','.','5'},
            new char[] { '.','.','.','.','8','.','.','7','9'},
        };

        char[][] board2 = new char[][] {
            new char[] {'8','3','.','.','7','.','.','.','.'},
            new char[] {'6','.','.','1','9','5','.','.','.'},
            new char[] {'.','9','8','.','.','.','.','6','.'},
            new char[]{'8','.','.','.','6','.','.','.','3'},
            new char[]{'4','.','.','8','.','3','.','.','1'},
            new char[]{'7','.','.','.','2','.','.','.','6'},
            new char[]{'.','6','.','.','.','.','2','8','.'},
            new char[]{'.','.','.','4','1','9','.','.','5'},
            new char[]{'.','.','.','.','8','.','.','7','9'}
        };

        System.out.println(IsSudokuBoardValid(board));
        System.out.println(IsSudokuBoardValid(board2));
    }

    /*
        Time Complexity: O(1). Actual time complexity is O(n.n) where n refers to number of rows
        or columns in sudoku board. However, board is guaranteed to always have 9 rows and 9 columns.
        Therefore, time complexity is constant.
        
        Space Complexity: O(1). Actual space complexity is O(n.n) + O(n.n) + O(n.n), where n refers
        to the number of rows or columsn in sudoku board. However, board is guaranteed to always have 9 rows
        and columms. Therefore, space complexity is constant.
    */
    public boolean IsSudokuBoardValid(char[][] board)
    {
        // Flags for numbers in specific rows, columns, and squares of sudoku board
        boolean[][] rowCheck = new boolean[9][9];
        boolean[][] colCheck = new boolean[9][9];
        boolean[][] squareCheck = new boolean[9][9];

        for (int rowIndex = 0; rowIndex < board.length; rowIndex++)
        {
            for (int colIndex = 0; colIndex < board[0].length; colIndex++)
            {
                // Ignore empty cells
                if (board[rowIndex][colIndex] != '.')
                {
                    // Get cell value using row and column indices
                    int cellValue = Character.getNumericValue(board[rowIndex][colIndex]);

                    // Check if cell value already exists in row
                    if (!rowCheck[rowIndex][cellValue - 1])
                        rowCheck[rowIndex][cellValue - 1] = true;
                    else
                        return false;

                    // Check if cell value already exists in column
                    if (!colCheck[colIndex][cellValue - 1])
                        colCheck[colIndex][cellValue - 1] = true;
                    else
                        return false;

                    // Check if cell value already exists in square
                    int squareIndex = (rowIndex / 3) * 3 + (colIndex / 3);
                    if (!squareCheck[squareIndex][cellValue - 1])
                        squareCheck[squareIndex][cellValue - 1] = true;
                    else
                        return false;
                }
            }
        }

        // No conflicts found - return true
        return true;
    }
}
