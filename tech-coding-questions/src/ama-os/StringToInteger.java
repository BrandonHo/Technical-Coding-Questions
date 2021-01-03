/*
    https://leetcode.com/problems/string-to-integer-atoi/
*/
public class StringToInteger {
    
    public void Test()
    {
        System.out.println(ConvertToInteger("42"));
        System.out.println(ConvertToInteger("   -42"));
        System.out.println(ConvertToInteger("4193 with words"));
        System.out.println(ConvertToInteger("words and 987"));
        System.out.println(ConvertToInteger("-91283472332"));
    }

    /*
        Time Complexity: O(n) [iterating through string] + O(n) for parsing
        the result number string to an integer, where n refers to the size
        of the specified string.

        Space Complexity: O(n), where n refers to the size of the specified
        string. This complexity exists due to the constructed result number
        string.
    */
    public int ConvertToInteger(String s)
    {
        // Skip white spaces
        int sPos = 0;
        while (sPos < s.length() && Character.isWhitespace(s.charAt(sPos)))
            sPos += 1;

        // Check if there is a negative sign at the front
        boolean isNegative = false;
        if (sPos < s.length())
        {
            if (s.charAt(sPos) == '-')
            {
                isNegative = true;
                sPos += 1;
            }
        }

        String numberString = "";
        while (sPos < s.length())
        {
            // Append each character that is a digit
            if (Character.isDigit(s.charAt(sPos)))
            {
                numberString += s.charAt(sPos);
                sPos += 1;
            }
            // If a letter or space - stop
            else
                break;
        }

        // If nothing added to number string, return 0
        if (numberString.length() == 0)
            return 0;

        return GetNumberByParsingString(numberString, isNegative);
    }

    public int GetNumberByParsingString(String numberString, boolean isNegative)
    {
        try {
            int result = Integer.parseInt(numberString);
            if (isNegative)
                return result * -1;
            return result;
        } catch (NumberFormatException e) {

            if (isNegative)
                return Integer.MIN_VALUE;
            return Integer.MAX_VALUE;
        }
    }
}
