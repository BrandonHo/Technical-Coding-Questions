/*
    https://leetcode.com/problems/longest-palindromic-substring/
*/
public class LongestPalindrome {
    
    public void Test()
    {
        System.out.println(FindLongestPalindrome("babad"));
    }

    /*
        Time Complexity: O(n.n) where n refers to the length of the specified string.

        Space Complexity: O(n), from substring method that constructs the result.
    */
    public String FindLongestPalindrome(String s)
    {
        if (s.length() == 0)
            return "";
        if (s.length() == 1)
            return s;
        
        int bestStartIndex = 0;
        int globalLongestLength = 0;
        
        // Iterate through each of the indices as center points for expanding outwards
        for (int startCenterIndex = 0; startCenterIndex < s.length(); startCenterIndex++)
        {
            // Get longest length from expanding from both odd and even centers in the string
            int longestLengthFromOddCenter = GetLongestLengthFromExpandingIndices(s, startCenterIndex, startCenterIndex);
            int longestLengthFromEvenCenter = GetLongestLengthFromExpandingIndices(s, startCenterIndex, startCenterIndex + 1);

            // Reference the longest length found
            int localLongestLength = Math.max(longestLengthFromOddCenter, longestLengthFromEvenCenter);
            if (localLongestLength > globalLongestLength)
            {
                bestStartIndex = startCenterIndex - (localLongestLength - 1) / 2;
                globalLongestLength = localLongestLength;
            }
        }
        
        // Construct substring based on the longest length found
        return s.substring(bestStartIndex, bestStartIndex + globalLongestLength);
    }

    public int GetLongestLengthFromExpandingIndices(String s, int left, int right)
    {
        // Expand indices outwards as long as the chars are equal and indices are valid
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right))
        {
            left -= 1;
            right += 1;
        }

        // Finally, return the length
        return right - left - 1;
    }
}
