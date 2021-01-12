/*
    https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
*/
public class NUniqueIntegersSumToZero {
    
    public void Test()
    {
        DisplayResult(Solve(3));
        DisplayResult(Solve(4));
        DisplayResult(Solve(5));
        DisplayResult(Solve(7));
        DisplayResult(Solve(9));
    }

    public void DisplayResult(int[] result)
    {
        for (int num : result)
            System.out.print(num + " ");
        System.out.println();
    }

    /*
        Question says to generate ANY array that has unique integers and
        adds up to zero.

        Therefore, I generate arrays by adding pairs of numbers that cancel
        out each other to give zero. If n is odd, then I add a zero.

        For example:
        n = 5 -> [0, 1, -1, 2, -2]
        n = 7 -> [0, 1, -1, 2, -2, 3, -3]
        n = 4 -> [1, -1, 2, -2]

        Time Complexity: O(n), where n refers to the specified number n

        Space Complexity: O(n), or O(1) if not counting the result array
        as extra space, where n refers to the specified number n.
    */
    public int[] Solve(int n)
    {
        // Base case
        if (n == 1)
            return new int[] { 0 };

        int[] result = new int[n];
        int startIndex = 0;

        // If n odd, then add zero to cater for the lone element not in a pair (zero)
        if (n % 2 != 0)
        {
            result[0] = 0;
            startIndex = 1;
        }
        
        // Continously add positive and negative value of arrNum to array until n reached
        int arrNum = 1;
        for (int x = startIndex; x < n; x += 2)
        {
            result[x] = arrNum;
            result[x + 1] = arrNum * -1;
            arrNum += 1;
        }

        return result;
    }
}
