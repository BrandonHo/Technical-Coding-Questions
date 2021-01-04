/*
    https://leetcode.com/problems/next-greater-element-iii/
*/
public class NextGreatestElement3 {
    
    public void Test()
    {
        // System.out.println(Solve(1132));
        // System.out.println(Solve(1341));
        System.out.println(Solve(230241));
        // System.out.println(Solve(2147483486));
    }

    /*
        Time Complexity: O(n) [looking for first digit that is not increasing] 
        + O(n) [looking for next digit that is greater than non-increasing first digit]
        + O(n / 2) [ reversing the remaining portion to get next biggest number], or ~O(n),
        where n refers to the length of num.

        Space Complexity: O(n) [char array that is constructed from the specified number]
    */
    public int Solve(int num)
    {
        String stringNum = num + "";
        char[] numCharArray = stringNum.toCharArray();

        // Iterating from the back, find the first digit where it is not increasing
        int swapPoint = -1;
        for (int charIndex = numCharArray.length - 1; charIndex > 0; charIndex--)
        {
            if (numCharArray[charIndex - 1] < numCharArray[charIndex])
            {
                swapPoint = charIndex - 1;
                break;
            }
        }
            
        // Edge case - we have the largest possible number, therefore no next greater element
        if (swapPoint == -1)
            return -1;

        // From end of array to swap point, we look for the first digit that is bigger than numCharArray[swapPoint] + swap it
        for (int charIndex = numCharArray.length - 1; charIndex > swapPoint; charIndex--)
        {
            if (numCharArray[charIndex] > numCharArray[swapPoint])
            {   
                Swap(swapPoint, charIndex, numCharArray);
                break;
            }
        }

        // We know that the part after the swap point is in descending order, so we reverse it to get the next biggest digit arrangement
        Reverse(swapPoint + 1, numCharArray.length - 1, numCharArray);

        /*
            Alternatively, you could also iterate from swap point to end of array to find the first digit
            that is bigger than numCharArray[swapPoint] + swap it.

            Afterwards, you can sort the remaining portion to get the same result.

            Sorting, however, is slower than reversing, and thus the reverse method is used here.
        */

        return TryParse(numCharArray);
    }

    public int TryParse(char[] numCharArray)
    {
        try
        {
            return Integer.parseInt(new String(numCharArray));
        }
        catch (Exception e)
        {
            return -1;
        }
    }

    public void Reverse(int startPos, int endPos, char[] charArray)
    {
        while (startPos < endPos)
        {
            Swap(startPos, endPos, charArray);
            startPos += 1;
            endPos -= 1;
        }
    }

    public void Swap(int posA, int posB, char[] charArray)
    {
        char temp = charArray[posA];
        charArray[posA] = charArray[posB];
        charArray[posB] = temp;
    }
}
