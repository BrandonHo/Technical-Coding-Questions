/*
    https://leetcode.com/problems/last-substring-in-lexicographical-order/
*/
public class LastSubstringInLexicographicalOrder {
    
    public void Test()
    {
        System.out.println(Solve("abab"));
        System.out.println(Solve("cacacb"));
    }

    /*
        Time Complexity: O(n) + O(n.n), where n refers to the size of string s.

        Space Complexity: O(1)
    */
    public String Solve(String s)
    {
        // Base edge case
        if (s.length() == 1)
            return s;

        // Find the lexicograpically largest char in the string
        char largestChar = s.charAt(0);
        for (char sChar : s.toCharArray())
            if (largestChar < sChar)
                largestChar = sChar;

        String result = "";
        for (int charIndex = 0; charIndex < s.length(); charIndex++)
        {
            // Optimisation - ignore non-largest chars, since they can't be the largest lexicographically substring
            if (s.charAt(charIndex) != largestChar)
                continue;
            
            // Reference the lexicographically largest substring
            String possibleResult = s.substring(charIndex);
            if (possibleResult.compareTo(result) > 0)
                result = possibleResult;

            // Optimisation - skip subsequent largest char substrings, since they cannot have larger substrings
            while (charIndex < s.length() && s.charAt(charIndex) == largestChar)
                charIndex += 1;
        }

        return result;
    }
}
