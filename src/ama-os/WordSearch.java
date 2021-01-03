/*
    https://leetcode.com/problems/word-search/
*/
public class WordSearch {
    
    public void Test()
    {
        char[][] testBoard = new char[][]
        {
            new char[] {'A', 'B', 'C', 'E'},
            new char[] {'S', 'F', 'C', 'S'},
            new char[] {'A', 'D', 'E', 'E'}
        };

        char[][] testBoard2 = new char[][]
        {
            new char[] {'A', 'B', 'C', 'E'},
            new char[] {'S', 'F', 'E', 'S'},
            new char[] {'A', 'D', 'E', 'E'}
        };

        System.out.println(Solve(testBoard, "ABCCE"));
        System.out.println(Solve(testBoard2, "ABCESEEEFS"));
    }

    /*
        Time Complexity: O(n.m) + O(4^z), where n and m refer to board dimensions,
        and z refers to the length of the specified word.

        - O(n.m) occurs from searching throughout the board for the appropriate
        starting char.

        - O(4^z) occurs from performing a DFS on a specific position in the board
        for a word of length z. At each iteration, 4 possible options are performed
        when searching through the board (up, down, left, right).

        Space Complexity: O(z), where z refers to the length of the specified word.
        This space complexity occurs due to recursion stack height from performing
        DFS.
    */
    public boolean Solve(char[][] board, String word)
    {
        char firstCharFromWord = word.charAt(0);

        // Iterate through the board
        for (int x = 0; x < board.length; x++)
        {
            for (int y = 0; y < board[0].length; y++)
            {
                // If we find a matching char with first char of word...
                if (board[x][y] == firstCharFromWord)
                {
                    // ... Start DFS here to see if word can be found in board
                    if (HelperDFS(x, y, board, word, 0))
                        return true;
                }
            }
        }

        return false;
    }

    public boolean HelperDFS(int x, int y, char[][] board, String word, int curIndex)
    {
        // If we hit the end of the word - word found, return true
        if (curIndex == word.length())
            return true;

        // Edge case - out of bounds
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length)
            return false;
        
        // Edge case - if char does not match specified char in word
        if (board[x][y] != word.charAt(curIndex))
            return false;
        else
        {
            // Temporarily change the board char to a placeholder (used to see what chars have been visited)
            char tempBoardCharHolder = board[x][y];
            board[x][y] = '*';

            boolean up = HelperDFS(x - 1, y, board, word, curIndex + 1);
            boolean down = HelperDFS(x + 1, y, board, word, curIndex + 1);
            boolean left = HelperDFS(x, y - 1, board, word, curIndex + 1);
            boolean right = HelperDFS(x, y + 1, board, word, curIndex + 1);

            if (up || down || left || right)
                return true;

            // Since previous attempts failed, change board char back to normal
            board[x][y] = tempBoardCharHolder;
            return false;
        }
    }
}
