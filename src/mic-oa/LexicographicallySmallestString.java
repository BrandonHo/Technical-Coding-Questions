/*
    Given a string 'str', write a function that finds the lexicographically smallest string
    that can be formed by removing at most one character from the string 'str'.
*/
public class LexicographicallySmallestString {
    
    public void Test()
    {
        System.out.println(Solve("abdc"));
        System.out.println(Solve("abczd"));
        System.out.println(Solve("abcda"));
        System.out.println(Solve("aab"));
        System.out.println(Solve("aaaaaaa"));
    }

    /*
        Time Complexity: O(n), where n refers to the size of str.

        Space Complexity: O(n), where n refers to the size of str.
    */
    public String Solve(String str)
    {
        if (str.length() == 0 || str.length() == 1)
            return "";

        String result = "";
        boolean replacedChar = false;

        // Iterate through string, from beginning to end
        for (int currStrIndex = 1; currStrIndex < str.length(); currStrIndex++)
        {
            // Check for conflict if we have yet to replace a char
            if (!replacedChar && str.charAt(currStrIndex) < str.charAt(currStrIndex - 1))
            {
                // Add the current char to result
                result = result + str.charAt(currStrIndex);
                replacedChar = true;
            }
            // Otherwise simply add the previous char to result
            else
                result = result + str.charAt(currStrIndex - 1);
        }

        return result;
    }
}
